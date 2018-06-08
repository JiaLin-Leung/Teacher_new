package com.tbkt.model_lib.Base;

import android.app.Application;
import android.os.Environment;

import com.tbkt.model_lib.Tools.Util;

public class BaseApplocation extends Application {
    private static BaseApplocation sInstance;
    /**
     * 应用程序缓存根目录
     */
    public static String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/tbkt/";
    public static BaseApplocation getInstance() {
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
        Util.init(this);
    }
}