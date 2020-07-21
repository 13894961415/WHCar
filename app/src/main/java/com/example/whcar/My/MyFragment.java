package com.example.whcar.My;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.example.whcar.Home.model.UserInfoModel;
import com.example.whcar.Home.utils.CircleImageView;
import com.example.whcar.My.Activity.CenterdataActivity;
import com.example.whcar.My.Activity.GuYuActivity;
import com.example.whcar.My.Activity.JianYiActivity;
import com.example.whcar.My.Activity.SheZhiActivity;
import com.example.whcar.R;
import com.example.whcar.Sell.untils.Config;
import com.example.whcar.Sell.untils.Tools;
import com.example.whcar.untils.RequestCode;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class MyFragment extends Fragment implements  View.OnClickListener  {

    private View view;
    private LinearLayout my_lingquan;
    private ImageView shezhi;
    private  LinearLayout my_kefu;
    String ss= "13159511437";
    private LinearLayout my_yijian;
    private CircleImageView my_photo;
    public String user_Id,user_Integral;
    public SharedPreferences sharedPre;
    private TextView my_name,my_integral;
    private UserInfoModel userInfoModel;
    private RelativeLayout  center_data;

    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_my,container,false);

        inivtView();
        return view;
    }

    private void inivtView() {
        my_lingquan = (LinearLayout) view.findViewById(R.id.my_lingquan);
        my_lingquan.setOnClickListener(this);
        shezhi =(ImageView)view.findViewById(R.id.shezhi);
        shezhi.setOnClickListener(this);
        my_kefu =(LinearLayout) view.findViewById(R.id.my_kefu);
        my_kefu.setOnClickListener(this);

        my_yijian=(LinearLayout)view.findViewById(R.id.my_yijian);
        my_yijian.setOnClickListener(this);

        my_photo = (CircleImageView) view.findViewById(R.id.my_photo);

        center_data = (RelativeLayout)view .findViewById(R.id.center_data);
        center_data.setOnClickListener(this);


        if (user_Id != null && user_Id.length() > 0) {
            String User_Nick_Name = sharedPre.getString("User_Nick_Name", my_name.getText().toString());
            String user_avatar = sharedPre.getString("User_Avatar", "");
            Log.e("liu", "fragment 头像" + user_avatar);
            ImageLoader.getInstance().displayImage(user_avatar, my_photo);
            my_name.setText("" + User_Nick_Name);
            user_Integral = sharedPre.getString("User_Integral", "");
            my_integral.setText("累计积分:"+user_Integral);

            new User_Get_User_Info().execute("");
        }
    }

    /**
     * 获取会员信息请求接口
     */
    private class User_Get_User_Info extends AsyncTask<String, Integer, Object> {


        @Override
        protected String doInBackground(String... params) {
            return HttpGet_User_Info();
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            String reString = result.toString();

            try {
                JSONObject jsonObject = new JSONObject(reString);
                int status = jsonObject.getInt("Status");
                if (status == 1) {
                    String json = jsonObject.getString("Result");
                    userInfoModel = JSON.parseObject(json, UserInfoModel.class);

                    sendHandlerMessage(1,"");
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("liu", "接口异常输出-----" + e);
            }

        }
    }
    //刷新UI使用handler
    private void sendHandlerMessage(int what, Object obj) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = obj;
        handler.sendMessage(msg);
    }
    Handler handler = new Handler() {
        @SuppressLint({"HandlerLeak", "ShowToast"})
        public void handleMessage(Message msg) {
            Log.e("come in", "");
            switch (msg.what) {
                case 1:
                    ImageLoader.getInstance().displayImage(Config.Image_URL+userInfoModel.getUser_Avatar(), my_photo);
                    my_name.setText("" + userInfoModel.getUser_Nick_Name());
                    my_integral.setText("累计积分:"+userInfoModel.getUser_Integral());
                    editor.putString("User_Sex", userInfoModel.getUser_Sex());
                    editor.commit();

                    break;

                default:

            }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.center_data:
                Intent centerdata = new Intent(getContext(), CenterdataActivity.class);
                startActivityForResult(centerdata, RequestCode.To_User_Photo);
                break;
            case  R.id.my_lingquan:
            startActivity(new Intent(getContext(), GuYuActivity.class));
            break;
            case  R.id.shezhi:
                startActivity(new Intent(getContext(), SheZhiActivity.class));break;
            case  R.id. my_kefu:
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:" + ss));
                //开启打电话的意图
                startActivity(intent);
                break;
            case  R.id.my_yijian:
                startActivity(new Intent(getContext(), JianYiActivity.class));
                break;
        }
    }
    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
    private String HttpGet_User_Info() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("User_Id", user_Id);//用户手机
        Tools tools = new Tools();
        String resString = "";
        try {
            resString = tools.doPostTokenData2(Config.BASE_URL + "Get_User_Info", map);

            Log.e("liu", "获取会员信息 Get_User_Info 接口返回值：" + resString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resString;

    }

}
