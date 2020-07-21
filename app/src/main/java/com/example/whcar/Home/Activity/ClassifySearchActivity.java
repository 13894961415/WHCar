package com.example.whcar.Home.Activity;

import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whcar.Home.utils.CircleImageView;
import com.example.whcar.Home.utils.ClearEditText;
import com.example.whcar.Home.utils.DrawableUtils;
import com.example.whcar.Home.utils.FlowLayout;
import com.example.whcar.R;

/**
 * Created by wt on 2018/3/19.
 */

public class ClassifySearchActivity extends AppCompatActivity {

    private FlowLayout searchFlowLayout;
    private ClearEditText searchEdit;
    private String[] data = new String[]{ "玛莎拉蒂", "保时捷",
            "捷豹XJ", "兰博基尼", "法拉利", "宾利",
            "奔驰", "路虎", "宝马", "奥迪",
            "丰田", "本田", "现代", "起亚" };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_search);
        initView();
        initData();
    }

    private void initView() {
        //搜索条
//        searchList=(RecyclerView)findViewById(R.id.act_classify_search_list);
        searchFlowLayout=(FlowLayout)findViewById(R.id.search_flow_layout);
        searchEdit=(ClearEditText)findViewById(R.id.act_sousuo);

//        initAdapter();

    }

//    private void initAdapter() {
//        searchList.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
//        searchHeadModels.add(new SearchHeadModel(true,"热门搜索"));
//        searchHeadModels.add(new SearchHeadModel(new SearchModel("以纯满300减30")));
//        searchHeadModels.add(new SearchHeadModel(new SearchModel("以纯满300")));
//        searchHeadModels.add(new SearchHeadModel(new SearchModel("以纯满30减30")));
//        searchHeadModels.add(new SearchHeadModel(new SearchModel("以纯")));
//        searchHeadModels.add(new SearchHeadModel(new SearchModel("300减30")));
//        searchHeadModels.add(new SearchHeadModel(new SearchModel("00减30")));
//        searchHeadModels.add(new SearchHeadModel(new SearchModel("以纯0减30")));
//        searchHeadModels.add(new SearchHeadModel(new SearchModel("以纯满")));
//        searchAdapter=new SearchAdapter(R.layout.item_search_content,R.layout.item_head_search,searchHeadModels);
//        searchList.setAdapter(searchAdapter);
//
//    }

    //搜索标签
    private void initData() {
        int padding = dip2px(5);
        searchFlowLayout.setPadding(padding, padding, padding, padding);// 设置内边距
        for (int i = 0; i < data.length; i++) {
            final String tag = data[i];
            TextView tv = new TextView(this);
            tv.setText(tag);
            tv.setTag(i);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setPadding(padding, padding, padding, padding);

            tv.setGravity(Gravity.CENTER);
            final int color = 0xffcecece;// 按下后偏白的背景色
            StateListDrawable selector;
//            if (i == 0) {
//                tv.setTextColor(Color.RED);
////                tv.setEnabled(false);
//                selector = DrawableUtils.getSelector(false, Color.parseColor("#2c90d7"), color, dip2px(30));
//            } else {
//                selector = DrawableUtils.getSelector(true, Color.WHITE, color, dip2px(30));
//            }
            selector = DrawableUtils.getSelector(false, Color.WHITE, color, dip2px(5));
            tv.setBackgroundDrawable(selector);

            searchFlowLayout.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    searchEdit.setText(tag);
                    searchEdit.setSelection(tag.trim().length());

                    int key = searchFlowLayout.getChildCount();
                    for (int i =0 ;i<key;i++){
                        final TextView t = (TextView) searchFlowLayout.getChildAt(i);
                        StateListDrawable selector;
                        if (t.getTag()==v.getTag()){
                            Log.e("liu","选中了这个view");
                            //第一个颜色是抬起的颜色，第二个是按下的颜色，第三个是圆角
                            selector = DrawableUtils.getSelector(true, Color.WHITE, color, dip2px(5));
                            t.setTextColor(Color.RED);
                            t.setBackgroundDrawable(selector);
                        }else {
                            selector = DrawableUtils.getSelector(false, Color.WHITE, color, dip2px(5));
                            t.setTextColor(Color.GRAY);
                            t.setBackgroundDrawable(selector);
                        }
                    }
                }
            });
        }

    }

    public int dip2px(float dip) {
        float density = this.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

}
