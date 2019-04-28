package com.jack.appweiliao.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.appweiliao.R;
import com.jack.appweiliao.ui.BaseFragment;
import com.jack.appweiliao.ui.activity.MineAboutUsActivity;
import com.jack.appweiliao.ui.activity.MineSettingsActivity;

import butterknife.BindView;

public class MineListFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.main_bar_left_title)
    TextView mLeftTitle;

    @BindView(R.id.ll_setting)
    LinearLayout mSetting;

    @BindView(R.id.ll_about)
    LinearLayout mAbout;


    @Override
    protected int inflateLayoutId() {
        return R.layout.fragment_mine_list;
    }

    @Override
    protected void initViews() {
        initTitle();

        mSetting.setOnClickListener(this);
        mAbout.setOnClickListener(this);
    }

    private void initTitle() {
        mLeftTitle.setText(context.getResources().getString(R.string.main_tab_mine_name));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting:
                startActivity(new Intent(context, MineSettingsActivity.class));
                break;
            case R.id.ll_about:
                startActivity(new Intent(context, MineAboutUsActivity.class));
                break;
        }
    }
}