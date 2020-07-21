package com.example.whcar.Home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import com.example.whcar.Buy.BuyFragment;
import com.example.whcar.Home.Activity.BuyBuyActivity;
import com.example.whcar.Home.Activity.ClassifySearchActivity;
import com.example.whcar.Home.Activity.ShoppingDetailActivity;
import com.example.whcar.Home.Activity.XinActivity;
import com.example.whcar.Home.Activity.ZeroActivity;
import com.example.whcar.Home.Adapter.HomeHorGridAdapter;
import com.example.whcar.Home.Adapter.HomeOneGridAdapter;

import com.example.whcar.Home.Adapter.HomeThreeGridAdapter;
import com.example.whcar.Home.Adapter.HomeTwoGridAdapter;
import com.example.whcar.Home.Adapter.HomeVerGridAdapter;
import com.example.whcar.Home.model.HomeHorGridModel;
import com.example.whcar.Home.model.HomeHorGridModel1;
import com.example.whcar.Home.model.HomeHorGridModel2;
import com.example.whcar.Home.model.HomeHorGridModel3;
import com.example.whcar.Home.model.HomeVerGridModel;
import com.example.whcar.Home.utils.MyGridView;
import com.example.whcar.MainActivity;

import com.example.whcar.R;
import com.example.whcar.Sell.Activity.GuJiaActivity;
import com.example.whcar.Sell.Activity.YuActivity;
import com.example.whcar.refresh_load.BaseRefreshListener;
import com.example.whcar.refresh_load.PullToRefreshLayout;
import com.google.zxing.client.android.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnBannerListener, BaseRefreshListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private View view;
    private Banner banner;
    private ArrayList<Integer> imgs;

    private MyGridView home_hor_grid;
    private MyGridView home_one_grid;
    private MyGridView home_two_grid;
    private MyGridView home_three_grid;

    private List<HomeHorGridModel> hormodels = new ArrayList<>();
    private List<HomeHorGridModel1> hormodels1 = new ArrayList<>();
    private List<HomeHorGridModel2> hormodels2 = new ArrayList<>();
    private List<HomeHorGridModel3> hormodels3 = new ArrayList<>();


    private HomeHorGridAdapter horadapter;
    private HomeOneGridAdapter adapter;
    private HomeTwoGridAdapter horadapter2;
    private HomeThreeGridAdapter horadapter3;

    private MyGridView home_ver_grid;
    private List<HomeVerGridModel> vermodels = new ArrayList<>();
    private HomeVerGridAdapter veradapter;

    private PullToRefreshLayout pull;
    private int page = 1;

    private Fragment curragefragment;

    private RelativeLayout scratchBcakground;

    //    private RecyclerView mToolbar;
//    private View mHeaderView;
    private  ImageView xiao,home_phone_img,home_address_img;
    String ss= "13159511437";
    //扫一扫
   private ImageView sao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home,container,false);
        call_phone();

        initView();
        initHorGrid();
        initVerGrid();

        initoneGrid();
        inittwoGrid();
        initthreeGrid();



////渐变搜索
//        NestedScrollView nestedScrollView = view.findViewById(R.id.nested_scroll_view);
//        mHeaderView = view.findViewById(R.id.header);
//
//        final int startColor = ContextCompat.getColor(getContext(), R.color.clickColor);
//        final int endColor = ContextCompat.getColor(getContext(), R.color.item_click_bg_color);
//
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                int headerHeight = mHeaderView.getHeight();
//                int scrollDistance = Math.min(scrollY, headerHeight);
//                float fraction = (float) scrollDistance / (float) headerHeight;
//
//                setToolbarStatusBarAlpha(evaluate(fraction, startColor, endColor));
//                setNavigationViewColor(evaluate(fraction, endColor, startColor));
//            }
//        });
//
//        Sofia.with(getActivity())
//                .statusBarBackground(ContextCompat.getColor(getActivity(), R.color.clickColor))
//                .navigationBarBackground(ContextCompat.getDrawable(getContext(), R.color.item_click_bg_color));

        return view;
    }

