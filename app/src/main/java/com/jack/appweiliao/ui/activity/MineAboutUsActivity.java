package com.jack.appweiliao.ui.activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.jack.appweiliao.R;
import com.jack.appweiliao.ui.BaseActivity;
import com.jack.appweiliao.util.WebViewUtil;

import butterknife.BindView;

/**
 * 关于我们
 */
public class MineAboutUsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.detail_bar_btn_back)
    Button mBtnBack;
    @BindView(R.id.project_address)
    TextView mAddress;
    @BindView(R.id.detail_bar_center_title)
    TextView mCenterTitle;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initViews() {
        initTitle();
        mBtnBack.setOnClickListener(this);
        mAddress.setOnClickListener(this);
    }

    private void initTitle() {
        mCenterTitle.setText(context.getResources().getString(R.string.mine_tab_about_us));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_bar_btn_back:
                finish();
                break;
            case R.id.project_address:
                String githubUrl = "https://github.com/wukong1688/Android-AppWeiLiao";
                WebView webView = WebViewUtil.loadUrl(context, githubUrl);
                setContentView(webView);
                break;
        }
    }

}