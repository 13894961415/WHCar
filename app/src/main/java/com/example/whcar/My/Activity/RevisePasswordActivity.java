package com.example.whcar.My.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whcar.R;
import com.example.whcar.Sell.untils.Config;
import com.example.whcar.Sell.untils.Tools;
import com.example.whcar.untils.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;


/**
 * Created by wt on 2017/9/20.
 */

public class RevisePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText password_jiu, password_c, password_xin;
    private TextView password_ok;
    private TextView back;

    //dialog
    private Dialog dialog;
    private LinearLayout xiugailayout;

    private void showLoading() {
        dialog = WeiboDialogUtils.createLoadingDialog(RevisePasswordActivity.this, "加载中...");
    }

    private void closeLoading() {
        WeiboDialogUtils.closeDialog(dialog);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_revise_passworld);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        password_jiu = (EditText) findViewById(R.id.center_password_jiu);
        password_xin = (EditText) findViewById(R.id.center_password_xin);
        password_c = (EditText) findViewById(R.id.center_password_c);
        password_ok = (TextView) findViewById(R.id.revise_password_ok);
        back = (TextView) findViewById(R.id.revise_password_back);

        xiugailayout=(LinearLayout)findViewById(R.id.xiugailayout);

        xiugailayout.setOnClickListener(this);
        password_ok.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.revise_password_back:
                finish();
                break;
            case R.id.revise_password_ok:
                if (password_xin.getText().length() > 5 &&password_xin.getText().length()<20) {
                    if (password_xin.getText().toString().equals(password_c.getText().toString())) {
                        showLoading();
                        new User_Update_Password().execute("");
                    } else {
                        Toast.makeText(RevisePasswordActivity.this, "两次输入新密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RevisePasswordActivity.this,"请填写正确格式!", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.xiugailayout:
                InputMethodManager imm1 = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                break;
        }
    }

    public class User_Update_Password extends AsyncTask<String, Integer, Object> {

        @Override
        protected String doInBackground(String... params) {
            return HttpUpdatePassword();
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            String reString = result.toString();
            Log.e("接口返回值", "" + reString);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(reString);
                int status = jsonObject.getInt("Status");
                if (status == 1) {
                    SharedPreferences sharedPre2 = RevisePasswordActivity.this.getSharedPreferences("keys", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sharedPre2.edit();
                    editor2.remove("User_Id");
                    editor2.commit();

                    finish();
                } else {
                    Toast.makeText(RevisePasswordActivity.this, "原密码不正确,请重新输入", Toast.LENGTH_SHORT).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("JSONException", "" + e);
            }
            closeLoading();
        }
    }

    private String HttpUpdatePassword() {
        SharedPreferences sharedPre = RevisePasswordActivity.this.getSharedPreferences("keys", Context.MODE_PRIVATE);
        String user_Id = sharedPre.getString("User_Id", "");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("User_Id", user_Id);
        map.put("Original_Password", password_jiu.getText().toString());
        map.put("Password", password_xin.getText().toString());
        Tools tools = new Tools();
        String resString = "";
        try {
            resString = tools.doPostTokenData2(Config.BASE_URL + "User_Update_Password", map);
            Log.e("修改密码返回值", "" + resString);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("修改密码token异常", "" + e);
        }
        return resString;
    }

}
