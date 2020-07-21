package com.example.whcar.My.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whcar.R;
import com.example.whcar.Sell.untils.Tools;
import com.example.whcar.untils.ResultCode;


/**
 * Created by wt on 2017/9/20.
 */

public class ReviseNameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView nickName_ok, nick_name_back;
    private EditText userNickname;
    private LinearLayout nichenglayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_user_name);
        init();
    }

    private void init() {
        nickName_ok = (TextView) findViewById(R.id.nickName_ok);
        userNickname = (EditText) findViewById(R.id.userNickname);
        nichenglayout = (LinearLayout) findViewById(R.id.nichenglayout);
        nick_name_back = (TextView) findViewById(R.id.nick_name_back);

        nichenglayout.setOnClickListener(this);
        nick_name_back.setOnClickListener(this);
        nickName_ok.setOnClickListener(this);

//        InputMethodManager imm1 = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm1.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
//        imm1.hideSoftInputFromWindow(userNickname.getWindowToken(), 0);//从控件所在的窗口中隐藏

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //do something...
            Intent intent = getIntent();
            intent.putExtra("text", "");
            setResult(ResultCode.FAILURE, intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nickName_ok:

                if (userNickname.getText() != null && userNickname.getText().length() > 1 && userNickname.getText().length() < 10) {
                    if ( Tools.compileExChar(userNickname.getText().toString())){
                        Toast.makeText(ReviseNameActivity.this,"不允许输入特殊符号！", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = getIntent();
                        intent.putExtra("text", "" + userNickname.getText().toString());
                        setResult(ResultCode.SUCCESS, intent);
                        finish();
                    }

                } else {

                    Toast.makeText(ReviseNameActivity.this, "请填写正确格式!", Toast.LENGTH_SHORT).show();

                }


                break;
            case R.id.nick_name_back:

                Intent intent = getIntent();
                intent.putExtra("text", "");
                setResult(ResultCode.FAILURE, intent);
                finish();
                break;
            //隐藏软键盘
            case R.id.nichenglayout:
                InputMethodManager imm1 = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

                break;

        }

    }
}