//    private void setSupportActionBar(Toolbar mToolbar) {
//
//    }
//
//
//    private void setToolbarStatusBarAlpha(int color) {
//        DrawableCompat.setTint(mToolbar.getBackground().mutate(), color);
//        Sofia.with(getActivity())
//                .statusBarBackground(color);
//    }
//
//    private void setNavigationViewColor(int color) {
//        Sofia.with(getActivity())
//                .navigationBarBackground(color);
//    }
//
//    public int evaluate(float fraction, int startValue, int endValue) {
//        int startA = (startValue >> 24) & 0xff;
//        int startR = (startValue >> 16) & 0xff;
//        int startG = (startValue >> 8) & 0xff;
//        int startB = startValue & 0xff;
//
//        int endA = (endValue >> 24) & 0xff;
//        int endR = (endValue >> 16) & 0xff;
//        int endG = (endValue >> 8) & 0xff;
//        int endB = endValue & 0xff;
//
//        return ((startA + (int) (fraction * (endA - startA))) << 24) |
//                ((startR + (int) (fraction * (endR - startR))) << 16) |
//                ((startG + (int) (fraction * (endG - startG))) << 8) |
//                ((startB + (int) (fraction * (endB - startB))));
//    }


    private void initVerGrid() {
        if (page==1){
            vermodels.clear();
            for (int i=0;i<20;i++){
                HomeVerGridModel model1 = new HomeVerGridModel(R.drawable.ddf,"奔驰G63"+i,"201"+1+"款",
                        "201"+i+"年",i+"0万公里",i+i+i+i+"万","新车:"+i+i+i+2+"万");
                vermodels.add(model1);
            }
        }else {
            for (int i=0;i<20;i++){
                HomeVerGridModel model1 = new HomeVerGridModel(R.drawable.ddf,"车"+i,"201"+1+"款",
                        "201"+i+"年",i+"0万公里",i+i+i+i+"万","新车:"+i+i+i+2+"万");
                vermodels.add(model1);
            }
        }


    }

    private void ceshi(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    if(grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getContext(),"必须授权才能进行",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else {
                    Toast.makeText(getContext(),"发生未知错误", Toast.LENGTH_SHORT).show();
                }
        }
    }



    private void initHorGrid() {
        HomeHorGridModel model1 = new HomeHorGridModel(R.mipmap.renwu,"查征信");
        hormodels.add(model1);
        HomeHorGridModel model2 = new HomeHorGridModel(R.mipmap.zhibo,"免费估价");
        hormodels.add(model2);
        HomeHorGridModel model3 = new HomeHorGridModel(R.mipmap.shoufa,"准新车");
        hormodels.add(model3);
        HomeHorGridModel model4 = new HomeHorGridModel(R.mipmap.you,"全国优选");
        hormodels.add(model4);
        HomeHorGridModel model5 = new HomeHorGridModel(R.mipmap.geng,"智能荐车");
        hormodels.add(model5);
        HomeHorGridModel model6 = new HomeHorGridModel(R.mipmap.shoufu,"0首付");
        hormodels.add(model6);
        HomeHorGridModel model7 = new HomeHorGridModel(R.mipmap.papo,"求购好车");
        hormodels.add(model7);
        HomeHorGridModel model8 = new HomeHorGridModel(R.mipmap.maiche,"快速卖车");
        hormodels.add(model8);
    }
    private void initoneGrid() {
        HomeHorGridModel1 model1 = new HomeHorGridModel1(R.mipmap.da,"大众");
        hormodels1.add(model1);
        HomeHorGridModel1 model2 = new HomeHorGridModel1(R.mipmap.ao,"奥迪");
        hormodels1.add(model2);
        HomeHorGridModel1 model3 = new HomeHorGridModel1(R.mipmap.ben,"奔驰");
        hormodels1.add(model3);
        HomeHorGridModel1 model4 = new HomeHorGridModel1(R.mipmap.bao,"宝马");
        hormodels1.add(model4);
        HomeHorGridModel1 model5 = new HomeHorGridModel1(R.mipmap.tian,"本田");
        hormodels1.add(model5);
        HomeHorGridModel1 model6 = new HomeHorGridModel1(R.mipmap.biek,"别克");
        hormodels1.add(model6);
        HomeHorGridModel1 model7 = new HomeHorGridModel1(R.mipmap.yiqi,"日产");
        hormodels1.add(model7);
        HomeHorGridModel1 model8 = new HomeHorGridModel1(R.mipmap.gend,"更多");
        hormodels1.add(model8);
    }
    private void inittwoGrid() {
        HomeHorGridModel2 model1 = new HomeHorGridModel2("5万以下");
        hormodels2.add(model1);
        HomeHorGridModel2 model2 = new HomeHorGridModel2("5-10万");
        hormodels2.add(model2);
        HomeHorGridModel2 model3 = new HomeHorGridModel2("10-15万");
        hormodels2.add(model3);
        HomeHorGridModel2 model4 = new HomeHorGridModel2("15万以上");
        hormodels2.add(model4);
        HomeHorGridModel2 model5 = new HomeHorGridModel2("首付1万以内");
        hormodels2.add(model5);
        HomeHorGridModel2 model6 = new HomeHorGridModel2("首付1-3万");
        hormodels2.add(model6);
        HomeHorGridModel2 model7 = new HomeHorGridModel2("首付3-5万");
        hormodels2.add(model7);
        HomeHorGridModel2 model8 = new HomeHorGridModel2("首付5万以上");
        hormodels2.add(model8);
    }
    private void initthreeGrid() {
        HomeHorGridModel3 model1 = new HomeHorGridModel3(R.drawable.two,"两厢轿车");
        hormodels3.add(model1);
        HomeHorGridModel3 model2 = new HomeHorGridModel3(R.drawable.three,"三厢轿车");
        hormodels3.add(model2);
        HomeHorGridModel3 model3 = new HomeHorGridModel3(R.drawable.suv,"SUV");
        hormodels3.add(model3);
        HomeHorGridModel3 model4 = new HomeHorGridModel3(R.drawable.mvp,"MPV");
        hormodels3.add(model4);

    }

    private void initView() {
        banner = (Banner)view.findViewById(R.id.home_banner);
        imgs = new ArrayList<>();
        imgs.add(R.drawable.aq);
        imgs.add(R.drawable.ddf);
        imgs.add(R.drawable.dq);
        imgs.add(R.drawable.er);
        imgs.add(R.drawable.p);
        banner.setImageLoader(new MyLoader());
        banner.setImages(imgs);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.setBannerStyle(BannerConfig.RIGHT)
                .setOnBannerListener(this)
                .start();

        home_hor_grid = (MyGridView) view.findViewById(R.id.home_hor_grid);
        horadapter = new HomeHorGridAdapter(getContext(),hormodels);
        xiao = (ImageView)view.findViewById(R.id.xiao);
        xiao.setOnClickListener(this);
        sao =(ImageView)view.findViewById(R.id.sao);
        sao.setOnClickListener(this);
        //扫一扫

        home_phone_img = (ImageView)view.findViewById(R.id.home_phone_img);
        home_phone_img.setOnClickListener(this);


        home_hor_grid.setAdapter(horadapter);
        home_hor_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Toast.makeText(getContext(),"维护中",Toast.LENGTH_SHORT).show();
                }
                if (position==1){
                    startActivity(new Intent(getContext(), GuJiaActivity.class));
                }
                if (position==2){
                    startActivity(new Intent(getContext(), XinActivity.class));
                }
                if (position==3){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("userloginflag",1);
                    startActivity(intent);
                }
                if (position==4){
                    startActivity(new Intent(getContext(), BuyBuyActivity.class));
                }
                if (position==5){
                    startActivity(new Intent(getContext(), ZeroActivity.class));
                }
                if (position==6){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("userloginflag",1);
                    startActivity(intent);

                }
                if (position==7){
                    startActivity(new Intent(getContext(), YuActivity.class));
                }
            }
        });
