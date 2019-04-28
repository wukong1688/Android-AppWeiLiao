package com.jack.appweiliao.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jack.appweiliao.R;
import com.jack.appweiliao.adapter.FriendZoneRecycleViewAdapter;
import com.jack.appweiliao.bean.DiscoverZoneBean;
import com.jack.appweiliao.ui.BaseActivity;
import com.jack.appweiliao.util.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * 发现 - 朋友圈
 */
public class DiscoverFriendActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.detail_bar_btn_back)
    TextView mBtnBack;
    @BindView(R.id.detail_bar_center_title)
    TextView mCenterTitle;
    @BindView(R.id.discover_friend_recycle_list)
    RecyclerView mRecyclerView;

    private FriendZoneRecycleViewAdapter mAdapter;
    private List<DiscoverZoneBean> mList;

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_discover_friend;
    }

    @Override
    protected void initViews() {
        initTitle();
        mBtnBack.setOnClickListener(this);
        initRecycleView();
    }

    private void initTitle() {
        mCenterTitle.setText(context.getResources().getString(R.string.discover_friend_zone));
    }

    private void initRecycleView() {
        //1)添加布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //2)配置Adapter
        mList = genData();
        mAdapter = new FriendZoneRecycleViewAdapter(context, mList);
        mRecyclerView.setAdapter(mAdapter);

        //3)监听器
        mAdapter.setOnItemClickListener(new FriendZoneRecycleViewAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position, List<DiscoverZoneBean> items) {

            }
        });
    }

    private List<DiscoverZoneBean> genData() {
        List<DiscoverZoneBean> mList = new ArrayList<>();

        String response = FileUtils.getFromAssets(context, "discover_zone.json");
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                DiscoverZoneBean itemData = new DiscoverZoneBean();
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                itemData.setText(jsonObject.optString("text"));
                JSONArray images = jsonObject.optJSONArray("images");
                for (int j = 0; j < images.length(); j++) {
                    String url = images.optString(j);
                    itemData.images.add(url);
                }

                mList.add(itemData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.shuffle(mList);//打乱顺序输出，为了美观
        return mList;
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
