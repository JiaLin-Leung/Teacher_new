package com.tbkt.model_lib.Internet;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbkt.model_lib.R;
import com.tbkt.model_lib.View.Loading_view;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by smile_raccoon on 2018/1/11.
 */

public abstract class LoadCallBack<T> extends BaseCallBack<T> {
    private Context context;

    private boolean showDialog = true;
    private Loading_view dialog;
    public LoadCallBack(Context context) {
        this.context = context;
//        dialog=createLoadingDialog(context,"正在加载");
        showDialog();
    }

    public LoadCallBack(Context context,boolean showDialog) {
        this.context = context;
        this.showDialog = showDialog;
//        dialog=createLoadingDialog(context,"正在加载");
        showDialog();
    }
    //显示进度条
    private void showDialog() {
        dialog=new Loading_view(context,R.style.CustomDialog);
        dialog.show();
    }

    private void hideDialog() {
        if (dialog != null) {
//            // TODO: 2018/6/1
//           closeDialog(dialog);
            //隐藏进度条
//            dialog.dismiss();
        }
    }



    @Override
    protected void OnRequestBefore(Request request) {
        if (showDialog){
            showDialog();
        }
    }

    @Override
    protected void onFailure(Call call, IOException e) {
        hideDialog();
    }

    @Override
    protected void onResponse(Response response) {
        hideDialog();
    }

    @Override
    protected void inProgress(int progress, long total, int id) {

    }
//    public static Dialog createLoadingDialog(Context context, String msg) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
//        LinearLayout layout = (LinearLayout) v
//                .findViewById(R.id.dialog_loading_view);// 加载布局
//        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
//        tipTextView.setText(msg);// 设置加载信息
//
//        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
//        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
//        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
//        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
//        /**
//         *将显示Dialog的方法封装在这里面
//         */
//        Window window = loadingDialog.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setGravity(Gravity.CENTER);
//        window.setAttributes(lp);
//        return loadingDialog;
//    }
//    /**
//     * 关闭dialog
//     *
//     * @param mDialogUtils
//     */
//    public static void closeDialog(Dialog mDialogUtils) {
//        if (mDialogUtils != null && mDialogUtils.isShowing()) {
//            mDialogUtils.dismiss();
//        }
//    }
}