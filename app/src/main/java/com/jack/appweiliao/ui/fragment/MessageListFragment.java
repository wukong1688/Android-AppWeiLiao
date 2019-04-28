package com.jack.appweiliao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jack.appweiliao.R;
import com.jack.appweiliao.adapter.CustomRecyclerViewAdapter;
import com.jack.appweiliao.bean.MessageListBean;
import com.jack.appweiliao.mock.MessageListConstant;
import com.jack.appweiliao.ui.BaseFragment;
import com.jack.appweiliao.ui.activity.MessageDetailActivity;
import com.jack.appweiliao.ui.activity.MineSettingsActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * 消息列表
 */
public class MessageListFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    @Nullable
    @BindView(R.id.main_bar_left_title)
    TextView mLeftTitle;

    @BindView(R.id.message_recycle_list)
    XRecyclerView mRecyclerView;

    private CustomRecyclerViewAdapter<MessageListBean> mAdapter;
    private List<MessageListBean> mList = new ArrayList<>();
    private int times = 0;

    @Override
    protected int inflateLayoutId() {
        return R.layout.fragment_message_list;
    }

    protected void initViews() {
        initTitle();

        //1)初始化RecyclerView设置
        initSettingList();

        //2)添加布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //3)配置Adapter
        mList = genData();
        mAdapter = new CustomRecyclerViewAdapter<MessageListBean>(mList, R.layout.item_message_list_view) {
            @Override
            public void bindView(ViewHolder holder, MessageListBean obj) {
                holder.setText(R.id.message_list_nick_name, obj.getNickName());
                holder.setText(R.id.message_list_last_msg, obj.getLastMsg());
                holder.setText(R.id.message_list_item_time, obj.getTime());
                holder.setImageResource(R.id.head_avatar, obj.getAvatar());
            }
        };
        mRecyclerView.setAdapter(mAdapter);

        //4) 监听 点击,注意是监听 mAdapter ，而不是 mRecyclerView
        mAdapter.setOnItemClickListener(new CustomRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, MessageDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dataBean", mList.get(position - 1));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        //5)实现 下拉刷新和加载更多 接口
        mRecyclerView.setLoadingListener(this);
    }

    private void initTitle() {
        mLeftTitle.setText(context.getResources().getString(R.string.main_tab_index_name));
    }

    /**
     * 初始化设置 RecycleList 列表
     */
    private void initSettingList() {
        mRecyclerView.setPullRefreshEnabled(false); //禁止 下拉刷新
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
    }

    @NonNull
    /**
     * 生成数据实例
     */
    private List<MessageListBean> genData() {
        List<MessageListBean> mList = new ArrayList<>();
        int len = MessageListConstant.nickNameList.length;
        for (int i = 0; i < len; i++) {
            mList.add(new MessageListBean(MessageListConstant.nickNameList[i], MessageListConstant.avatarList[i],
                    MessageListConstant.lastMsgList[i], MessageListConstant.timeList[i]));
        }
        for (int i = 0; i < len; i++) {
            mList.add(new MessageListBean(MessageListConstant.nickNameList[i], MessageListConstant.avatarList[i],
                    MessageListConstant.lastMsgList[i], MessageListConstant.timeList[i]));
        }

        Collections.shuffle(mList);//打乱顺序输出，为了美观
        return mList;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        if (times < 2) {//加载2次后，就不再加载更多
            List<MessageListBean> list = genData();
            mList.addAll(list); //将数据追加到后面

            mAdapter.notifyDataSetChanged();
            mRecyclerView.loadMoreComplete();
        } else {
            List<MessageListBean> list = genData();
            mList.addAll(list); //将数据追加到后面

            mAdapter.notifyDataSetChanged();
            mRecyclerView.setNoMore(true);
        }
        times++;


    }
}