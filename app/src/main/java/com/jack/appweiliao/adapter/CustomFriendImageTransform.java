package com.jack.appweiliao.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.widget.ImageView.ScaleType;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

public class CustomFriendImageTransform extends BitmapTransformation {
    ScaleType scaleType;

    public CustomFriendImageTransform(Context context, ScaleType scaleType) {
        super(context);
        this.scaleType = scaleType;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        if (null == source || source.isRecycled()) {
            return null;
        }
        switch (scaleType) {
            case CENTER_CROP: {
                final Bitmap toReuse = pool.get(outWidth, outHeight, source.getConfig() != null
                        ? source.getConfig() : Bitmap.Config.ARGB_8888);
                Bitmap transformed = TransformationUtils.centerCrop(toReuse, source, outWidth, outHeight);
                if (toReuse != null && toReuse != transformed && !pool.put(toReuse)) {
                    toReuse.recycle();
                }
                return transformed;
            }
            case FIT_XY: {
                float scaleX = (float) outWidth / source.getWidth();
                float scaleY = (float) outHeight / source.getHeight();
                Bitmap result = Bitmap.createBitmap(outWidth, outHeight, getSafeConfig(source));
                Matrix matrix = new Matrix();
                matrix.setScale(scaleX, scaleY);
                final Canvas canvas = new Canvas(result);
                final Paint paint = new Paint(Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);
                canvas.drawBitmap(source, matrix, paint);
                return result;
            }
        }
        return source;
    }

    private static Bitmap.Config getSafeConfig(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    @Override
    public String getId() {
        return scaleType.name();
    }
}
