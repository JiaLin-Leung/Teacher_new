package com.tbkt.teacher.Login;

import android.content.Intent;
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
import butterknife.OnClick;

/**
 * @Author: DBJ
 * @Date: 2018/6/11 9:57
 * @Description:登陆界面
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.login_account)
    EditText phone;
    @Bind(R.id.login_pasw)
    EditText pasw;
    @Bind(R.id.login_btn)
    Button login;
    //    @Bind(R.id.cs_phone)
//    TextView kf_phone;
    @Bind(R.id.get_sms_pass)
    TextView get_sms_pass;
    @Bind(R.id.iv_clear_pasw)
    ImageView iv_clear_pasw;
    @Bind(R.id.iv_clear_phone)
    ImageView iv_clear_phone;
    //   账号
    private String accountTxt;
    //  密码
    private String passwTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
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
                    iv_clear_pasw.setVisibility(View.VISIBLE);
                } else {
                    iv_clear_pasw.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                bt_isselecter();
            }
        });

        //账号框输入监听
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.length() != 0) {
                    iv_clear_phone.setVisibility(View.VISIBLE);
                } else {
                    iv_clear_phone.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                pasw.setText("");
                bt_isselecter();
            }
        });
        iv_clear_pasw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasw.setText("");
            }
        });
        iv_clear_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.setText("");
            }
        });
    }

    /**
     * 判断按钮是否可点击
     */
    private void bt_isselecter() {
        if (!TextUtils.isEmpty(phone.getText().toString()) && !TextUtils.isEmpty(pasw.getText().toString())) {
            login.setBackgroundResource(R.drawable.login_bt_seleter);
            login.setTextColor(getResources().getColor(R.color.white));
            login.setClickable(true);
            login.setEnabled(true);
        } else {
            login.setBackgroundResource(R.drawable.login_bt_noseleter);
            login.setTextColor(getResources().getColor(R.color.bt_noseleter_text));
            login.setClickable(false);
            login.setEnabled(false);
        }
    }


    /***
     * 登录逻辑处理
     */
    public void login_logic() {
        accountTxt = phone.getText().toString().trim();
        passwTxt = pasw.getText().toString().trim();


        //获取连续错误后的登录状态  0：可登录状态  1：不可登录状态
//         String loginState = PreferencesManager.getInstance().getString("loginState", "0");
//        if (loginState.equals("1")) {
//            Long lastTime = PreferencesManager.getInstance().getLong("FailureTime", 1000);
//            Long currentTime = System.currentTimeMillis();
//            if (currentTime - lastTime < 2 * 60 * 1000) {
//                Long time = 2 * 60 * 1000 - (currentTime - lastTime);
//                int second = (int) (time / 1000);//商
//                int secondLess = (int) (time % 1000);//余数
//                if (secondLess > 500) {//余数毫秒值大于500；秒数加1
//                    second = second + 1;
//                }
//                MyToastUtils.toastText(this, "" + second + "秒后请重试！");
//                return;
//            } else {
//                loginFailureNum = 0;
//                PreferencesManager.getInstance().putString("loginState", "0");
//                PreferencesManager.getInstance().putBoolean("setNewFirstFailureTime", true);
//            }
//        }

    }

    @Override
    public void initData() {
    }

    @OnClick(R.id.get_sms_pass )
    public void toPass(){
        Intent intent = new Intent(LoginActivity.this,FindPasswordActivity.class);
        startActivity(intent);
    }

}
