package com.ccj.dialoglib.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccj.dialoglib.R;
import com.ccj.dialoglib.listener.OnCancelListener;
import com.ccj.dialoglib.listener.OnLeftListener;
import com.ccj.dialoglib.listener.OnRightListener;


/**
 * 普通dialog的基类
 * Created by chenchangjun on 18/1/12.
 */

public abstract class BaseNormalDialog extends BaseDialog implements View.OnClickListener {

    //str
    protected String title;//弹窗标题,
    protected String strLeft, strRight; //左右button标题

    //view
    protected ViewGroup ll_dialog_container_content; //内容区容器
    protected ImageView iv_cancel;//右上角 取消按钮
    protected TextView tv_title; //如果为空则不显示
    protected View v_vertical_line; //内容区和底部button 之间 的 间隔view
    protected TextView tv_left, tv_right; //左右button

    //需要填充的view
    protected View contentView;

    //监听
    protected OnLeftListener onLeftListener; //多用于确认按钮;也可用于只有一个button的时候,
    protected OnRightListener onRightListener;//多用于取消

    protected OnCancelListener onCancelListener;//弹窗取消

    /**
     * 加载通用头部,包括title 和 cancel 按钮
     */
    @Override
    protected void loadHeader() {
        if (TextUtils.isEmpty(title)) {
            tv_title.setVisibility(View.GONE);
        } else {
            tv_title.setText(Html.fromHtml(title));
        }
    }

    /**
     * 加载子类实现的 内容区域
     */
    @Override
    protected void loadContent() {
        handleContentView(ll_dialog_container_content, contentView);
    }

    /**
     * 加载底部按钮区域
     */
    @Override
    protected void loadBottom() {

        if (TextUtils.isEmpty(strLeft)) {
            tv_left.setVisibility(View.GONE);
            v_vertical_line.setVisibility(View.GONE);
        } else {
            tv_left.setText(strLeft);
            tv_left.setOnClickListener(this);
        }

        if (TextUtils.isEmpty(strRight)) {
            tv_right.setVisibility(View.GONE);
            v_vertical_line.setVisibility(View.GONE);
        } else {
            tv_right.setText(strRight);
            tv_right.setOnClickListener(this);

        }
    }
    /*************************构造子类的内容布局***********************/


    /**
     * 构造 实现类 内容布局
     *
     * @return
     */
    public abstract View createContentView();


    /**
     * 处理 实现类的 view数据
     *
     * @param parents view 的ViewGroup容器
     * @param v       view本身
     */
    public abstract void handleContentView(ViewGroup parents, View v);

    /**
     * 子类view 监听器
     *
     * @param v 事件分发 view
     */
    public abstract void onContentClick(View v);

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
            onLeftListener.onLeftClick("");
            dismiss();
        } else if (i == R.id.tv_right) {
            onRightListener.onRightClick("");
            dismiss();

        } else if (i == R.id.iv_header_cancel) {
            cancel();
            if (onCancelListener != null) {
                onCancelListener.onCancel();
            }
        } else {
            onContentClick(v);
        }
    }


    public BaseNormalDialog(Context context) {
        super(context);
    }


    /**
     * 初始化 头部布局
     *
     * @return
     */
    @Override
    protected View initHeader() {
        View header = View.inflate(context, R.layout.common_normal_base_dialog_header, null);

        tv_title = (TextView) header.findViewById(R.id.tv_title);
        iv_cancel = (ImageView) header.findViewById(R.id.iv_header_cancel);
        iv_cancel.setOnClickListener(this);
        return header;
    }


    /**
     * 初始化 内容区
     *
     * @return
     */
    @Override
    protected View initContent() {

        View content = View.inflate(context, R.layout.common_normal_base_dialog_content, null);

        ll_dialog_container_content = (ViewGroup) content.findViewById(R.id.ll_dialog_container_content);

        contentView = createContentView();

        ll_dialog_container_content.addView(contentView);

        return content;
    }

    /**
     * 初始化 底部
     *
     * @return
     */
    @Override
    protected View initBottom() {
        View footer = View.inflate(context, R.layout.common_dialog_footer_btn, null);

        v_vertical_line = footer.findViewById(R.id.v_vertical_line);
        tv_left = (TextView) footer.findViewById(R.id.tv_left);
        tv_right = (TextView) footer.findViewById(R.id.tv_right);

        return footer;
    }

    /***********************set 区域************************/

    public BaseNormalDialog setTitle(String str) {
        title = str;

        return this;
    }


    public BaseNormalDialog setLeftBtn(String str, OnLeftListener onLeftListener) {
        strLeft = str;
        if (TextUtils.isEmpty(str)) {
            tv_left.setVisibility(View.GONE);
        } else {
            tv_left.setText(str);
            tv_left.setOnClickListener(this);
            this.onLeftListener = onLeftListener;
        }
        return this;
    }


    public BaseNormalDialog setConfirmBtn(String str, OnLeftListener onLeftListener) {
        strLeft = str;
        if (TextUtils.isEmpty(str)) {
            tv_left.setVisibility(View.GONE);
        } else {
            tv_left.setText(str);
            tv_left.setOnClickListener(this);
            this.onLeftListener = onLeftListener;
        }


        return this;
    }


    /**
     * 给当前Dialog设置关闭之后的回调监听,和ondissmiss不同
     *
     * @param listener OnCancelListener
     */
    public BaseNormalDialog setOnCancelListener(DialogInterface.OnCancelListener listener) {
        super.setOnDialogCancelListener(listener);
        return this;
    }


    public BaseNormalDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        getDialog().setOnDismissListener(onDismissListener);
        return this;
    }

    public BaseNormalDialog setRightBtn(String str, OnRightListener onRightListener) {
        strRight = str;
        if (TextUtils.isEmpty(str)) {
            tv_right.setVisibility(View.GONE);
        } else {
            tv_right.setText(str);
            tv_right.setOnClickListener(this);
            this.onRightListener = onRightListener;
        }
        return this;
    }

    @Override
    public BaseNormalDialog setDialogCancelable(boolean flag) {
        super.setDialogCancelable(flag);
        return this;
    }
}

