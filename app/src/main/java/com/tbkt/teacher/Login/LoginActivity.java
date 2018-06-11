package com.tbkt.teacher.Login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
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
 * @Date: 2018/6/11 9:57
 * @Description:登陆界面
 *
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.login_account)
    AutoCompleteTextView phone;
    @Bind(R.id.login_pasw)
    EditText pasw;
    @Bind(R.id.login_btn)
    Button login;
    @Bind(R.id.cs_phone)
    TextView kf_phone;
    @Bind(R.id.get_sms_pass)
    TextView get_sms_pass;
    @Bind(R.id.iv_clear)
    ImageView iv_clear;
    @Bind(R.id.iv_clear1)
    ImageView iv_clear1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }
    @Override
    public void initView() {
        initTextChanged();
    }

    private void initTextChanged() {
        //密码框监听
        pasw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    iv_clear.setVisibility(View.VISIBLE);
                } else {
                    iv_clear.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

            //账号框输入监听
        phone.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (s.length() != 0) {
                    iv_clear1.setVisibility(View.VISIBLE);
                } else {
                    iv_clear1.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                pasw.setText("");
            }
        });

    }

    @Override
    public void initData() {

    }
}
