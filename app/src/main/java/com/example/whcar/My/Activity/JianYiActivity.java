package com.example.whcar.My.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whcar.R;

import static com.example.whcar.BuildConfig.DEBUG;

public class  JianYiActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView zhuan;//结束页面
    private TextView app,xianxia;
    private Button tijiao;
    private EditText shuoming,phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jianyi_my);

        initView();

    }

    private void initView() {

        zhuan = (ImageView)findViewById(R.id.zhuan);
        zhuan.setOnClickListener(this);
        app = (TextView) findViewById(R.id.app);
        xianxia = (TextView) findViewById(R.id.xianxia);
        app.setOnClickListener(this);
        xianxia.setOnClickListener(this);
        tijiao = (Button)findViewById(R.id.tijiao);
        tijiao.setOnClickListener(this);
        shuoming = (EditText)findViewById(R.id.shuoming);
        phone = (EditText)findViewById(R.id.phone);

        shuoming.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (app.isClickable()||xianxia.isClickable()) {
                    if (s.length()>=20){
                        if (shuoming.length()==11){
                            tijiao.setBackgroundColor(Color.parseColor("#008000"));
                        }else {
                            tijiao.setBackgroundColor(Color.parseColor("#9d9d9d"));
                        }
                    }else {
                        tijiao.setBackgroundColor(Color.parseColor("#9d9d9d"));
                    }
                }else {
                    tijiao.setBackgroundColor(Color.parseColor("#9d9d9d"));
                }
            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (app.isClickable()||xianxia.isClickable()) {
                    if (shuoming.length()>=20){
                        if (s.length()==11){
                            tijiao.setBackgroundColor(Color.parseColor("#008000"));
                        }else {
                            tijiao.setBackgroundColor(Color.parseColor("#9d9d9d"));
                        }
                    }else {
                        tijiao.setBackgroundColor(Color.parseColor("#9d9d9d"));
                    }
                }else {
                    tijiao.setBackgroundColor(Color.parseColor("#9d9d9d"));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.zhuan:
                finish();
                break;
            case R.id.app:
                app.setTextColor(this.getResources().getColor(R.color.green));
                xianxia.setTextColor(this.getResources().getColor(R.color.gray));
                if ((app.isClickable()||xianxia.isClickable())&&shuoming.getText().length()>=20&&phone.getText().length()>=11){
                    tijiao.setBackgroundColor(Color.parseColor("#008000"));
                }else {
                    tijiao.setBackgroundColor(Color.parseColor("#9d9d9d"));
                }
                break;
            case R.id.xianxia:
                xianxia.setTextColor(this.getResources().getColor(R.color.green));
                app.setTextColor(this.getResources().getColor(R.color.gray));
                if ((app.isClickable()||xianxia.isClickable())&&shuoming.getText().length()>=20&&phone.getText().length()>=11){
                    tijiao.setBackgroundColor(Color.parseColor("#008000"));
                }else {
                    tijiao.setBackgroundColor(Color.parseColor("#9d9d9d"));
                }
                break;
            default:
        }

    }
}
