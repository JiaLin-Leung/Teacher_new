package com.tbkt.module_common;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.model_lib.Tools.Content;

/**
 * @Author: LiuShiQi 刘士奇
 * @Date: 2018/6/12
 * @Description:
 */
@Route(path = Content.GOTO_COMMON)
public class MainActivity extends BaseActivity{
    @Override
    public int setLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
