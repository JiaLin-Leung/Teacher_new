package com.tbkt.teacher.SuppleInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.teacher.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Author: DBJ
 * @Date: 2018/6/12 10:18
 * @Description:完善信息欢迎页
 *
 */
public class SuppleInfoActivity extends BaseActivity implements View.OnClickListener {
    String type;
    @Bind(R.id.top_infotxt)
    TextView top_infotxt;
    @Bind(R.id.bt_supple_info)
    Button bt_supple_info;
    @Bind(R.id.top_btnback)
    ImageView top_btnback;

    @Override
    public int setLayoutId() {
        return R.layout.activity_suppleinfo;
    }
    @Override
    public void initView() {
        ButterKnife.bind(this);
        //        type = getIntent().getStringExtra("type");
        top_infotxt.setText("完善信息");
        bt_supple_info.setOnClickListener(this);
        top_btnback.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_supple:
                Intent intent = null;
//                //syw 跳转完善姓名界面
//                if ("name".equals(type) || "all".equals(type)) {
//                    intent = new Intent(SuppleInfoActivity.this, SuppleNameActivity.class);
//                    intent.putExtra("type", type);
//                //syw 跳转完善学校信息界面
//                } else if ("class".equals(type)) {
//                    intent = new Intent(SuppleInfoActivity.this, SuppleSchoolActivity.class);
//                }
                startActivity(intent);
                break;
            case R.id.top_btnback:
                finish();
                break;
        }
    }
}
