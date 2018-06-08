package com.tbkt.teacher.application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tbkt.model_lib.Base.BaseApplocation;
import com.tbkt.model_lib.BuildConfig;

/**
 * Created by zhf on 2018/6/6.
 */
public class myApplication extends BaseApplocation {
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            ARouter.openLog();//打印日志
            ARouter.openDebug();//开启调试模式（如果在InstantRun模式下运行，必须开启调试模式！线上版本选哦关闭，否则有安全风险）
        }
        ARouter.init(this);
    }
}
