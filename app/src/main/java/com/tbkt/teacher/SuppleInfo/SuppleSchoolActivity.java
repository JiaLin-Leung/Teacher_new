package com.tbkt.teacher.SuppleInfo;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.teacher.R;


import butterknife.Bind;

/**
 * @Author: DBJ
 * @Date: 2018/6/12 10:59
 * @param
 * @Description:完善班级
 *
 */
public class SuppleSchoolActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.top_infotxt)
    TextView top_infotxt;
    @Bind(R.id.top_btnback)
    ImageView top_btnback;
    @Bind(R.id.supple_school_bt_submit)
    Button bt_submit;
    @Bind(R.id.supple_school_tv_city)
    TextView tv_city;
    @Bind(R.id.supple_school_tv_school)
    TextView tv_school;
    @Bind(R.id.supple_school_tv_class)
    TextView tv_class;
    @Bind(R.id.supple_school_ll_city)
    LinearLayout ll_city;
    @Bind(R.id.supple_school_ll_school)
    LinearLayout ll_school;
    @Bind(R.id.supple_school_ll_class)
    LinearLayout ll_class;
    int buMenId, nianJiId, banJiId;
    String schoolName;
    String youzhengbianma="";


    @Override
    public int setLayoutId() {
        return R.layout.activity_school;
    }


    public void initView() {
        schoolName = "选择学校";
        //syw 清除学校区域
//        PreferencesManager.getInstance().putString("youzhengbianma", "");
//        PreferencesManager.getInstance().putString("school_name", "选择学校");
//        PreferencesManager.getInstance().putInt("school_id", 0);


        initListener();
        tv_city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
//                syw修改文字内容
                tv_school.setText("选择学校");
                tv_class.setText("选择班级");
//                syw清空学校信息
//                PreferencesManager.getInstance().putInt("school_id", 0);
//                PreferencesManager.getInstance().putString("school_name", "选择学校");
//                syw清空班级信息
                buMenId = 0;
                nianJiId = 0;
                banJiId = 0;
            }
        });

        tv_school.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tv_class.setText("选择班级");
//              syw清空班级信息
                buMenId = 0;
                nianJiId = 0;
                banJiId = 0;
            }
        });
        top_infotxt.setText("完善学校信息");
    }

    @Override
    public void initData() {

    }

    private void initListener() {
        ll_city.setOnClickListener(this);
        ll_school.setOnClickListener(this);
        ll_class.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        top_btnback.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        schoolName = PreferencesManager.getInstance().getString("school_name", "选择学校");
        tv_school.setText(schoolName);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.supple_school_ll_city:
                getAreaDate();
                break;
            case R.id.supple_school_ll_school:
//                youzhengbianma = PreferencesManager.getInstance().getString("youzhengbianma", "");
//                if ("".equals(youzhengbianma)) {
//                    MyToastUtils.toastText(SuppleSchoolActivity.this, "请先选择地市");
//                    return;
//                }
//                Intent intent = new Intent(this, ChoiceSchoolActivity.class);
//                startActivity(intent);
                break;

            case R.id.supple_school_ll_class:
                getClassData();
                break;

            case R.id.supple_school_bt_submit:
//                youzhengbianma = PreferencesManager.getInstance().getString("youzhengbianma", "");
//                if ("".equals(youzhengbianma)){
//                    MyToastUtils.toastText(SuppleSchoolActivity.this, "请先选择地市");
//                }else if ("选择学校".equals(schoolName)){
//                    MyToastUtils.toastText(SuppleSchoolActivity.this, "请先选择学校");
//                }else if (buMenId == 0 || nianJiId == 0) {
//                    MyToastUtils.toastText(this, "请先选择班级");
//                } else {
//                    submitSchoolInfo(buMenId, nianJiId, banJiId);
//                }
                break;

            case R.id.top_btnback:
                finish();
                break;
        }
    }

    /**
     * 获取县区信息
     */
    private void getAreaDate() {

    }

    /**
     * 获取班级信息
     */
    private void getClassData() {


    }

    public void submitSchoolInfo(int buMenId, int nianJiId, int banJiId) {


    }
}
