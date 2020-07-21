package com.example.whcar.Home.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whcar.Home.Adapter.HomeVerGridAdapter;
import com.example.whcar.Home.model.HomeVerGridModel;
import com.example.whcar.Home.utils.MyGridView;
import com.example.whcar.R;
import com.example.whcar.refresh_load.BaseRefreshListener;
import com.example.whcar.refresh_load.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class XinActivity extends AppCompatActivity implements BaseRefreshListener {

    private MyGridView home_ver_grid;
    private List<HomeVerGridModel> vermodels = new ArrayList<>();
    private HomeVerGridAdapter veradapter;
    private PullToRefreshLayout pull;
    private int page = 1;
    private ImageView zhubo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin);
        initView();
        initModel();
    }


    private void initModel() {
        for (int i=0;i<20;i++){
            HomeVerGridModel model1 = new HomeVerGridModel(R.drawable.dq,"准新车"+i,"201"+1+"款",
                    "201"+i+"年",i+"0万公里",i+i+i+i+"万","新车:"+i+i+i+2+"万");
            vermodels.add(model1);
        }
    }

    private void initView() {
        home_ver_grid = (MyGridView)findViewById(R.id.home_ver_grid1);
        veradapter = new HomeVerGridAdapter(XinActivity.this,vermodels);
        home_ver_grid.setAdapter(veradapter);

        pull = (PullToRefreshLayout)findViewById(R.id.home_pull1);
        pull.setRefreshListener(this);

        zhubo = (ImageView)findViewById(R.id.zhubo);
        zhubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(ZeroActivity.this, HomeFragment.class);
                startActivity(intent);*/
                finish();
            }
        });

    }

    public void refresh() {
        page = 1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pull.finishRefresh();
            }
        },2000);
    }


    public void loadMore() {
        page++;

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
}

