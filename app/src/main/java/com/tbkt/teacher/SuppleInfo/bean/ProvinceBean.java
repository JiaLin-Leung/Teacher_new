package com.tbkt.teacher.SuppleInfo.bean;

/**
 * Created by zhf on 2018/6/13.
 */
public class ProvinceBean {
    private long id;
    private String name;
    private String description;
    private String others;

    public  ProvinceBean(long id, String name, String description, String others){
        this.id = id;
        this.name = name;
        this.description = description;
        this.others = others;
    }

    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return name;
    }
}
