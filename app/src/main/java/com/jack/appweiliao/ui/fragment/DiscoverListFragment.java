package com.jack.appweiliao.ui.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.appweiliao.R;
import com.jack.appweiliao.ui.BaseFragment;
import com.jack.appweiliao.ui.activity.DiscoverFriendActivity;

import butterknife.BindView;

public class DiscoverListFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.main_bar_left_title)
    TextView mLeftTitle;
    @BindView(R.id.ll_friend_zone)
    LinearLayout mFriendZone;
    @BindView(R.id.ll_look)
    LinearLayout mLook;
    @BindView(R.id.ll_search)
    LinearLayout mSearch;

    @Override
    protected int inflateLayoutId() {
        return R.layout.fragment_dicover_list;
    }

    @Override
    protected void initViews() {
        initTitle();
        mFriendZone.setOnClickListener(this);
        mLook.setOnClickListener(this);
        mSearch.setOnClickListener(this);
    }

    private void initTitle() {
        mLeftTitle.setText(context.getResources().getString(R.string.main_tab_discover_name));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_friend_zone:
            case R.id.ll_look:
            case R.id.ll_search:
                Intent intent = new Intent(context, DiscoverFriendActivity.class);
                context.startActivity(intent);
                break;

        }
    }
}