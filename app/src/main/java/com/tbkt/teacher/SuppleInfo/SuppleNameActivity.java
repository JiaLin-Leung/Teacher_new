package com.tbkt.teacher.SuppleInfo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.teacher.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Author: DBJ
 * @Date: 2018/6/11 18:02
 * @Description:完善信息界面
 *
 */
public class SuppleNameActivity extends BaseActivity implements View.OnClickListener {
    //姓名
    @Bind(R.id.et_name)
    EditText et_name;
    String newName;
    String type;
    //顶部名字信息
    @Bind(R.id.top_infotxt)
    TextView top_infotxt;
    //顶部返回按钮
    @Bind(R.id.top_btnback)
    ImageView top_btnback;
    //确定按钮
    @Bind(R.id.bt_supple)
    Button bt_supple;


    @Override
    public int setLayoutId() {
        return R.layout.activity_supplename;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        top_infotxt.setText(R.string.supplenameinfo+"");
        top_btnback.setOnClickListener(this);
        bt_supple.setOnClickListener(this);
        initTextChanged();
    }
    //处理按钮监听
    private void initTextChanged() {
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //按钮可点击状态
                if (!TextUtils.isEmpty(et_name.getText().toString())) {
                    bt_supple.setBackgroundResource(R.drawable.login_bt_seleter);
                    bt_supple.setTextColor(getResources().getColor(R.color.white));
                    bt_supple.setClickable(true);
                    bt_supple.setEnabled(true);
                }else {
                    //按钮不可点击状态
                    bt_supple.setBackgroundResource(R.drawable.login_bt_noseleter);
                    bt_supple.setTextColor(getResources().getColor(R.color.bt_noseleter_text));
                    bt_supple.setClickable(false);
                    bt_supple.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void initData() {
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_btnback:
                finish();
                break;
            case R.id.bt_supple:
                newName = et_name.getText().toString().trim();
                if (newName.length() >= 2 && newName.length() <= 5) {
                    updateName();
                }else if (newName.isEmpty()){
                    showShortToast(R.string.blank_name+"");
                }else {
                    showShortToast(R.string.noblank_name+"");
                }
                break;
        }
    }
    private void updateName() {
    }
}
