package com.tbkt.teacher.SuppleInfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    public SchoolAdapter(Context context, List<SchoolBean.DataBean> list) {
        this.context=context;
        this.list=list;
        mInflater = LayoutInflater.from(context);
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder {
        TextView tv_school_name;
    }
}
