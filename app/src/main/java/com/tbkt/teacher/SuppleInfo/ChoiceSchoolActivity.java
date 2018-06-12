package com.tbkt.teacher.SuppleInfo;


import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.teacher.R;
import com.tbkt.teacher.SuppleInfo.bean.SchoolBean;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @Author: DBJ
 * @Date: 2018/6/12 11:43
 * @Description:选择学校
 *
 */
public class ChoiceSchoolActivity extends BaseActivity {
    @Bind(R.id.et_search)
    EditText et_search;
    @Bind(R.id.lv_school)
    ListView lv_school;
    @Bind(R.id.iv_clear)
    ImageView iv_clear;
    @Bind(R.id.tv_noschool)
    TextView tv_noschool;
    @Bind(R.id.top_infotxt)
    TextView top_infotxt;
    @Bind(R.id.top_btnback)
    ImageView top_btnback;
    long lastTime;
    long currTime;
    List<SchoolBean.DataBean> schools;

    @Override
    public int setLayoutId() {
        return R.layout.activity_choice_school;
    }

    public void initView() {
        top_infotxt.setText(R.string.selecter_school+"");
        top_btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if ("".equals(charSequence.toString())) {
                    iv_clear.setVisibility(View.INVISIBLE);
                } else {
                    iv_clear.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                lastTime = System.currentTimeMillis();
                Message message = new Message();
                message.obj = editable.toString().trim();
                message.what = 1;
                handler.sendMessageDelayed(message, 500);
            }
        });

        lv_school.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        //开始的时候，没有关键字，填充列表
        searchSchool();
    }
    @Override
    public void initData() {
        schools=new ArrayList<>();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    currTime = System.currentTimeMillis();
                    if (currTime - lastTime > 500) {
                        searchSchool();
                    }
                    break;
            }
        }
    };
    private void searchSchool() {
    }




}
