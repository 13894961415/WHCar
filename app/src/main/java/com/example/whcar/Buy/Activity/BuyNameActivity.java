package com.example.whcar.Buy.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whcar.Buy.Activity.datas.CarBean;
import com.example.whcar.Buy.Activity.datas.RecCarAdapter;
import com.example.whcar.Buy.Activity.datas.Utils;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import qdx.stickyheaderdecoration.NormalDecoration;

public class BuyNameActivity extends AppCompatActivity {
    @BindView(R.id.recView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_name);
        ButterKnife.bind(this);

        CarBean carBean = new Utils().readFromAssets(BuyNameActivity.this);
        final List<CarBean.CarInfo> carList = carBean.getData();


        RecCarAdapter adapter = new RecCarAdapter(this);
        adapter.addDatas(carList);

        final NormalDecoration decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int pos) {
                return carList.get(pos).getInitial();
            }
        };

        decoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
            @Override
            public void headerClick(int pos) {
                Toast.makeText(BuyNameActivity.this, "点击到头部" + carList.get(pos).getInitial(), Toast.LENGTH_SHORT).show();
            }
        });

        final LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);


        //侧边导航栏
        IndexLayout indexLayout = (IndexLayout) findViewById(R.id.index_layout);
        List<String> heads = new ArrayList<>();
        for (CarBean.CarInfo car : carList) {
            if (!heads.contains(car.getInitial())) {
                heads.add(car.getInitial());
            }
        }
        indexLayout.setIndexBarHeightRatio(0.9f);
        indexLayout.getIndexBar().setIndexsList(heads);
        indexLayout.setCircleTextColor(Color.RED);
        indexLayout.setCircleRadius(200);
        indexLayout.setCirCleTextSize(150);
        indexLayout.setCircleColor(ContextCompat.getColor(this, R.color.circle_bg));
        indexLayout.getIndexBar().setIndexChangeListener(new IndexBar.IndexChangeListener() {
            @Override
            public void indexChanged(String indexName) {
                for (int i = 0; i < carList.size(); i++) {
                    if (indexName.equals(carList.get(i).getInitial())) {
                        manager.scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });

    }
}
