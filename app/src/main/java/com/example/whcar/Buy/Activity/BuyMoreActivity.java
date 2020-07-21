package com.example.whcar.Buy.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whcar.Buy.Adapter.BuyMoreTypeAdapter;
import com.example.whcar.Buy.Model.BuyMoreTypeModel;
import com.example.whcar.Home.utils.MyGridView;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class BuyMoreActivity extends AppCompatActivity {

    private MyGridView buy_more_type_gv,buy_more_age_gv,buy_more_km_gv,
            buy_more_change_gv,buy_more_pailiang_gv,buy_more_paifang_gv,
            buy_more_color_gv;
    private BuyMoreTypeAdapter moreTypeAdapter,ageAdapter,kmAdapter,changeAdapter,pailiangAdapter,paifangAdapter,colorAdapter;

    private List<BuyMoreTypeModel> typeModels = new ArrayList<>();
    private List<BuyMoreTypeModel> ageModels = new ArrayList<>();
    private List<BuyMoreTypeModel> kmModels = new ArrayList<>();
    private List<BuyMoreTypeModel> changeModels = new ArrayList<>();
    private List<BuyMoreTypeModel> pailiangModels = new ArrayList<>();
    private List<BuyMoreTypeModel> paifangModels = new ArrayList<>();
    private List<BuyMoreTypeModel> colorModels = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_more);
        initTypeData();
        initAgeData();
        initKmData();
        initChangeData();
        initView();
        initPailiang();
        initPaifang();
        initcolor();
    }

    private void initcolor() {
        BuyMoreTypeModel model = new BuyMoreTypeModel("不限");
        colorModels.add(model);
        BuyMoreTypeModel mode2 = new BuyMoreTypeModel("黑色");
        colorModels.add(mode2);
        BuyMoreTypeModel mode3 = new BuyMoreTypeModel("白色");
        colorModels.add(mode3);
        BuyMoreTypeModel mode4 = new BuyMoreTypeModel("银灰色");
        colorModels.add(mode4);
        BuyMoreTypeModel mode5 = new BuyMoreTypeModel("深灰色");
        colorModels.add(mode5);
        BuyMoreTypeModel mode6 = new BuyMoreTypeModel("红色");
        colorModels.add(mode6);
        BuyMoreTypeModel mode7 = new BuyMoreTypeModel("橙色");
        colorModels.add(mode7);
        BuyMoreTypeModel mode8 = new BuyMoreTypeModel("绿色");
        colorModels.add(mode8);
        BuyMoreTypeModel mode9 = new BuyMoreTypeModel("蓝色");
        colorModels.add(mode9);
        BuyMoreTypeModel mode10 = new BuyMoreTypeModel("紫色");
        colorModels.add(mode10);
        BuyMoreTypeModel mode11 = new BuyMoreTypeModel("黄色");
        colorModels.add(mode11);
        BuyMoreTypeModel mode12 = new BuyMoreTypeModel("粉红色");
        colorModels.add(mode12);
        BuyMoreTypeModel mode13 = new BuyMoreTypeModel("香槟色");
        colorModels.add(mode13);
        BuyMoreTypeModel mode14 = new BuyMoreTypeModel("咖啡色");
        colorModels.add(mode14);

    }

    private void initPaifang() {
        BuyMoreTypeModel model = new BuyMoreTypeModel("不限");
        paifangModels.add(model);
        BuyMoreTypeModel mode2 = new BuyMoreTypeModel("国一及以上");
        paifangModels.add(mode2);
        BuyMoreTypeModel mode3 = new BuyMoreTypeModel("国二及以上");
        paifangModels.add(mode3);
        BuyMoreTypeModel mode4 = new BuyMoreTypeModel("国三及以上");
        paifangModels.add(mode4);
        BuyMoreTypeModel mode5 = new BuyMoreTypeModel("国四及以上");
        paifangModels.add(mode5);
        BuyMoreTypeModel mode6 = new BuyMoreTypeModel("国五及以上");
        paifangModels.add(mode6);
    }

    private void initPailiang() {
        BuyMoreTypeModel models1  = new BuyMoreTypeModel("不限");
        pailiangModels.add(models1);
        BuyMoreTypeModel models2  = new BuyMoreTypeModel("1.0L以内");
        pailiangModels.add(models2);
        BuyMoreTypeModel models3  = new BuyMoreTypeModel("1.1L-1.6L");
        pailiangModels.add(models3);
        BuyMoreTypeModel models4  = new BuyMoreTypeModel("1.6L-2.0L");
        pailiangModels.add(models4);
        BuyMoreTypeModel models5  = new BuyMoreTypeModel("2.0L-2.5L");
        pailiangModels.add(models5);
        BuyMoreTypeModel models6  = new BuyMoreTypeModel("2.5L-3.0L");
        pailiangModels.add(models6);
        BuyMoreTypeModel models7  = new BuyMoreTypeModel("3.0L-4.0L");
        pailiangModels.add(models7);
        BuyMoreTypeModel models8  = new BuyMoreTypeModel("4.0L-5.0L");
        pailiangModels.add(models8);
        BuyMoreTypeModel models9  = new BuyMoreTypeModel("5.0L以上");
        pailiangModels.add(models9);
    }

    private void initChangeData() {
        BuyMoreTypeModel model1 = new BuyMoreTypeModel("不限");
        changeModels.add(model1);
        BuyMoreTypeModel model2 = new BuyMoreTypeModel("自动");
        changeModels.add(model2);
        BuyMoreTypeModel model3 = new BuyMoreTypeModel("手动");
        changeModels.add(model3);
    }

    private void initKmData() {
        BuyMoreTypeModel model1 = new BuyMoreTypeModel("不限");
        kmModels.add(model1);
        BuyMoreTypeModel model2 = new BuyMoreTypeModel("国一及以上");
        kmModels.add(model2);
        BuyMoreTypeModel model3 = new BuyMoreTypeModel("国二及以上");
        kmModels.add(model3);
        BuyMoreTypeModel model4 = new BuyMoreTypeModel("国三及以上");
        kmModels.add(model4);
        BuyMoreTypeModel model5 = new BuyMoreTypeModel("国四及以上");
        kmModels.add(model5);
        BuyMoreTypeModel model6 = new BuyMoreTypeModel("国五及以上");
        kmModels.add(model6);
    }

    private void initAgeData() {
        BuyMoreTypeModel model1 = new BuyMoreTypeModel("不限");
        ageModels.add(model1);
        BuyMoreTypeModel model2 = new BuyMoreTypeModel("一年以内");
        ageModels.add(model2);
        BuyMoreTypeModel model3 = new BuyMoreTypeModel("1-3年");
        ageModels.add(model3);
        BuyMoreTypeModel model4 = new BuyMoreTypeModel("3-5年");
        ageModels.add(model4);
        BuyMoreTypeModel model5 = new BuyMoreTypeModel("5-8年");
        ageModels.add(model5);
        BuyMoreTypeModel model6 = new BuyMoreTypeModel("8-10年");
        ageModels.add(model6);
        BuyMoreTypeModel model7 = new BuyMoreTypeModel("10年以上");
        ageModels.add(model7);
    }

    private void initTypeData() {
        BuyMoreTypeModel model1 = new BuyMoreTypeModel("不限");
        typeModels.add(model1);
        BuyMoreTypeModel model2 = new BuyMoreTypeModel("微小型车");
        typeModels.add(model2);
        BuyMoreTypeModel model3 = new BuyMoreTypeModel("紧凑型车");
        typeModels.add(model3);
        BuyMoreTypeModel model4 = new BuyMoreTypeModel("中型车");
        typeModels.add(model4);
        BuyMoreTypeModel model5 = new BuyMoreTypeModel("中大型车");
        typeModels.add(model5);
        BuyMoreTypeModel model6 = new BuyMoreTypeModel("SUV");
        typeModels.add(model6);
        BuyMoreTypeModel model7 = new BuyMoreTypeModel("MPV");
        typeModels.add(model7);
        BuyMoreTypeModel model8 = new BuyMoreTypeModel("跑车");
        typeModels.add(model8);
        BuyMoreTypeModel model9 = new BuyMoreTypeModel("皮卡");
        typeModels.add(model9);
        BuyMoreTypeModel model10 = new BuyMoreTypeModel("微面");
        typeModels.add(model10);
    }

    private void initView() {
        buy_more_type_gv = (MyGridView)findViewById(R.id.buy_more_type_gv);
        moreTypeAdapter = new BuyMoreTypeAdapter(BuyMoreActivity.this,typeModels);
        buy_more_type_gv.setAdapter(moreTypeAdapter);

        buy_more_age_gv = (MyGridView)findViewById(R.id.buy_more_age_gv);
        ageAdapter = new BuyMoreTypeAdapter(BuyMoreActivity.this,ageModels);
        buy_more_age_gv.setAdapter(ageAdapter);

        buy_more_km_gv = (MyGridView)findViewById(R.id.buy_more_km_gv);
        kmAdapter = new BuyMoreTypeAdapter(BuyMoreActivity.this,kmModels);
        buy_more_km_gv.setAdapter(kmAdapter);

        buy_more_change_gv = (MyGridView)findViewById(R.id.buy_more_change_gv);
        changeAdapter = new BuyMoreTypeAdapter(BuyMoreActivity.this,changeModels);
        buy_more_change_gv.setAdapter(changeAdapter);


        buy_more_pailiang_gv = (MyGridView)findViewById(R.id.buy_more_pailiang_gv);
        pailiangAdapter = new BuyMoreTypeAdapter(BuyMoreActivity.this,pailiangModels);
        buy_more_pailiang_gv.setAdapter(pailiangAdapter);

        buy_more_paifang_gv = (MyGridView)findViewById(R.id.buy_more_paifang_gv);
        paifangAdapter = new BuyMoreTypeAdapter(BuyMoreActivity.this,paifangModels);
        buy_more_paifang_gv.setAdapter(paifangAdapter);

        buy_more_color_gv = (MyGridView)findViewById(R.id.buy_more_color_gv);
        colorAdapter = new BuyMoreTypeAdapter(BuyMoreActivity.this,colorModels);
        buy_more_color_gv .setAdapter(colorAdapter);

        buy_more_type_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moreTypeAdapter.setMcurrentItem(position);
                moreTypeAdapter.setOnClick(true);
                moreTypeAdapter.notifyDataSetChanged();
            }
        });

        buy_more_age_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setMcurrentItem(position);
                ageAdapter.setOnClick(true);
                ageAdapter.notifyDataSetChanged();
            }
        });

        buy_more_km_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                kmAdapter.setMcurrentItem(position);
                kmAdapter.setOnClick(true);
                kmAdapter.notifyDataSetChanged();
            }
        });

        buy_more_change_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeAdapter.setMcurrentItem(position);
                changeAdapter.setOnClick(true);
                changeAdapter.notifyDataSetChanged();
            }
        });

        buy_more_pailiang_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pailiangAdapter.setMcurrentItem(position);
                pailiangAdapter.setOnClick(true);
                pailiangAdapter.notifyDataSetChanged();
            }
        });
        buy_more_paifang_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                paifangAdapter.setMcurrentItem(position);
                paifangAdapter.setOnClick(true);
                paifangAdapter.notifyDataSetChanged();
            }
        });

        buy_more_color_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                colorAdapter.setMcurrentItem(position);
                colorAdapter.setOnClick(true);
                colorAdapter.notifyDataSetChanged();
            }
        });


    }
}
