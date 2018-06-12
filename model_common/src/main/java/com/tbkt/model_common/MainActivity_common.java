package com.tbkt.model_common;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.model_lib.Tools.Content;

@Route(path = Content.GOTO_COMMON)
public class MainActivity_common extends BaseActivity {

    @Override
    public int setLayoutId() {
        return R.layout.activity_main_common;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
