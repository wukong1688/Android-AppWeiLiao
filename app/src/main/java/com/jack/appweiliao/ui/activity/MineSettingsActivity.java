package com.jack.appweiliao.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.jack.appweiliao.R;
import com.jack.appweiliao.ui.BaseActivity;

import butterknife.BindView;

public class MineSettingsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.detail_bar_btn_back)
    TextView mBtnBack;
    @BindView(R.id.detail_bar_center_title)
    TextView mCenterTitle;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_mine_setting;
    }

    @Override
    protected void initViews() {
        initTitle();
        mBtnBack.setOnClickListener(this);
    }

    private void initTitle() {
        mCenterTitle.setText(context.getResources().getString(R.string.mine_tab_setting));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_bar_btn_back:
                finish();
                break;
        }
    }
}

