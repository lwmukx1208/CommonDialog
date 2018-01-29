package com.ccj.dialoglib.dialog.imp;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccj.dialoglib.R;
import com.ccj.dialoglib.dialog.BaseDialog;
import com.ccj.dialoglib.listener.OnLeftListener;
import com.ccj.dialoglib.listener.OnRightListener;


/**
 * 普通dialig 无头部,
 * Created by chenchangjun on 17/10/11.
 */

public class CommonNormalDialog extends BaseDialog implements View.OnClickListener {


    protected int type = -1; //有无头  有无底部 样式
    protected int contentGravity; //layout_gravity

    protected String title;
    protected String content;
    protected String editContentHint;
    protected String strLeft, strRight;


    protected ImageView iv_cancel;
    protected View v_vertical_line;
    protected TextView tv_left, tv_right;
    protected TextView tv_title, tv_content;
    protected EditText etv_content;

    protected OnLeftListener onLeftListener;
    protected OnRightListener onRightListener;


    @Override
    protected void loadHeader() {
        iv_cancel.setVisibility(View.VISIBLE);
    }

    @Override
    protected void loadContent() {

        if (TextUtils.isEmpty(title)) {
            tv_title.setVisibility(View.GONE);
        } else {
            tv_title.setText(Html.fromHtml(title));
        }

        if (TextUtils.isEmpty(content)) {
            tv_content.setVisibility(View.GONE);
        } else {
            tv_content.setText(Html.fromHtml(content));
            tv_content.setGravity(contentGravity);
        }


        if (TextUtils.isEmpty(editContentHint)) {
            etv_content.setVisibility(View.GONE);
        } else {
            etv_content.setHint(editContentHint);
        }

    }

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



    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
            if (etv_content.getVisibility() == View.VISIBLE) {
                if (onLeftListener!=null){
                    onLeftListener.onLeftClick(etv_content.getText().toString());
                }
                setInputState();
            } else {
                if (onLeftListener!=null){
                    onLeftListener.onLeftClick(tv_content.getText().toString());
                }
            }
            dismiss();
        } else if (i == R.id.tv_right) {
            if (etv_content.getVisibility() == View.VISIBLE) {
                if (onRightListener!=null){
                    onRightListener.onRightClick(etv_content.getText().toString());
                }
                setInputState();
            } else {
                if (onRightListener!=null){
                    onRightListener.onRightClick(tv_content.getText().toString());
                }
            }
            dismiss();

        } else if (i == R.id.iv_content_cancel) {
            dismiss();

        } else if (i == R.id.iv_header_cancel) {
            dismiss();

        }
    }


    public void setInputState() {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(etv_content.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    public CommonNormalDialog(Context context) {
        super(context);
    }

    /**
     * 普通样式无头部
     * @return
     */
    @Override
    protected View initHeader() {
        return null;
    }

    /**
     * 创建内容布局
     * @return 内容布局
     */
    @Override
    protected View initContent() {

        View content = View.inflate(context, R.layout.common_dialog_content, null);


        tv_title = (TextView) content.findViewById(R.id.tv_title);
        tv_content = (TextView) content.findViewById(R.id.tv_content);

        etv_content = (EditText) content.findViewById(R.id.etv_content);

        v_vertical_line = content.findViewById(R.id.v_vertical_line);
        tv_left = (TextView) content.findViewById(R.id.tv_left);
        tv_right = (TextView) content.findViewById(R.id.tv_right);

        iv_cancel = (ImageView) content.findViewById(R.id.iv_content_cancel);

        iv_cancel.setOnClickListener(this);
        return content;
    }

    /**
     * 创建 底部 按钮布局
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


    public CommonNormalDialog setTitle(String str) {
        title = str;

        return this;
    }


    public CommonNormalDialog setContent(String str) {
        content = str;

        return this;
    }


    public CommonNormalDialog setShowEditHint(String str) {
        editContentHint = str;

        return this;
    }

    public CommonNormalDialog setDialogType(int type) {
        this.type = type;

        return this;
    }

    /**
     * left 作为 双btn的 左侧按钮, 也可作为 单按钮的 确定按钮
     * @param str
     * @param onLeftListener
     * @return
     */
    public CommonNormalDialog setLeftBtn(String str, OnLeftListener onLeftListener) {
        strLeft = str;
        this.onLeftListener = onLeftListener;
        return this;
    }


    public CommonNormalDialog setConfirmBtn(String str, OnLeftListener onLeftListener) {
        strLeft = str;
        this.onLeftListener = onLeftListener;
        return this;
    }


    public CommonNormalDialog setRightBtn(String str, OnRightListener onRightListener) {
        strRight = str;
        this.onRightListener = onRightListener;
        return this;
    }

    public CommonNormalDialog setContentGravity(int contentGravity) {
        this.contentGravity = contentGravity;
        return this;
    }
    public CommonNormalDialog setCancelable(boolean cancelable) {
        setDialogCancelable(cancelable);
        return this;
    }

}
