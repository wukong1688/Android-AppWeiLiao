package com.jack.appweiliao.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jack.appweiliao.R;
import com.jack.appweiliao.adapter.MessageDetailAdapter;
import com.jack.appweiliao.bean.MessageChatBean;
import com.jack.appweiliao.bean.MessageListBean;
import com.jack.appweiliao.ui.BaseActivity;
import com.jack.appweiliao.util.UiUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MessageDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.detail_bar_btn_back)
    TextView mBtnBack;

    @BindView(R.id.detail_bar_left_title)
    TextView mLeftTitle;

    @BindView(R.id.detail_bar_center_title)
    TextView mCenterTitle;

    @BindView(R.id.btn_message_send)
    Button mBtnSend;

    @BindView(R.id.et_message_input)
    EditText mMsgInput;

    @BindView(R.id.message_detail_recycle_list)
    RecyclerView mRecyclerView;

    private MessageDetailAdapter mAdapter;
    private List<MessageChatBean> mList;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initViews() {
        initTitle();
        initRecycle();
    }

    private void initTitle() {
        mBtnBack.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mCenterTitle.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        MessageListBean dataBean = (MessageListBean) intent.getSerializableExtra("dataBean");
        mLeftTitle.setText(dataBean.getNickName().toString());
    }

    private void initRecycle() {
        //1)添加布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //2)配置Adapter
        mList = genData();
        mAdapter = new MessageDetailAdapter(context, mList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MessageDetailAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position, List<MessageChatBean> items) {

            }
        });
    }

    private List<MessageChatBean> genData() {
        List<MessageChatBean> mList = new ArrayList<>();
        String date = new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date());

        mList.add(new MessageChatBean("你好啊", 1, date, 1));
        mList.add(new MessageChatBean("请问在吗", 1, date, 1));
        mList.add(new MessageChatBean("在的呢", 2, date, 1));
        mList.add(new MessageChatBean("你好", 2, date, 1));

        return mList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_bar_btn_back: //返回
                finish();
            case R.id.btn_message_send: //发送
                String inputContent = mMsgInput.getText().toString();
                if (!TextUtils.isEmpty(inputContent)) {
                    MessageChatBean messageBean = new MessageChatBean();
                    messageBean.setDate(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
                    messageBean.setMsg(inputContent);
                    messageBean.setType(2);
                    messageBean.setState(1);

                    mList.add(messageBean);
                    mAdapter.notifyDataSetChanged();
                    mMsgInput.clearFocus();
                    mMsgInput.setSelected(false);
                    mMsgInput.setText("");
                    UiUtil.hideSoftKeyboard(context, mMsgInput);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRecyclerView != null) {
            mRecyclerView = null;
        }
    }
}
