package com.tbkt.teacher;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baoyz.actionsheet.ActionSheet;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.model.TResult;
import com.tbkt.model_lib.Base.BaseActivity;
import com.tbkt.model_lib.Internet.LoadCallBack;
import com.tbkt.model_lib.Internet.OkHttpManager;
import com.tbkt.model_lib.Tools.Content;
import com.tbkt.model_lib.Tools.LogUtils;
import com.tbkt.model_lib.Tools.Util;
import com.tbkt.model_lib.Tools.VideoRecordActivity;
import com.tbkt.teacher.Login.LoginActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Author: DBJ
 * @Date: 2018/6/8 11:21

 * @Description:
 *
 */
public class MainActivity extends BaseActivity implements View.OnClickListener,ActionSheet.ActionSheetListener{
    @Bind(R.id.toBJ)
    Button toBJ;
    @Bind(R.id.toHN)
    Button toHN;
    @Bind(R.id.toLN)
    Button toLN;
    @Bind(R.id.button_login)
    Button button_login;
    //    @Bind(R.id.toluyin)
//    Button button_paishipin;
    @Bind(R.id.ed_username)
    EditText ed_username;
    @Bind(R.id.ed_password)
    EditText ed_password;
    private String username;
    private String password;
    private OkHttpManager mOkHttpManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toBJ://去北京
//                ARouter.getInstance().build(Content.GOTO_BEIJING).navigation();
                break;
            case R.id.toHN://去河南
                ARouter.getInstance().build(Content.GOTO_HENAN).navigation();
                break;
            case R.id.toLN://去辽宁
//                ARouter.getInstance().build(Content.GOTO_LIAONING).navigation();
                //第三方弹框
                ActionSheet.createBuilder(MainActivity.this, getSupportFragmentManager())
                        .setCancelButtonTitle(R.string.cancle)
                        .setOtherButtonTitles("相册","拍照")
                        .setCancelableOnTouchOutside(true)
                        .setListener(MainActivity.this).show();
                break;
            case R.id.button_login:
//                  username = ed_username.getText().toString();
////                password = ed_password.getText().toString();
////                loginServer(username,password);
//                //资源文件转bitmap
//                Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.clear);
//                try {
//                    //上传文件
//                    uploadMyFile( saveFile(bitmap,"head"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.toluyin:
                //去录视频
                Intent intent =new Intent(MainActivity.this, VideoRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.tosaoyisao:
                //去摇一摇
                ARouter.getInstance().build(Content.GOTO_SCAN).navigation();
                break;
        }
    }

    /**
     * @Author: DBJ
     * @Date: 2018/6/4 17:38
     * @param username 用户名
     * @param password 密码
     * @Description: 登陆
     *
     */
    private void loginServer(String username,String password) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        params.put("login_type", 9 + "");
        params.put("version", Util.getAndroidVersion());
        params.put("name", Util.getAndroidName(this));
        params.put("model", Util.getDeviceISN(this));
        params.put("platform", "Android");
        params.put("uuid", Util.getDeviceType(this));
        params.put("appversion", Util.getAppVersion(this));
        mOkHttpManager = OkHttpManager.getInstance();
        mOkHttpManager.postRequest(MainActivity.this, "http://mapi-beta.m.jxtbkt.cn/account/login/s", new LoadCallBack<String>(MainActivity.this) {

            @Override
            protected void onSuccess(Call call, Response response, String s) {
                LogUtils.showLog("登陆成功---",s);
            }

            @Override
            protected void onEror(Call call, int statusCode, Exception e) {

            }
        }, params);
    }

    /**
     * @Author: DBJ
     * @Date: 2018/6/4 17:25
     * @param file  头像文件
     * @Description: 上传头像
     *
     */
    private void uploadMyFile(File file) {
        Log.e("dbj",file.toString());
        mOkHttpManager = OkHttpManager.getInstance();
        mOkHttpManager.postUploadSingleImage(MainActivity.this, "http://upload.m.xueceping.cn/swf_upload/?upcheck=c3e41afb2d7d798431840a8f172631d0&upType=portrait", new LoadCallBack<String>(MainActivity.this) {
            @Override
            protected void onSuccess(Call call, Response response, String s) throws IOException {
//                final ResultBean2 bean = ResultBeanObject.getResultBean2(response.body().string());
                Log.e("syw", "将文件上传之后保存的路径:" + response.toString());
            }

            @Override
            protected void onEror(Call call, int statusCode, Exception e) {
                Log.e("syw", "shibai:");
            }
        },file,"");
    }

    /**
     * 将bitmap保存为File
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public File saveFile(Bitmap bm, String fileName) throws IOException {
        Log.e("dbj",bm+"----");
        String path = Environment.getExternalStorageDirectory().getPath() + "/revoeye/";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();

        return myCaptureFile;
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    /**
     * 调起系统相机 0 相册  1  相机
     * @param actionSheet
     * @param index
     */

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            //调起系统相册
            getTakePhoto().onPickMultiple(1);
        } else if (index == 1) {
            //调起系统相机
            File file = new File(Environment.getExternalStorageDirectory(), "/revoeye/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            Uri imageUri = Uri.fromFile(file);
            getTakePhoto().onPickFromCapture(imageUri);
        }
    }
    //取消调起系统相机
    @Override
    public void takeCancel() {
        super.takeCancel();
    }
    @Override
    public TakePhoto getTakePhoto() {
        return super.getTakePhoto();
    }
    //调起成功
    @Override
    public void takeSuccess(final TResult result) {
        super.takeSuccess(result);
        final String imgstr = result.getImages().get(0).getOriginalPath();
        File img = new File(imgstr);
        if (img.exists()) {
            File fileLinShi = new File(imgstr);
            String src_path = fileLinShi.getAbsolutePath();// 原图片的路径
            String targetPath = src_path;// 压缩后图片的路径
            final String compressImage = Util.compressImage(src_path, targetPath,
                    100);// 进行图片压缩，返回压缩后图片的路径
            final File file = new File(compressImage); // 压缩后的图片
            uploadMyFile(file);
        }

    }
    //调起失败
    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

}
