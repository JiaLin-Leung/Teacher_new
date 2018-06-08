package com.tbkt.model_lib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tbkt.model_lib.Tools.LogUtils;
import com.tbkt.model_lib.Tools.Util;

public class MainActivity_LIB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__lib);

        int bbb = Util.getAppVersionCode(MainActivity_LIB.this,"com.tbkt.model_lib");
        LogUtils.showLog("资源版本号---",bbb+"");
    }
}
