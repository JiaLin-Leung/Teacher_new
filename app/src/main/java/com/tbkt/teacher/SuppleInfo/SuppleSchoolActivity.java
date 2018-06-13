package com.tbkt.teacher.SuppleInfo;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.model_lib.Tools.Util;
import com.tbkt.model_lib.Tools.picker.OptionsPickerView;
import com.tbkt.teacher.R;
import com.tbkt.teacher.SuppleInfo.bean.ProvinceBean;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author: DBJ
 * @Date: 2018/6/12 10:59
 * @param
 * @Description:完善班级
 *
 */
public class SuppleSchoolActivity extends BaseActivity {
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
    //城市选择
    private int selectPosition1,selectPosition2;
    private OptionsPickerView pvOptions;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_school;
    }


    public void initView() {
        ButterKnife.bind(this);
        schoolName = "选择学校";
        //syw 清除学校区域
//        PreferencesManager.getInstance().putString("youzhengbianma", "");
//        PreferencesManager.getInstance().putString("school_name", "选择学校");
//        PreferencesManager.getInstance().putInt("school_id", 0);



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
        //选项1
        options1Items.add(new ProvinceBean(0,"郑州市","001","00100"));
        options1Items.add(new ProvinceBean(1,"许昌市","002","00200"));
        options1Items.add(new ProvinceBean(2,"平顶山市","003","00300"));

        //选项2
        ArrayList<String> options2Items_01=new ArrayList<>();
        options2Items_01.add("金水区");
        options2Items_01.add("惠济区");
        options2Items_01.add("二七区");
        options2Items_01.add("高新区");
        options2Items_01.add("经开区");
        options2Items_01.add("郑东新区");
        ArrayList<String> options2Items_02=new ArrayList<>();
        options2Items_02.add("禹州市");
        options2Items_02.add("魏都区");
        options2Items_02.add("襄县市");
        options2Items_02.add("长葛市");
        options2Items_02.add("许昌县");
        ArrayList<String> options2Items_03=new ArrayList<>();
        options2Items_03.add("新华区");
        options2Items_03.add("滨河区");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        schoolName = PreferencesManager.getInstance().getString("school_name", "选择学校");
        tv_school.setText(schoolName);
    }


    @OnClick({R.id.supple_school_ll_city,R.id.supple_school_ll_school,R.id.supple_school_ll_class,R.id.top_btnback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.supple_school_ll_city:
                getAreaDate();
                break;
            case R.id.supple_school_ll_school:
//                youzhengbianma = PreferencesManager.getInstance().getString("youzhengbianma", "");
                if ("".equals(tv_city.getText().toString())) {
                    showCenterToastCenter("请先选择地市");
                    return;
                }
                Intent intent = new Intent(this, ChoiceSchoolActivity.class);
                startActivity(intent);
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
        //选项选择器
        pvOptions = new OptionsPickerView(this);

        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items,true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
//        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, false, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(0,0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2);
                selectPosition1 = options1;
                selectPosition2 = option2;
                tv_city.setText(tx);
            }
        });
        pvOptions.setSelectOptions(selectPosition1, selectPosition2);
        pvOptions.show();

    }

    /**
     * 获取班级信息
     */
    private void getClassData() {


    }

    public void submitSchoolInfo(int buMenId, int nianJiId, int banJiId) {

    }
}
