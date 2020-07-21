package com.example.whcar.Sell.Activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.whcar.Buy.Activity.BuyNameActivity;
import com.example.whcar.R;
import com.example.whcar.Sell.untils.Config;
import com.example.whcar.Sell.untils.JsonBean;
import com.example.whcar.Sell.untils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuJiaActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout maiche;
    private RelativeLayout chexing;
    private ImageView zhuboimageview;
    //三级联动
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private List<JsonBean> jsonBean = new ArrayList<>();
    private String shengid,cityid,areaid="";

    private TextView mTvadress,asdfasdfas;
    private EditText gongli;
    private  RelativeLayout pan1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gujia);
        inivtView();
        new User_Get_All_Addr().execute("");
    }

    private void inivtView() {
        maiche = (RelativeLayout)findViewById(R.id.maiche);
        maiche.setOnClickListener(this);

        chexing = (RelativeLayout)findViewById(R.id.chexing);
        chexing.setOnClickListener(this);

        pan1 = (RelativeLayout)findViewById(R.id.pan1);
        pan1.setOnClickListener(this);

        mTvadress = (TextView)findViewById(R.id.mTvadress1);
        asdfasdfas = (TextView)findViewById(R.id.asdfasdfas);

        zhuboimageview  =(ImageView)findViewById(R.id.zhuboimageview);
        zhuboimageview.setOnClickListener(this);

        EditText gongli = (EditText)findViewById(R.id.gongli);
        gongli.setSelection(gongli.getText().length());



    }



    private void showPickerView() {
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3);
                //  tvOptions.setText(tx);
                Log.e("w",""+tx);
                mTvadress.setText(tx);
            }
        }).build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.maiche:
                /*startActivity(new Intent(GuJiaActivity.this, SanActivity.class));*/
                showPickerView();
                break;
            case R.id.chexing:
                startActivity(new Intent(GuJiaActivity.this, BuyNameActivity.class));
                break;

            case  R.id.zhuboimageview:
                finish();
                break;
            case R.id.pan1:
                initTimePicker();
                break;
        }


    }


    private void initTimePicker() {//Dialog 模式下，在底部弹出
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
//                tvTime.setText(getTime(date));
                asdfasdfas.setText(getTime(date));
            }
        }).build();
        // pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }




    private class User_Get_All_Addr extends AsyncTask<String,Integer,Object> {
        @Override
        protected Object doInBackground(String... strings) {
            return HttpGet_All_Addr();
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            String reString = result.toString();

            try {
                JSONObject jsonObject = new JSONObject(reString);
                int status = jsonObject.getInt("Status");
                if (status==1){
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    List<JsonBean> json = JSON.parseArray(jsonArray+"",JsonBean.class);
                    jsonBean.addAll(json);
                    initJsonData();
                }else {
                    Toast.makeText(GuJiaActivity.this, "获取信息失败，请联系客服", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("liu", "接口异常输出-----" + e);
            }
        }
    }

    private void initJsonData() {
        //解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //  获取json数据
//        String JsonData = JsonFileReader.getJson(this, "province_data.json");
//        List<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){
            ArrayList<String> CityList = new ArrayList<>();
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();

            for (int c=0;c<jsonBean.get(i).getArea().size();c++){
                String CityName = jsonBean.get(i).getArea().get(c).getArea_name();
                CityList.add(CityName);

                ArrayList<String> City_AreaList = new ArrayList<>();

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getArea().get(c).getBusiness()==null
                        ||jsonBean.get(i).getArea().get(c).getBusiness().size()==0){
                    City_AreaList.add("");
                }else {
                    for (int d=0;d<jsonBean.get(i).getArea().get(c).getBusiness().size();d++){
                        String AreaName = jsonBean.get(i).getArea().get(c).getBusiness().get(d).getBusiness_name();
                        City_AreaList.add(AreaName);
                    }
                }
                Province_AreaList.add(City_AreaList);
            }
            options2Items.add(CityList);
            options3Items.add(Province_AreaList);
        }

    }

    private String HttpGet_All_Addr() {
        Tools tools = new Tools();
        String resString = "";
        resString = tools.doGetData(Config.BASE_URL+"Get_All_Addr");

        Log.e("liu", "获取城市信息 Get_All_Addr 接口返回值：" + resString);
        return resString;
    }
}
