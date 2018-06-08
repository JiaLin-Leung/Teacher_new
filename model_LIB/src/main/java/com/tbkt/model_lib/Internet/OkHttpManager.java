package com.tbkt.model_lib.Internet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.tbkt.model_lib.Tools.LogUtils;
import com.tbkt.model_lib.Tools.SpUtils;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by smile_raccoon on 2018/1/11.
 */

public class OkHttpManager {

    private static OkHttpManager mOkHttpManager;

    private OkHttpClient mOkHttpClient;

    private Gson mGson;

    private Handler handler;

    public String tbkt_token;
    public SharedPreferences sp ;

    private OkHttpManager() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.newBuilder().connectTimeout(1, TimeUnit.SECONDS).readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS);
        mGson = new Gson();
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 获得单一实例【Okhttp官方建议】
     * @return OkHttpManager单一实例
     */
    public static OkHttpManager getInstance() {
        if (mOkHttpManager == null) {
            mOkHttpManager = new OkHttpManager();
        }
        return mOkHttpManager;
    }

    /**
     * 对外GET方法
     * @param url url地址
     * @param callBack 回调函数
     */
    public void getRequest(Context context,String url, final BaseCallBack callBack) {
        LogUtils.showLog("服务器地址",url);
        Request request = buildRequest(context,url, null, HttpMethodType.GET);
        doRequest(context,request, callBack);
    }

    /**
     * 对外POST方法
     * @param url POST 地址
     * @param callBack 回调函数
     */
    public void postRequest(Context context,String url, final BaseCallBack callBack, Map<String, String> params) {
        LogUtils.showLog("服务器地址",url);
        LogUtils.showLog("POST传参",params.toString());
        Request request = buildRequest(context,url, params, HttpMethodType.POST);
        doRequest(context,request, callBack);
    }

    /**
     * 上传单一图片
     * @param url  上传图片地址
     * @param callback 回调函数
     * @param file 文件【file类型】
     * @param fileKey 文件key值
     */
    public void postUploadSingleImage(Context context,String url, final BaseCallBack callback, File file, String fileKey) {
//        Param[] paramsArr = fromMapToParams(params);
        try {
            postAsyn(context,url, callback, file, fileKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void postUploadMoreImages(Context context,String url, final BaseCallBack callback, File[] files, String[] fileKeys, Map<String, String> params) {
        Param[] paramsArr = fromMapToParams(params);

        try {
            postAsyn(context,url, callback, files, fileKeys, paramsArr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /***********************
     * 对内方法
     ************************/
    //单个文件上传请求  不带参数
    private void postAsyn(Context context,String url, BaseCallBack callback, File file, String fileKey) throws IOException {
        Request request = buildMultipartFormRequest1(url);
        doRequest(context,request, callback);
    }

    //单个文件上传请求 带参数
    private void postAsyn(Context context,String url, BaseCallBack callback, File file, String fileKey, Param... params) throws IOException {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, params);
        doRequest(context,request, callback);
    }

    //多个文件上传请求 带参数
    private void postAsyn(Context context,String url, BaseCallBack callback, File[] files, String[] fileKeys, Param... params) throws IOException {
        Request request = buildMultipartFormRequest(url, files, fileKeys, params);
        doRequest(context,request, callback);
    }

    /**
     * 构造上传图片Request
     * @param url 上传图片地址
     * @param files 文件【file类型】
     * @param fileKeys 文件 key值
     * @param params
     * @return
     */
    private Request buildMultipartFormRequest(String url, File[] files, String[] fileKeys, Param[] params) {
        params = validateParam(params);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Param param : params) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""),
                    RequestBody.create(MediaType.parse("image/png"), param.value));
        }
        if (files != null) {
            RequestBody fileBody = null;
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //TODO 根据文件名设置contentType
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + fileName + "\""),
                        fileBody);
            }
        }

        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }
    /**
     * 构造上传图片Request
     * @param url 上传图片地址

     * @return
     */
    private Request buildMultipartFormRequest1(String url) {

        return new Request.Builder()
                .url(url)
                .build();
    }

    //Activity页面所有的请求以Activity对象作为tag，可以在onDestory()里面统一取消,this
    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
    private String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }

    private Param[] fromMapToParams(Map<String, String> params) {
        if (params == null)
            return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }

    //去进行网络 异步 请求
    private void doRequest(final Context context, Request request, final BaseCallBack callBack) {
        callBack.OnRequestBefore(request);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onResponse(response);
                String result = response.body().string();
                Log.e("result",result);
                if(result.contains("no_user")){//如果是no_user直接调到登录页!
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //TODO 判断是否no_user
//                            Intent intent = new Intent();
//                            intent.setClass(context, LoginActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            context.startActivity(intent);
                        }
                    });
                }
                if(result.contains("fail")){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context,"请求失败，请重试！",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                if (response.isSuccessful()) {
                    Log.e("isSuccessful ",result);
                    if (callBack.mType == String.class) {
//                        callBack.onSuccess(call, response, result);
                        callBackSuccess(callBack, call, response, result);
                    } else {
                        try {
                            Log.e("result",result);
                            Object object = mGson.fromJson(result, callBack.mType);//自动转化为 泛型对象
//                            callBack.onSuccess(call, response, object);
                            callBackSuccess(callBack, call, response, object);
                        } catch (JsonParseException e) {
                            //json解析错误时调用
                            callBackError(callBack,call,response.code());
                        }
                    }
                } else {
                    callBackError(callBack,call,response.code());
                }
            }
        });
    }

    /**
     * 创建 Request对象
     * @param url
     * @param params
     * @param methodType
     * @return
     */
    private Request buildRequest(Context context,String url, Map<String, String> params, HttpMethodType methodType) {

        sp = SpUtils.get(context);
        tbkt_token = sp.getString("token","");
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.addHeader("tbkt-token",tbkt_token);
        if (methodType == HttpMethodType.GET) {
            builder.get();
        } else if (methodType == HttpMethodType.POST) {
            RequestBody requestBody = buildFormData(params);
            builder.post(requestBody);
        }
        return builder.build();
    }

    /**
     * 构建请求所需的参数表单
     * @param params 需要传给服务器的数据，键值对形式
     * @return
     */
    private RequestBody buildFormData(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("platform", "android");
//        builder.add("version", "1.0");
//        builder.add("key", "123456");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    private void callBackSuccess(final BaseCallBack callBack, final Call call, final Response response, final Object object) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try{
                    callBack.onSuccess(call, response, object);
                }catch (Exception e){
                }
            }
        });

    }

    private void callBackError(final BaseCallBack callBack, final Call call, final int code) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onEror(call, code, null);
            }
        });

    }

    private Param[] validateParam(Param[] params) {
        if (params == null)
            return new Param[0];
        else
            return params;
    }

    public static class Param {
        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }

    enum HttpMethodType {
        GET, POST
    }


}
