package com.tbkt.teacher.SuppleInfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbkt.teacher.R;
import com.tbkt.teacher.SuppleInfo.ChoiceSchoolActivity;
import com.tbkt.teacher.SuppleInfo.bean.SchoolBean;

import java.util.List;


/**
 * @Author: DBJ
 * @Date: 2018/6/12 13:36
 * @Description:学校选择列表
 *
 */
public class SchoolAdapter extends BaseAdapter {
    List<SchoolBean.DataBean> list;
    Context context;
    private LayoutInflater mInflater;
    //选中标识
    private int index=-1;
    public SchoolAdapter(Context context, List<SchoolBean.DataBean> list) {
        this.context=context;
        this.list=list;
        mInflater = LayoutInflater.from(context);
    }
    public int index(int index){
        return this.index=index;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate( R.layout.item_school, null);
            holder.tv_school_name = (TextView) convertView.findViewById(R.id.tv_school_name);
            holder.item_school=convertView.findViewById(R.id.item_school);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder {
        CheckBox checkBox;
        TextView tv_school_name;
        LinearLayout item_school;
    }
}
