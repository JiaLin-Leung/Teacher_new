package com.tbkt.model_lib.Tools;

import android.util.Log;

public class LogUtils {
       /**
        * @Author: DBJ
        * @Date: 2018/6/8 11:16

        * @Description:  Toast提示工具类
        *
        */
    public static void showLog(String message,String information){
        Log.e("tbkt"+message,information);
    }
}
