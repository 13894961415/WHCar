package com.example.whcar;

import android.app.Application;

import com.example.whcar.untils.ImageLoaderKit;

public class IApplication extends Application {

    // 图片加载、缓存与管理组件
    private static ImageLoaderKit imageLoaderKit;
    @Override
    public void onCreate() {
        super.onCreate();
        imageLoaderKit = new ImageLoaderKit(this, null);


    }

}
