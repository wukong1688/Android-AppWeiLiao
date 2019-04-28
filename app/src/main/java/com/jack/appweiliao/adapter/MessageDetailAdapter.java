package com.jack.appweiliao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.appweiliao.R;
import com.jack.appweiliao.bean.MessageChatBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageDetailAdapter extends RecyclerView.Adapter<MessageDetailAdapter.ViewHolder> {
    private Context context;
    private List<MessageChatBean> items;
    private ClickListener clickListener;

    public MessageDetailAdapter(Context context, List<MessageChatBean> items) {
        this.context = context;
        this.items = items;
    }

    public interface ClickListener {
        void onItemClick(View v, int position, List<MessageChatBean> items);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder viewHolder = null;

        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_msg_list_left, parent, false);
                viewHolder = new ViewHolder(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_msg_list_right, parent, false);
                viewHolder = new ViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MessageChatBean messageBean = items.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mItemMsg.setText(messageBean.getMsg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                clickListener.onItemClick(holder.itemView, pos, items);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_msg)
        TextView mItemMsg;
        @BindView(R.id.head_img)
        ImageView mHeadImg;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}