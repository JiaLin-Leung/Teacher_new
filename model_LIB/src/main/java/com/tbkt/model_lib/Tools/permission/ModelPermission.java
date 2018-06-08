package com.tbkt.model_lib.Tools.permission;

public class ModelPermission {
    private boolean rationalNeed;//是否需要手动弹框提示授权
    private String name;//权限名称

    /**
     * 是否弹出自定义权限申请框
     *
     * @return true：弹出自定义框；false：不再弹框
     */
    public boolean isRationalNeed() {
        return rationalNeed;
    }

    public void setRationalNeed(boolean rationalNeed) {
        this.rationalNeed = rationalNeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
