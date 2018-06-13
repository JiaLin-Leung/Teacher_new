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
import butterknife.OnClick;

/**
 * @Author: DBJ
 * @Date: 2018/6/12 10:18
 * @Description:完善信息欢迎页
 *
 */
public class SuppleInfoActivity extends BaseActivity  {
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
        onClick();
    }

    @Override
    public void initData() {

    }

    public void onClick() {
        bt_supple_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(SuppleInfoActivity.this, SuppleNameActivity.class);
                startActivity(intent);
            }
        });
        top_btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
