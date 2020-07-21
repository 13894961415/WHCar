package com.example.whcar.Buy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.whcar.Buy.Activity.BuyMoreActivity;
import com.example.whcar.Buy.Activity.BuyNameActivity;
import com.example.whcar.Buy.Adapter.ButPriceAdapter;
import com.example.whcar.Buy.Adapter.BuyHotListAdapter;
import com.example.whcar.Buy.Adapter.BuyListAdapter;
import com.example.whcar.Buy.Model.BuyHotListModel;
import com.example.whcar.Buy.Model.BuyListModel;
import com.example.whcar.Buy.Model.BuyPriceModel;
import com.example.whcar.Home.Activity.ShoppingDetailActivity;
import com.example.whcar.Home.utils.MyGridView;
import com.example.whcar.Home.utils.ScrollViewContainListView;
import com.example.whcar.R;
import com.example.whcar.refresh_load.BaseRefreshListener;
import com.example.whcar.refresh_load.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class BuyFragment extends Fragment implements BaseRefreshListener, View.OnClickListener {

    private View view;
    private ScrollViewContainListView lv;
    private List<BuyListModel> models = new ArrayList<>();
    private BuyListAdapter adapter;

    private PullToRefreshLayout pull;
    private int pagr = 1;

    private ScrollViewContainListView buy_hot_lv;
    private BuyHotListAdapter hotListAdapter;
    private List<BuyHotListModel> hotmodels = new ArrayList<>();


    private TextView buy_old,buy_new,price_black_item;
    private LinearLayout hotup,hotdown,nameup,namedown,priceup,pricedown,moreup,moredown;
    private RelativeLayout buy_hot_visi;
    private TextView home_black_item;
    private RelativeLayout buy_price_visi;

    private MyGridView buy_price_gv;
    private ButPriceAdapter priceAdapter;
    private List<BuyPriceModel> priceModels = new ArrayList<>();
    private  ScrollViewContainListView buy_lv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.actvivity_buy,container,false);

        initData();
        initHotData();
        initPriceData();
        initView();
        return view;
    }

    private void initPriceData() {
        BuyPriceModel model1 = new BuyPriceModel("不限");
        priceModels.add(model1);
        BuyPriceModel model2 = new BuyPriceModel("5万以内");
        priceModels.add(model2);
        BuyPriceModel model3 = new BuyPriceModel("5-8万");
        priceModels.add(model3);
        BuyPriceModel model4 = new BuyPriceModel("8-10万");
        priceModels.add(model4);
        BuyPriceModel model5 = new BuyPriceModel("10-15万");
        priceModels.add(model5);
        BuyPriceModel model6 = new BuyPriceModel("15-20万");
        priceModels.add(model6);
        BuyPriceModel model7 = new BuyPriceModel("20-30万");
        priceModels.add(model7);
        BuyPriceModel model8 = new BuyPriceModel("30-50万");
        priceModels.add(model8);
        BuyPriceModel model9 = new BuyPriceModel("50-80万");
        priceModels.add(model9);
        BuyPriceModel model10 = new BuyPriceModel("80到100万");
        priceModels.add(model10);
        BuyPriceModel model11 = new BuyPriceModel("100万以上");
        priceModels.add(model11);
    }

    private void initHotData() {
        BuyHotListModel model1 = new BuyHotListModel(R.drawable.duihao,"热门车源");
        hotmodels.add(model1);
        BuyHotListModel model2 = new BuyHotListModel(R.drawable.duihao,"最新上架");
        hotmodels.add(model2);
        BuyHotListModel model3 = new BuyHotListModel(R.drawable.duihao,"价格最低");
        hotmodels.add(model3);
        BuyHotListModel model4 = new BuyHotListModel(R.drawable.duihao,"里程最短");
        hotmodels.add(model4);
        BuyHotListModel model5 = new BuyHotListModel(R.drawable.duihao,"里程最短");
        hotmodels.add(model5);
        BuyHotListModel model6 = new BuyHotListModel(R.drawable.duihao,"车龄最短");
        hotmodels.add(model6);
    }

    private void initData() {
        for (int i=0;i<20;i++){
            BuyListModel model = new BuyListModel(R.drawable.aq,"汽车"+i,
                    "201"+i+"款"+i+".0L"+"精英版","201"+i+"年0"+i+"月",
                    i+".7万公里","武汉","40.0"+i+"万","新车:40.0"+i+"万");
            models.add(model);
        }
    }

    private void initView() {
        pull = (PullToRefreshLayout)view.findViewById(R.id.buy_pull);
        pull.setRefreshListener(this);
        lv = (ScrollViewContainListView)view.findViewById(R.id.buy_lv);
        adapter = new BuyListAdapter(getContext(),models);
        lv.setAdapter(adapter);

        buy_old = (TextView)view.findViewById(R.id.buy_old);
        buy_new = (TextView)view.findViewById(R.id.buy_new);
        buy_old.setOnClickListener(this);
        buy_new.setOnClickListener(this);

        buy_lv= (ScrollViewContainListView)view.findViewById(R.id.buy_lv);
        buy_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(), ShoppingDetailActivity.class));
            }
        });

        buy_hot_lv = (ScrollViewContainListView)view.findViewById(R.id.buy_hot_lv);
        hotListAdapter = new BuyHotListAdapter(getContext(),hotmodels);
        buy_hot_lv.setAdapter(hotListAdapter);
        buy_hot_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hotListAdapter.setCurrentItem(position);
                hotListAdapter.setClick(true);
                hotListAdapter.notifyDataSetChanged();
            }
        });

        hotup = (LinearLayout)view.findViewById(R.id.hotup);
        hotup.setOnClickListener(this);
        hotdown = (LinearLayout)view.findViewById(R.id.hotdown);
        hotdown.setOnClickListener(this);

        buy_hot_visi = (RelativeLayout)view.findViewById(R.id.buy_hot_visi);
        home_black_item = (TextView) view.findViewById(R.id.home_black_item);
        home_black_item.setOnClickListener(this);

        priceup = (LinearLayout)view.findViewById(R.id.priceup);
        priceup.setOnClickListener(this);
        pricedown = (LinearLayout)view.findViewById(R.id.pircedown);
        pricedown.setOnClickListener(this);

        buy_price_visi = (RelativeLayout) view.findViewById(R.id.buy_price_visi);

        buy_price_gv = (MyGridView) view.findViewById(R.id.buy_price_gv);
        priceAdapter = new ButPriceAdapter(getContext(),priceModels);
        buy_price_gv.setAdapter(priceAdapter);
        buy_price_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                priceAdapter.setItem(position);
                priceAdapter.setClick(true);
                priceAdapter.notifyDataSetChanged();
            }
        });

        price_black_item = (TextView)view.findViewById(R.id.price_black_item);
        price_black_item.setOnClickListener(this);

        moreup = (LinearLayout)view.findViewById(R.id.moreup);
        moreup.setOnClickListener(this);

        nameup = (LinearLayout)view.findViewById(R.id.nameup);
        nameup.setOnClickListener(this);
    }

    @Override
    public void refresh() {
        pagr = 1;
        initData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pull.finishRefresh();
            }
        },2000);
    }

    @Override
    public void loadMore() {
        pagr++;
        initData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pull.finishLoadMore();
            }
        },2000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_old:
                buy_old.setTextColor(Color.RED);
                buy_new.setTextColor(Color.GRAY);
                buy_old.setBackgroundResource(R.drawable.gray_search_back);
                buy_new.setBackgroundResource(R.drawable.title_search_back);
                break;
            case R.id.buy_new:
                buy_new.setTextColor(Color.RED);
                buy_old.setTextColor(Color.GRAY);
                buy_new.setBackgroundResource(R.drawable.gray_search_back);
                buy_old.setBackgroundResource(R.drawable.title_search_back);
                break;
            case R.id.hotup:
                hotup.setVisibility(View.GONE);
                hotdown.setVisibility(View.VISIBLE);
                buy_hot_visi.setVisibility(View.VISIBLE);
                break;
            case R.id.hotdown:
                hotup.setVisibility(View.VISIBLE);
                hotdown.setVisibility(View.GONE);
                buy_hot_visi.setVisibility(View.GONE);
                break;
            case R.id.home_black_item:
                hotup.setVisibility(View.VISIBLE);
                hotdown.setVisibility(View.GONE);
                buy_hot_visi.setVisibility(View.GONE);
                break;
            case R.id.priceup:
                priceup.setVisibility(View.GONE);
                pricedown.setVisibility(View.VISIBLE);
                buy_price_visi.setVisibility(View.VISIBLE);
                break;
            case R.id.pircedown:
                priceup.setVisibility(View.VISIBLE);
                pricedown.setVisibility(View.GONE);
                buy_price_visi.setVisibility(View.GONE);
                break;
            case R.id.price_black_item:
                priceup.setVisibility(View.VISIBLE);
                pricedown.setVisibility(View.GONE);
                buy_price_visi.setVisibility(View.GONE);
                break;
            case R.id.moreup:
                startActivity(new Intent(getContext(), BuyMoreActivity.class));
                break;
            case R.id.nameup:
                startActivity(new Intent(getContext(), BuyNameActivity.class));
                break;
            default:
        }
    }



}
