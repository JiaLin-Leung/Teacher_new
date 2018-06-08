package com.tbkt.model_lib.Tools.permission;


public interface PermissionResultCallBack {

    /**
     * 权限申请成功回调
     */
    public void onBasicPermissionSuccess();

    /**
     * 用户拒绝授权回调
     */
    public void onBasicPermissionFailed();

    /**
     * 用户禁止且勾选“不再提示”选择项的权限回调
     */
    public void onBasicPermissionFailedNeedRational();
}
