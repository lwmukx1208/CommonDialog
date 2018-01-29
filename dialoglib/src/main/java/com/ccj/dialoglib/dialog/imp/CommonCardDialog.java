package com.ccj.dialoglib.dialog.imp;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccj.dialoglib.R;
import com.ccj.dialoglib.dialog.BaseDialog;
import com.ccj.dialoglib.listener.OnLeftListener;
import com.ccj.dialoglib.listener.OnRightListener;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 通用商品卡片 样式
 * Created by chenchangjun on 17/10/11.
 */

public class CommonCardDialog extends BaseDialog implements View.OnClickListener {




    private String title;

    private String card_pic_url,str_card_title,str_card_price;


    private String strLeft, strRight;

    private ImageView iv_cancel;

    private SimpleDraweeView card_pic;


    private TextView card_title, card_price;

    private View v_vertical_line;
    private TextView tv_left, tv_right;


    private TextView tv_title;


    private OnLeftListener onLeftListener;
    private OnRightListener onRightListener;


    @Override
    protected void loadHeader() {
    }

    @Override
    protected void loadContent() {

        if (TextUtils.isEmpty(title)) {
            tv_title.setVisibility(View.GONE);
        } else {
            tv_title.setText(Html.fromHtml(title));
        }
        if (TextUtils.isEmpty(card_pic_url)) {
            card_pic.setVisibility(View.GONE);
        } else {
            card_pic.setImageURI(card_pic_url);
        }
        if (TextUtils.isEmpty(str_card_title)) {
            card_title.setVisibility(View.GONE);
        } else {
            card_title.setText(str_card_title);
        }
        if (TextUtils.isEmpty(str_card_price)) {
            card_price.setVisibility(View.GONE);
        } else {
            card_price.setText(str_card_price);
        }

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
    protected void loadBottom() {

    }



    public CommonCardDialog setTitle(String str) {
        title = str;

        return this;
    }


    public CommonCardDialog setCardPicUrl(String str) {
        card_pic_url = str;

        return this;
    }

    public CommonCardDialog setCardTitle(String str) {
        str_card_title = str;

        return this;
    }


    public CommonCardDialog setCardPrice(String str) {
        str_card_price = str;

        return this;
    }

    public CommonCardDialog setLeftBtn(String str, OnLeftListener onLeftListener) {
        strLeft = str;
        this.onLeftListener = onLeftListener;
        return this;
    }


    public CommonCardDialog setConfirmBtn(String str, OnLeftListener onLeftListener) {
        strLeft = str;
        this.onLeftListener = onLeftListener;
        return this;
    }


    public CommonCardDialog setRightBtn(String str, OnRightListener onRightListener) {
        strRight = str;
        if (TextUtils.isEmpty(str)) {
            tv_left.setVisibility(View.GONE);
        } else {
            tv_left.setText(str);
            tv_left.setOnClickListener(this);
            this.onRightListener = onRightListener;
        }
        return this;
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
                onLeftListener.onLeftClick("");
            dismiss();
        } else if (i == R.id.tv_right) {
                onRightListener.onRightClick("");
            dismiss();

        } else if (i == R.id.iv_content_cancel) {
            dismiss();

        }else if (i == R.id.iv_header_cancel) {
            dismiss();

        }
    }


    public CommonCardDialog(Context context) {
        super(context);
    }

    @Override
    protected View initHeader() {

        return null;
    }

    @Override
    protected View initContent() {

        View content = View.inflate(context, R.layout.common_dialog_content_card, null);


        tv_title = (TextView) content.findViewById(R.id.tv_title);

        card_pic= (SimpleDraweeView) content.findViewById(R.id.card_pic);
        card_price= (TextView) content.findViewById(R.id.card_price);
        card_title= (TextView) content.findViewById(R.id.card_title);


        v_vertical_line = content.findViewById(R.id.v_vertical_line);
        tv_left = (TextView) content.findViewById(R.id.tv_left);
        tv_right = (TextView) content.findViewById(R.id.tv_right);

        iv_cancel = (ImageView) content.findViewById(R.id.iv_content_cancel);
        iv_cancel.setOnClickListener(this);
        return content;
    }

    @Override
    protected View initBottom() {
        return null;
    }


}
