package com.ccj.dialoglib.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ccj.dialoglib.R;


/**
 * 装饰者模式 装饰 AlertDialog
 * Created by chenchangjun on 17/10/11.
 */

public abstract class BaseDialog {

    //===Desc:成员变量==========================================================================================

    protected Context context;//上下文对象


    public AlertDialog getDialog() {
        return dialog;
    }
    private Window window;
    private AlertDialog dialog;//基于AlertDialog真正显示在界面上的Dialog

    protected FrameLayout fl_base_header;//title部分
    protected FrameLayout fl_base_content;//正文部分
    protected FrameLayout fl_base_bottom;//底部

    //===Desc:构造函数==========================================================================================

    public BaseDialog(Context context) {
        this.context = context;
        initView();
    }

    //===Desc:本类中使用的方法==========================================================================================

    /**
     * 进行初始化的操作
     */
    private void initView() {
        //显示Dialog
        dialog = new AlertDialog.Builder(context, R.style.common_dialog).create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow()
                .setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE);

        //设置Material Design风格的背景
         window = dialog.getWindow();
        View rootView = LayoutInflater.from(context).inflate(R.layout.common_dialog_base, null, false);
        window.setContentView(rootView);

        //初始化header部分
        fl_base_header = (FrameLayout) window.findViewById(R.id.dialog_header_container);
        View titleView = initHeader();
        if (null == titleView) {
            fl_base_header.setVisibility(View.GONE);
            fl_base_header.removeAllViews();
        } else {
            fl_base_header.setVisibility(View.VISIBLE);
            fl_base_header.addView(titleView);
        }

        //初始化正文部分
        fl_base_content = (FrameLayout) window.findViewById(R.id.dialog_view_container);
        View contentView = initContent();
        if (null == contentView)
            throw new UnsupportedOperationException("The dialog must show a view in the window!");
        else
            fl_base_content.addView(contentView);

        //初始化底部
        fl_base_bottom = (FrameLayout) window.findViewById(R.id.dialog_footer_container);
        View bottomView = initBottom();
        if (null == bottomView)
            fl_base_bottom.setVisibility(View.GONE);
        else
            fl_base_bottom.addView(bottomView);
    }


    /**
     * 子类必须实现该方法用于显示在界面上的Dialog的Title部分
     *
     * @return title部分的显示的View，返回的titleView为null的话，Dialog将不显示title部分
     */
    protected abstract View initHeader();

    /**
     * 子类必须实现该方法用于显示在界面上的正文部分控件
     *
     * @return content部分显示的内容，如果返回null，将会抛出异常
     */
    protected abstract View initContent();

    /**
     * 子类实现该方法用于显示底部的view
     *
     * @return bottom部分显示的view，如果返回null，bottom部分将不显示
     */
    protected abstract View initBottom();


    /**
     * 模板方法
     */
    protected abstract void loadHeader();

    protected abstract void loadContent();

    protected abstract void loadBottom();

    /**
     * 加載完數據, 並且彈窗
     */
    public   void start(){
        loadHeader();
        loadContent();
        loadBottom();
        //show();
    }

    //===Desc:提供给外界使用的方法==========================================================================================


    /**
     * 设置点击Dialog以为是否关闭Dialog
     *
     * @param flag true：可以关闭。false：不可以关闭
     */
    public BaseDialog setDialogCancelable(boolean flag) {
        dialog.setCancelable(flag);
        return this;
    }





    /**
     * 显示这个Dialog
     */
    public void show() {
        if (!dialog.isShowing())
            dialog.show();
    }

    /**
     * 关闭当前的Dialog
     */
    public void dismiss() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    /**
     * 关闭当前的Dialog
     */
    public void cancel() {
        if (dialog.isShowing()) {
            dialog.cancel();
        }
    }

    /**
     * 当前Dialog是否在显示
     *
     * @return 是否显示.true:显示，false:没有显示
     */
    public boolean isShowing() {
        return null != dialog && dialog.isShowing();
    }

    /**
     * 给当前Dialog设置关闭之后的回调监听
     *
     * @param listener OnDialogDismissListener回调监听
     */
    protected void setOnDialogDismissListener(DialogInterface.OnDismissListener listener) {
        dialog.setOnDismissListener(listener);
    }


    /**
     * 给当前Dialog设置关闭之后的回调监听
     *
     * @param listener OnDialogDismissListener回调监听
     */
    protected void setOnDialogCancelListener(DialogInterface.OnCancelListener listener) {
        dialog.setOnCancelListener(listener);
    }


}