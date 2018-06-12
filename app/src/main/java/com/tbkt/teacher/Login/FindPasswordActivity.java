package com.tbkt.teacher.Login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.teacher.R;

/**
 * create by: LiuShiQi 刘士奇
 * create date: 2018/6/12
 * description: 找回密码页面
 */
public class FindPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText etPhone;
    private EditText etCode;
    private Button btnGetCode;
    private Chronometer chronometer;
    private int countdownNum = 0;
    private boolean phone = false;
    private boolean code = false;
    private Button btnFindPassword;

    @Override
    public int setLayoutId() {
        return R.layout.activity_find_password;
    }

    @Override
    public void initView() {
        etPhone = findViewById(R.id.et_phone);
        etCode = findViewById(R.id.et_code);
        btnGetCode = findViewById(R.id.btn_get_code);
        chronometer = findViewById(R.id.chronometer);
        btnFindPassword = findViewById(R.id.btn_find_password);
        btnFindPassword.setOnClickListener(this);
        btnGetCode.setOnClickListener(this);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                countDownTime();
            }
        });
        setTitle("找回密码");
    }

    @Override
    public void initData() {
        //设置手机号的输入监听
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.length() > 0;
                if (phone) {
                    btnGetCode.setClickable(true);
                    btnGetCode.setEnabled(true);
                    btnGetCode.setBackground(getResources().getDrawable(R.drawable.bg_btn_code));
                    btnGetCode.setTextColor(getResources().getColor(R.color.white));
                    if (code) {
                        btnFindPassword.setClickable(true);
                        btnFindPassword.setEnabled(true);
                        btnFindPassword.setBackground(getResources().getDrawable(R.drawable.login_bt_seleter));
                        btnFindPassword.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        btnFindPassword.setClickable(false);
                        btnFindPassword.setEnabled(false);
                        btnFindPassword.setBackground(getResources().getDrawable(R.drawable.login_bt_noseleter));
                        btnFindPassword.setTextColor(getResources().getColor(R.color.text_hint_color));
                    }
                } else {
                    btnGetCode.setClickable(false);
                    btnGetCode.setEnabled(false);
                    btnGetCode.setBackground(getResources().getDrawable(R.drawable.login_bt_noseleter));
                    btnGetCode.setTextColor(getResources().getColor(R.color.text_hint_color));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //设置验证码的输入监听
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code = s.length() > 0;
                if (phone && code) {
                    btnFindPassword.setClickable(true);
                    btnFindPassword.setEnabled(true);
                    btnFindPassword.setBackground(getResources().getDrawable(R.drawable.login_bt_seleter));
                    btnFindPassword.setTextColor(getResources().getColor(R.color.white));
                } else {
                    btnFindPassword.setClickable(false);
                    btnFindPassword.setEnabled(false);
                    btnFindPassword.setBackground(getResources().getDrawable(R.drawable.login_bt_noseleter));
                    btnFindPassword.setTextColor(getResources().getColor(R.color.text_hint_color));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void findPassWord() {
        String phoneString = etPhone.getText().toString().trim();
        String codeString = etCode.getText().toString().trim();
        if (phoneString.equals("10086") && codeString.equals("10086")) {
            showPasswordDialog(this);
        } else {
            showShortToast("验证码错误");
        }
    }

    /**
     * 获取验证码的时间开始递减
     */
    private void countDownTime() {
        if (countdownNum <= 0) {
            resetSendBtn();
            return;
        }
        String text = countdownNum + "秒后重发";
        btnGetCode.setText(text);
        btnGetCode.setBackground(getResources().getDrawable(R.drawable.login_bt_noseleter));
        btnGetCode.setTextColor(getResources().getColor(R.color.text_hint_color));
        countdownNum--;
    }

    /**
     * 重置获取验证码按钮
     */
    private void resetSendBtn() {
        btnGetCode.setText("获取验证码");
        btnGetCode.setEnabled(true);
        chronometer.stop();
    }

    /**
     * 倒计时
     */
    private void doCountdown() {
        countdownNum = 60;
        btnGetCode.setEnabled(false);
        btnGetCode.setFocusable(false);
        chronometer.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_find_password:
                findPassWord();
                break;
            case R.id.btn_get_code:
                String phone = etPhone.getText().toString().trim();
                if (phone.equals("")) {
                    showShortToast("手机号不能为空");
                } else {
                    doCountdown();
                }
                break;
        }
    }

    /**
     * 对话框提示用户找回密码成功信息
     * @param context 上下文环境
     */
    public static void showPasswordDialog(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context, com.tbkt.model_lib.R.style.MyDialog).create();
        dialog.show();
        Window window = dialog.getWindow();
        assert window != null;
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        View view = View.inflate(context, com.tbkt.model_lib.R.layout.layout_password_dialog, null);
        window.setContentView(view);
        TextView tv3 = view.findViewById(com.tbkt.model_lib.R.id.ok);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
