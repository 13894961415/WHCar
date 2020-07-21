package com.example.whcar.My.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whcar.R;

public class GuYuActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView zhuan;//结束页面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guanyu_my);

        initView();

    }

    private void initView() {
        zhuan = (ImageView)findViewById(R.id.zhuan);
        zhuan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuan:
           finish();
           break;
        }
    }
}
