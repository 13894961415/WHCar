package com.example.whcar.My.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.whcar.Home.utils.CircleImageView;
import com.example.whcar.MainActivity;
import com.example.whcar.R;
import com.example.whcar.Sell.untils.Config;
import com.example.whcar.Sell.untils.Tools;
import com.example.whcar.untils.FileImageUpload;
import com.example.whcar.untils.RequestCode;
import com.example.whcar.untils.ResultCode;
import com.example.whcar.untils.WeiboDialogUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wt on 2018/3/24.
 */

public class CenterdataActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView centerBlack;
    private RelativeLayout centerPhoto;
    private RelativeLayout centerName;
    private RelativeLayout centerGender;
    private RelativeLayout centerId;
    private RelativeLayout centerShuoming;
    private RelativeLayout centerAddress;
    private RelativeLayout centerSafe;
    private View inflate;
    private TextView genderMan;
    private TextView genderWoman;
    private TextView genderCancel,center_Nname,user_gender;

    private Button btn_picture, btn_photo, btn_cancle;
    private Bitmap head;// 头像Bitmap
    private String zhubotype = "0";
    private String pathString = "";
    private String textString,typeString,sexString;
    private TextView bang_tel;//绑定手机
    private String userApprove;
    private String User_Actual_Name;
    private String actualName;
    private String actualNumber;
    private TextView user_yes_or_no,center_quit,useridtextview;

    private static String path = "/sdcard/myHead/";// sd路径
    private CircleImageView user_Avatar;
    private Dialog dialog;
    private void showLoading() {
        dialog = WeiboDialogUtils.createLoadingDialog(CenterdataActivity.this, "加载中...");
    }
    private void closeLoading() {
        WeiboDialogUtils.closeDialog(dialog);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_data);

        checkPublishPermission();//6.0相机获取权限
        myPermission();//获取读取权限

        init();
    }

    private void init() {
        initView();

    }

    private void initView() {
        centerBlack=(ImageView)findViewById(R.id.center_black);
        centerPhoto=(RelativeLayout)findViewById(R.id.center_photo);
        centerName=(RelativeLayout)findViewById(R.id.center_name);
        centerGender=(RelativeLayout)findViewById(R.id.center_gender);
        centerId=(RelativeLayout)findViewById(R.id.center_id);
        centerShuoming=(RelativeLayout)findViewById(R.id.center_shuoming);
        centerAddress=(RelativeLayout)findViewById(R.id.center_address);

        center_quit =(TextView)findViewById(R.id.center_quit);
        center_quit.setOnClickListener(this);
        useridtextview = (TextView)findViewById(R.id.useridtextview);
        user_gender =(TextView)findViewById(R.id.user_gender);
        user_Avatar = (CircleImageView) findViewById(R.id.user_photo);//用户头像
        SharedPreferences sharedPre = CenterdataActivity.this.getSharedPreferences("keys", Context.MODE_PRIVATE);
        String user_avatar = sharedPre.getString("User_Avatar", "");

        ImageLoader.getInstance().displayImage(user_avatar, user_Avatar);
        Log.e("centerActivity 头像", "" + user_avatar);
        String userid = sharedPre.getString("User_Id","");
        useridtextview.setText(""+userid);
        center_Nname = (TextView) findViewById(R.id.user_nick);//昵称
        String User_Nick_Name = sharedPre.getString("User_Nick_Name","");
        center_Nname.setText(User_Nick_Name);

        String sexString = sharedPre.getString("User_Sex","");
        if (sexString.equals("")||null==sexString){
            user_gender.setText("未设置");
        }else {
            if (sexString.equals("1")){
                user_gender.setText("男");
            }else {
                user_gender.setText("女");
            }
        }

        centerBlack.setOnClickListener(this);
        centerAddress.setOnClickListener(this);
        centerGender.setOnClickListener(this);
        centerId.setOnClickListener(this);
        centerName.setOnClickListener(this);
        centerPhoto.setOnClickListener(this);

        centerShuoming.setOnClickListener(this);

    }


    public boolean isPermission() {


        PackageManager pm = getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.CAMERA", "com.wt.beiplayer"));
        if (permission) {
            Log.e("liu", "有这个权限");
            return true;
        } else {
            Log.e("liu", "木有这个权限");
            return false;
        }
    }
    private void preperImageLoader() {


        /******************* 配置ImageLoder ***********************************************/
        File cacheDir =
                StorageUtils.getOwnCacheDirectory(this,
                        "imageloader/Cache");

        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(this)
                        .denyCacheImageMultipleSizesInMemory()
                        .discCache(new UnlimitedDiskCache(cacheDir))// 自定义缓存路径
                        .build();// 开始构建

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc()
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();

        ImageLoader.getInstance().init(config);// 全局初始化此配置
        /*********************************************************************************/
    }
    /**
     * 上传图片接口end
     **/


    private void showDialog() {
        View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);
        final Dialog dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        btn_picture = (Button) window.findViewById(R.id.btn_picture);
        btn_photo = (Button) window.findViewById(R.id.btn_photo);
        btn_cancle = (Button) window.findViewById(R.id.btn_cancle);

        btn_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    /**
     * 6.0权限处理
     **/
    private boolean bPermission = false;
    private final int WRITE_PERMISSION_REQ_CODE = 100;

    private boolean checkPublishPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(CenterdataActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(CenterdataActivity.this, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }

            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(CenterdataActivity.this, Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(CenterdataActivity.this,
                        (String[]) permissions.toArray(new String[0]),
                        WRITE_PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }
    /**
     * 调用系统的裁剪
     *
     * @param uri
     */

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public void myPermission() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    if (zhubotype.equals("1")) {
                        cropPhotofengmian(data.getData());// 裁剪图片
                    } else {
                        cropPhoto(data.getData());// 裁剪图片
                    }
                }
                break;

            case 2:

                if (resultCode == RESULT_OK) {

                    if (zhubotype.equals("1")) {
                        File temp = new File(Environment.getExternalStorageDirectory() + "/zbhead.jpg");
                        cropPhotofengmian(Uri.fromFile(temp));// 裁剪图片
                    } else {
                        File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                        cropPhoto(Uri.fromFile(temp));// 裁剪图片
                    }
                }
                break;

            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        if (zhubotype.equals("1")) {
                            pathString = setPicToView(head);// 保存在SD卡中
                            user_Avatar.setImageBitmap(toRoundBitmap(head));// 用ImageView显示出来

                        } else {
                            pathString = setPicToView(head);// 保存在SD卡中
                            user_Avatar.setImageBitmap(toRoundBitmap(head));// 用ImageView显示出来
                            showLoading();
                            new UpdataImageTask().execute("");

                        }
//                        pathString = setPicToView(head);// 保存在SD卡中
//                        user_Avatar.setImageBitmap(toRoundBitmap(head));// 用ImageView显示出来
//                        new UpdataImageTask().execute("");

                    }
                }
                break;

            case RequestCode.TO_User_Nick_Name:
                if (resultCode == ResultCode.SUCCESS) {
                    textString = data.getExtras().getString("text");
                    center_Nname.setText("" + textString);

                    Log.e("修改内容回传", "" + data.getExtras().getString("text"));
                    showLoading();
                    new User_UpdateTask().execute("");

                }
                break;

            case RequestCode.To_User_Name:
                if (resultCode == ResultCode.SUCCESS) {
                    actualName = data.getExtras().getString("actual_name");
                    actualNumber = data.getExtras().getString("actual_number");

                    Log.e("修改内容回传", "" + data.getExtras().getString("actual_name"));
                    showLoading();
                    new User_UpdateTask().execute("");
                }
                break;


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 上传图片接口
     **/
    private class UpdataImageTask extends AsyncTask<String, Integer, Object> {

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            SharedPreferences sharedPre = CenterdataActivity.this.getSharedPreferences("keys", Context.MODE_PRIVATE);
            String user_id = sharedPre.getString("User_Id", "");
            File file = new File(pathString);

            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("User_Id", user_id);
            String RequestURL = Config.BASE_URL + "User_Update?User_Id=" + user_id;
            return FileImageUpload.uploadFile(file, hashMap, RequestURL, "User_Avatar");

        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            String reString = result.toString();
            try {
                JSONObject jsonObject = new JSONObject(reString);
                int status = jsonObject.getInt("Status");
                if (status == 1) {
                    JSONObject resJson = jsonObject.getJSONObject("Result");
                    String imageurl = Config.Image_URL + resJson.getString("User_Avatar");
                    SharedPreferences sharedPre2 = CenterdataActivity.this.getSharedPreferences("keys", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sharedPre2.edit();
                    editor2.putString("User_Avatar", "" + imageurl);
                    editor2.commit();
                    ImageLoader.getInstance().displayImage(imageurl, user_Avatar);
                    Toast.makeText(CenterdataActivity.this, "上传头像成功", Toast.LENGTH_SHORT).show();
                } else {
                    String resmsg = jsonObject.getString("Result");
                    Toast.makeText(CenterdataActivity.this, resmsg, Toast.LENGTH_SHORT).show();
                }

                Log.e("开始解析数据", "" + reString);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("头像接口异常输出-----", "" + e);
            }
            closeLoading();

        }
    }


    /**
     * 修改用户信息请求接口
     */
    private class User_UpdateTask extends AsyncTask<String, Integer, Object> {


        @Override
        protected String doInBackground(String... params) {
            return HttpUser_Update();
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            String reString = result.toString();

            try {
                JSONObject jsonObject = new JSONObject(reString);
                int status = jsonObject.getInt("Status");
                if (status == 1) {
                    SharedPreferences sharedPre2 = CenterdataActivity.this.getSharedPreferences("keys", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sharedPre2.edit();

                    if (typeString.equals("User_number")) {
                        editor2.putString("Actual_Name", "" + actualName);
                        editor2.putString("User_Number", "" + actualNumber);
                        editor2.putString("User_Approve", "1");

                    } else {
                        editor2.putString(typeString, "" + textString);
                    }
                    editor2.commit();

                    Toast.makeText(CenterdataActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

                } else {

                    String resmsg = jsonObject.getString("Result");
                    Toast.makeText(CenterdataActivity.this, resmsg, Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("接口异常输出-----", "" + e);
            }
            closeLoading();
        }
    }

    private String HttpUser_Update() {
        SharedPreferences sharedPre = CenterdataActivity.this.getSharedPreferences("keys", Context.MODE_PRIVATE);
        String userid = sharedPre.getString("User_Id", "");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("User_Id", "" + userid);//用户id
        map.put(typeString, textString);//修改类型
//        if (typeString.equals("User_number")) {
//            map.put("Actual_Name", actualName);
//            map.put("User_number", actualNumber);
//        } else {
//
//        }


        Tools tools = new Tools();
        String resString = "";
        try {
            resString = tools.doPostTokenData2(Config.BASE_URL + "User_Update", map);

            Log.e("User_Update接口返回值", "" + resString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resString;

    }


    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);//150
        intent.putExtra("outputY", 200);//150
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    public void cropPhotofengmian(Uri uri) {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.e("获取屏幕宽高", "Density is " + displayMetrics.density + " densityDpi is " + displayMetrics.densityDpi + " height: " + displayMetrics.heightPixels +
                " width: " + displayMetrics.widthPixels);

        WindowManager wm = getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Log.e("获取屏幕宽高", width + "---" + height);

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", width);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private String setPicToView(Bitmap mBitmap) {


        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return "";
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    /**
     * 把bitmap转成圆形
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r = 0;
        // 取最短边做边长
        if (width < height) {
            r = width;
        } else {
            r = height;
        }
        // 构建一个bitmap
        Bitmap backgroundBm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBm);
        Paint p = new Paint();
        // 设置边缘光滑，去掉锯齿
        p.setAntiAlias(true);
        RectF rect = new RectF(0, 0, r, r);
        // 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        // 且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r / 2, r / 2, p);
        // 设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, p);
        return backgroundBm;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.center_black:
                Intent intentPhoto = getIntent();
                setResult(ResultCode.SUCCESS, intentPhoto);
                finish();
                break;
            case R.id.center_photo:
                if (isPermission()) {

                    showDialog();
                } else {
                    checkPublishPermission();//6.0相机获取权限
                    myPermission();//获取读取权限
                    Toast.makeText(CenterdataActivity.this, "请设置相机权限！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.center_name:
                Intent intentNname = new Intent(this, ReviseNameActivity.class);
                typeString = "User_Nick_Name";
                startActivityForResult(intentNname, RequestCode.TO_User_Nick_Name);
                break;
            case R.id.center_gender://修改性别字段
                typeString = "Sex";
                bottomDialog();
                break;

            case R.id.center_id:
                break;
            case R.id.center_shuoming://修改密码
                startActivity(new Intent(this, RevisePasswordActivity.class));

                break;

            case R.id.center_quit:
                UserExit();
                break;


            case R.id.gender_woman:
                textString="2";
                showLoading();
                new User_UpdateTask().execute("");
                dialog.dismiss();
                break;
            case R.id.gender_cancel:
                typeString = "";
                dialog.dismiss();
                break;

        }
    }
    /**
     * 退出登录
     */
    private void UserExit() {
        SharedPreferences sharedPre2 = this.getSharedPreferences("keys", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor2 = sharedPre2.edit();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("温馨提示"); //设置标题
        builder.setMessage("是否确认退出?"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可@mipmap/ic_launcher
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                editor2.remove("User_Id");
                editor2.commit();
//                Intent intent = new Intent(CenterActivity.this, LoginOrRegister.class);
//                startActivity(intent);
                Intent intent = new Intent(CenterdataActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }
    private void bottomDialog() {
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_layout, null);
        //初始化控件
        genderMan = (TextView) inflate.findViewById(R.id.gender_man);
        genderWoman = (TextView) inflate.findViewById(R.id.gender_woman);
        genderCancel = (TextView) inflate.findViewById(R.id.gender_cancel);
        genderMan.setOnClickListener(this);
        genderWoman.setOnClickListener(this);
        genderCancel.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            Intent intent2 = getIntent();
            setResult(ResultCode.SUCCESS, intent2);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);


    }
}
