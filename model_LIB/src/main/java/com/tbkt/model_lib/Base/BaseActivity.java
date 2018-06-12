package com.tbkt.model_lib.Base;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhotoFragmentActivity;
import com.tbkt.model_lib.R;
import com.tbkt.model_lib.Tools.permission.EasyPermission;
import com.tbkt.model_lib.Tools.permission.PermissionEvent;
import com.tbkt.model_lib.Tools.permission.PermissionResultCallBack;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity extends TakePhotoFragmentActivity implements PermissionResultCallBack {

    public static final int REQUEST_CODE_CAMERA = 1000101;
    public int permissionState;
    private String permissionTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());

        initView();
        initData();
    }

    /**
     * 布局文件引入方法
     * @return 布局资源
     */
    public abstract int setLayoutId();

    /**
     * 初始化控件方法
     */
    public abstract void initView();

    /**
     * 初始化数据方法
     */
    public abstract void initData();



    /**
     * 设置标题
     *
     * @param title 标题的文本
     */
    public void setTitle(String title) {
        TextView baseTitle = (TextView) findViewById(R.id.tv_top_title);
        baseTitle.setText(title);
        initBack();
    }

    private void initBack(){
        ImageView ivBack = (ImageView) findViewById(R.id.iv_top_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 抛出返回按钮,如果点击返回需要保存东西,以便于返回的时候页面提示保存
     * @return 返回键控件
     */
    public ImageView getImageBack() {
        return (ImageView) findViewById(R.id.iv_top_back);
    }

    /**
     * 设置标题栏右侧按钮显示状态
     *
     * @param visible 显示状态
     * @param text    显示文字
     */
    public void setTopRightButtonVisible(boolean visible, String text) {
        TextView rightButton = (TextView) findViewById(R.id.tv_right_button);
        if (visible) {
            rightButton.setVisibility(View.VISIBLE);
        } else {
            rightButton.setVisibility(View.VISIBLE);
        }
        rightButton.setText(text);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRightButtonClick();
            }
        });

    }

    /**
     * 标题栏右侧按钮的点击事件
     */
    public void topRightButtonClick() {
    }

    /**
     * Toast 公共Toast方法
     * @param message 需要展示的信息
     */
    public void showLongToast( String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * Toast 公共Toast方法
     * @param message 需要展示的信息
     */
    public void showShortToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    public Toast c_toast;
    /**
     * 修改toast显示到中间位置
     * @param message 需要展示的信息
     */
    public void showCenterToastCenter(String message) {
        c_toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        c_toast.setGravity(Gravity.CENTER, 0, 0);
        c_toast.show();
    }

    /**
     * 判断权限
     * @param permissions 权限数组
     */
    public void requestMyPermission(String[] permissions) {
        //根据列出的权限分次获取敏感权限
        EasyPermission.with(this).code(REQUEST_CODE_CAMERA).permissions(permissions).request();
    }
    /**
     * 判断权限
     * @param permissions 权限数组
     */
    public void requestMyPermission(String permissions,String permission) {
        //根据列出的权限分次获取敏感权限
        permissionTag = permission;
        EasyPermission.with(this).code(REQUEST_CODE_CAMERA).permissions(permissions).request();
    }
    //必须复写此处的回调，否则无法获取用户授权结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermission.handleResult(this, requestCode, permissions, grantResults);//处理权限申请回调结果
    }

    //用户授权成功
    @Override
    public void onBasicPermissionSuccess() {
        permissionState = 0;
        EventBus.getDefault().post(new PermissionEvent(0,permissionTag));
    }

    //用户授权失败，但是未勾选不再提醒
    @Override
    public void onBasicPermissionFailed() {
        permissionState = 1;
        EventBus.getDefault().post(new PermissionEvent(1,permissionTag));
    }

    //用户授权失败，且勾选不再提醒
    @Override
    public void onBasicPermissionFailedNeedRational() {
        permissionState = 2;
        EventBus.getDefault().post(new PermissionEvent(2,permissionTag));
    }
}
