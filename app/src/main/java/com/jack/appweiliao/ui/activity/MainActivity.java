package com.jack.appweiliao.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.appweiliao.R;
import com.jack.appweiliao.adapter.MainViewPagerAdapter;
import com.jack.appweiliao.ui.BaseActivity;
import com.jack.appweiliao.ui.fragment.DiscoverListFragment;
import com.jack.appweiliao.ui.fragment.MessageListFragment;
import com.jack.appweiliao.ui.fragment.MineListFragment;
import com.jack.appweiliao.util.ToastUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements FragmentTabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    @BindView(R.id.main_view_pager)
    ViewPager viewPager;

    private Context mContext;

    private LayoutInflater layoutInflater;
    private Class mClass[] = {MessageListFragment.class, DiscoverListFragment.class, MineListFragment.class};
    private Fragment mFragment[] = {new MessageListFragment(), new DiscoverListFragment(), new MineListFragment()};
    private String mTitles[] = {"微聊", "发现", "我的"};
    //    private String[] mTitles;
    private int mImages[] = {
            R.drawable.main_tab_btn_news,
            R.drawable.main_tab_btn_image,
            R.drawable.main_tab_btn_mine
    };
    private long exitTime = 0;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mContext = MainActivity.this;
        layoutInflater = LayoutInflater.from(this);//加载布局管理器

//        initTitles();
        initTabHost();
        initViewPager();
    }

    private void initTitles() {
        mTitles[0] = mContext.getResources().getString(R.string.main_tab_index_name);
        mTitles[1] = mContext.getResources().getString(R.string.main_tab_discover_name);
        mTitles[2] = mContext.getResources().getString(R.string.main_tab_mine_name);
    }

    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_view_pager);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mFragment.length; i++) {
            FragmentTabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTitles[i]).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec, mClass[i], null);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }
        mTabHost.setOnTabChangedListener(this);
    }

    private void initViewPager() {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), mTitles, mFragment);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    private View getTabView(int index) {
        View view = layoutInflater.inflate(R.layout.main_tab_indicator, null);

        ImageView image = (ImageView) view.findViewById(R.id.iv_tab_icon);
        TextView title = (TextView) view.findViewById(R.id.tv_tab_text);

        image.setImageResource(mImages[index]);
        title.setText(mTitles[index]);

        return view;
    }

    /**
     * TabHost TabChange
     *
     * @param tabId
     */
    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }

        int position = mTabHost.getCurrentTab();
        viewPager.setCurrentItem(position);//把选中的Tab的位置赋给适配器，让它控制页面切换

        supportInvalidateOptionsMenu();
    }

    /**
     * ViewPager 切换
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //根据位置 position 设置当前的Tab
        mTabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.show(context, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
