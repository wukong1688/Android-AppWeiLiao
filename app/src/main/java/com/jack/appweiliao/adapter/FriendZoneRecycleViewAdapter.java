package com.jack.appweiliao.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jack.appweiliao.R;
import com.jack.appweiliao.bean.DiscoverZoneBean;
import com.jack.appweiliao.util.UiUtil;

import java.util.List;

import butterknife.ButterKnife;

public class FriendZoneRecycleViewAdapter extends RecyclerView.Adapter<FriendZoneRecycleViewAdapter.ViewHolder> {

    private Context context;
    private List<DiscoverZoneBean> items;
    private ClickListener clickListener;

    public FriendZoneRecycleViewAdapter(Context context, List<DiscoverZoneBean> items) {
        this.context = context;
        this.items = items;
    }

    public interface ClickListener {
        void onItemClick(View v, int position, List<DiscoverZoneBean> items);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder viewHolder = null;

        switch (viewType) {
            case DiscoverZoneBean.VIEW_TYPE_SINGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_zone_single_image, parent, false);
                viewHolder = new ViewHolder(view);
                break;
            case DiscoverZoneBean.VIEW_TYPE_MULTI:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_zone_multi_image, parent, false);
                viewHolder = new ViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DiscoverZoneBean dataBean = items.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        TextView content = holder.get(R.id.item_content);
        content.setText(dataBean.getText());

        int imageParentWidth = (UiUtil.getScreenWidth(context) - UiUtil.dip2px(context, 90));
        switch (getItemViewType(position)) {
            case DiscoverZoneBean.VIEW_TYPE_SINGLE:
                buildSingleImage(dataBean, holder, imageParentWidth);
                break;
            case DiscoverZoneBean.VIEW_TYPE_MULTI:
                buildMultiImages(dataBean, holder, imageParentWidth);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                clickListener.onItemClick(holder.itemView, pos, items);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void buildSingleImage(final DiscoverZoneBean data, ViewHolder holder, int imageParentWidth) {
        final ImageView imageView = holder.get(R.id.item_image);
        imageView.getLayoutParams().height = (int) (imageParentWidth * 9f / 16);
        imageView.setLayoutParams(imageView.getLayoutParams());
        Glide.with(context)
                .load(data.images.get(0))
                .placeholder(R.drawable.place_holder)
                .transform(new CustomFriendImageTransform(context, ImageView.ScaleType.CENTER_CROP))
                .into(imageView);
    }

    private void buildMultiImages(final DiscoverZoneBean data, ViewHolder holder, int imageParentWidth) {
        final RecyclerView recyclerView = holder.get(R.id.item_image);
        int column = data.images.size() == 4 ? 2 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(context, column));
        int space = UiUtil.dip2px(context, 3);
        final int itemWidth = (imageParentWidth - space * (column - 1)) / column;
        final int itemHeight = column == 2 ? (int) (itemWidth * 9f / 16) : itemWidth;
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                ImageView image = new ImageView(parent.getContext());
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(itemWidth, itemHeight);
                image.setLayoutParams(layoutParams);
                return new ViewHolder(image);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ImageView image = (ImageView) holder.itemView;
                Glide.with(context)
                        .load(data.images.get(position))
                        .placeholder(R.drawable.place_holder)
                        .transform(new CustomFriendImageTransform(context, ImageView.ScaleType.CENTER_CROP))
                        .into(image);
            }

            @Override
            public int getItemCount() {
                return data.images.size();
            }
        });
    }

    /**
     * ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        <T extends View> T get(int id) {
            return (T) itemView.findViewById(id);
        }
    }
}