//跳转

        home_one_grid = (MyGridView) view.findViewById(R.id.home_one_grid);
        adapter = new HomeOneGridAdapter(getContext(),hormodels1);
        home_one_grid.setAdapter(adapter);

        home_two_grid = (MyGridView) view.findViewById(R.id.home_two_grid);
        horadapter2 = new HomeTwoGridAdapter(getContext(),hormodels2);
        home_two_grid.setAdapter(horadapter2);

        home_three_grid = (MyGridView) view.findViewById(R.id.home_three_grid);
        horadapter3 = new HomeThreeGridAdapter(getContext(),hormodels3);
        home_three_grid.setAdapter(horadapter3);

        home_ver_grid = (MyGridView)view.findViewById(R.id.home_ver_grid);
        veradapter = new HomeVerGridAdapter(getContext(),vermodels);
        home_ver_grid.setAdapter(veradapter);

        home_ver_grid.setOnItemClickListener(this);

        pull = (PullToRefreshLayout)view.findViewById(R.id.home_pull);
        pull.setRefreshListener(this);

//        home_address_img  = (ImageView)view.findViewById(R.id.home_address_img);
//        home_address_img.setOnClickListener(this);

        scratchBcakground = (RelativeLayout) view.findViewById(R.id.scratch_bcakground);
        scratchBcakground.setOnClickListener(this);
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void refresh() {
        page = 1;
        initVerGrid();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pull.finishRefresh();
            }
        },2000);
    }

    @Override
    public void loadMore() {
        page++;
        initVerGrid();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pull.finishLoadMore();
            }
        },2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.home_address_img:
