package com.tbkt.teacher.SuppleInfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.teacher.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;

/**
 * Created by song on 2017/1/13 0013.  选择学校
 */
public class ChoiceSchoolActivity extends BaseActivity {

    EditText et_search;
    ListView lv_school;
    long lastTime;
    long currTime;
//    List<SchoolBean.DataBean> schools;
    ImageView iv_clear;
    @Bind(R.id.tv_noschool)
    TextView tv_noschool;
    @Bind(R.id.top_infotxt)
    TextView top_infotxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_choice_school;
    }

    public void initView() {
        top_infotxt.setText("选择学校");
        ImageView top_btnback = (ImageView) findViewById(R.id.top_btnback);
        top_btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        et_search = (EditText) findViewById(R.id.et_search);
        lv_school = (ListView) findViewById(R.id.lv_school);
        iv_clear = (ImageView) findViewById(R.id.iv_clear);
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
