package com.ccj.dialoglib.dialog.imp;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.ccj.dialoglib.DialogConfig;
import com.ccj.dialoglib.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 通用 带头部背景 样式
 * Created by chenchangjun on 17/10/11.
 */

public class CommonImgHeaderDialog extends CommonNormalDialog implements View.OnClickListener {

    private String url_header;
    private ImageView iv_header_cancel;
    private SimpleDraweeView iv_header;


    @Override
    protected void loadHeader() {


        if (type== DialogConfig.TYPE_HAS_HEADER){
            iv_header.setVisibility(View.VISIBLE);
            iv_header_cancel.setVisibility(View.VISIBLE);


            if (TextUtils.isEmpty(url_header)) {
                iv_header.setVisibility(View.GONE);
            } else {
                //iv_header.setImageURI(url_header);
                iv_header.setBackgroundResource(R.drawable.common_dialog_header_bg);
                Uri uri = Uri.parse(url_header);
                iv_header.setImageURI(uri);
            }



        }
    }

    public CommonImgHeaderDialog setHeaderUrl(String str) {
        url_header = str;
        return this;
    }

    public CommonImgHeaderDialog(Context context) {
        super(context);
    }

    @Override
    protected View initHeader() {
        View header=null;
        if (type==DialogConfig.TYPE_HAS_NO_HEADER){
            return null;
        }
        header = View.inflate(context, R.layout.common_dialog_pic_header, null);
        iv_header_cancel = (ImageView) header.findViewById(R.id.iv_header_cancel);
        iv_header = (SimpleDraweeView) header.findViewById(R.id.iv_header);
        iv_header_cancel.setOnClickListener(this);

        return header;
    }

}