//                startActivity(new Intent(getContext(), GeoFence_District_Activity.class));
//                break;
            case R.id.scratch_bcakground:
//                Intent intent2 = new Intent(getActivity(), AddressEditActivity.class);
//                Intent intent2 = new Intent(getActivity(), AddressListActivity.class);
//                startActivity(intent2);
                Intent intent2=new Intent(getContext(), ClassifySearchActivity.class);
                startActivity(intent2);
            case  R.id. xiao:
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:" + ss));
                //开启打电话的意图
                startActivity(intent);
                break;

            case  R.id.home_phone_img:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_CALL);
                intent1.setData(Uri.parse("tel:"+ss));
                startActivity(intent1);
                break;
            case  R.id.sao:
                ceshi();
                Intent iadfasd = new Intent(getContext(), CaptureActivity.class);
                startActivity(iadfasd);
                break;
        }
    }


  //动态获取电话权限
    public void call_phone() {
       ActivityCompat.requestPermissions( getActivity(),new String[]{Manifest.permission.CALL_PHONE},1);

    }


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

//    private void startScanActivity() {
//        Intent intent = new Intent(getContext(), CaptureActivity2.class);
//        intent.putExtra(CaptureActivity2.USE_DEFUALT_ISBN_ACTIVITY, true);
//        startActivityForResult(intent, SCAN_REQUEST_CODE);
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case CAMERA_PERMISSION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    startScanActivity();
//                } else {
//                    Toast.makeText(getContext(), "请手动打开摄像头权限", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//                break;
//        }
//    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == SCAN_REQUEST_CODE) {
//                //Todo Handle the isbn number entered manually
//                String isbn = data.getStringExtra("CaptureIsbn");
//                if (!TextUtils.isEmpty(isbn)) {
//                    //todo something
//                    Toast.makeText(getContext(), "解析到的内容为" + isbn, Toast.LENGTH_LONG).show();
//                    if (isbn.contains("http")) {
//                        Intent intent = new Intent(getContext(), WebViewActivity.class);
//                        intent.putExtra(WebViewActivity.RESULT, isbn);
//                        startActivity(intent);
//                    }
//                }
//            }
//        }
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getContext(), ShoppingDetailActivity.class));
    }

    private class MyLoader extends com.youth.banner.loader.ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
