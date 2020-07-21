package com.example.whcar.Home.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import com.example.whcar.Home.Adapter.SimpleFragmentPagerAdapter;
import com.example.whcar.Home.fragment.CarDetailFragment;
import com.example.whcar.Home.fragment.CarNewsFragment;
import com.example.whcar.Home.fragment.CarTakeFragment;
import com.example.whcar.Home.fragment.GoodsDetailWebFragment;
import com.example.whcar.Home.utils.PullUpToLoadMore;
import com.example.whcar.Home.utils.VerticalViewPager;
import com.example.whcar.R;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class ShoppingDetailActivity extends AppCompatActivity implements OnBannerListener {

    private GoodsDetailWebFragment goodsDetailWebFragment;
    private ArrayList<Fragment> list_fragment = new ArrayList<>();
    private ArrayList<String> list_title = new ArrayList<>();
    private SimpleFragmentPagerAdapter pagerAdapter;
    private VerticalViewPager viewPager;
    private TabLayout tabLayout;
    private Banner detailBanner;
    private List<Integer> bannerModels = new ArrayList<>();

    private ImageView huiding;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        initView();
    }

    private void initView() {
        final PullUpToLoadMore ptlm = (PullUpToLoadMore)findViewById(R.id.ptlm);
        huiding = (ImageView)findViewById(R.id.huiding);
        huiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ptlm.scrollToTop();
            }
        });
        initBanner();

        initTabView();
    }

    private void initTabView() {
        initDatas();
        findViews();
        initViews();
    }

    private void initViews() {
        for (int i=0;i<1;i++){
            list_fragment.add(new CarNewsFragment());
        }
        for (int i=1;i<2;i++){
            list_fragment.add(new CarDetailFragment());
        }
        for (int i=2;i<list_title.size();i++){
            list_fragment.add(new CarTakeFragment());
        }
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),this,list_fragment,list_title);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void findViews() {
        tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        viewPager = (VerticalViewPager) findViewById(R.id.viewpager);
    }

    private void initDatas() {
        list_title.add("车辆信息");
        list_title.add("详细配置");
        list_title.add("车辆实拍");
    }

    private void initBanner() {
        detailBanner = (Banner)findViewById(R.id.detail_banner);
        bannerModels.add(R.drawable.banner1);
        bannerModels.add(R.drawable.banner2);
        bannerModels.add(R.drawable.banner3);
        bannerModels.add(R.drawable.banner4);
        bannerModels.add(R.drawable.banner5);

        detailBanner.setImageLoader(new MyLoader());
        detailBanner.setImages(bannerModels);
        detailBanner.setBannerAnimation(Transformer.Default);
        detailBanner.setDelayTime(2000);
        detailBanner.isAutoPlay(false);
        detailBanner.setBannerStyle(BannerConfig.RIGHT)
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void OnBannerClick(int position) {

    }

    private class MyLoader extends com.youth.banner.loader.ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
