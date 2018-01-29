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
 * 通用 顶部logo 样式
 * Created by chenchangjun on 17/10/11.
 */

public class CommonLogoHeaderDialog extends CommonNormalDialog  {



    private String url_logo_pic;


    private SimpleDraweeView iv_logo;
    private ImageView iv_header_cancel;





    @Override
    protected void loadHeader() {
        if (type== DialogConfig.TYPE_HAS_HEADER){
            iv_logo.setVisibility(View.VISIBLE);
            iv_header_cancel.setVisibility(View.VISIBLE);

            if (TextUtils.isEmpty(url_logo_pic)) {
                iv_logo.setVisibility(View.GONE);
            } else {
                Uri uri = Uri.parse(url_logo_pic);
                iv_logo.setImageURI(uri);
              //  BaseImgUtils.loadCornersImage(url_logo_pic, iv_logo, Color.WHITE, 2, 6);
            }


        }
    }



    public CommonLogoHeaderDialog setLOGOUrl(String str) {
        url_logo_pic = str;

        return this;
    }




    public CommonLogoHeaderDialog(Context context) {
        super(context);
    }

    @Override
    protected View initHeader() {
        View header=null;
        if (type==DialogConfig.TYPE_HAS_NO_HEADER){
            return null;
        }
        header = View.inflate(context, R.layout.common_dialog_logo_header, null);
        iv_logo = (SimpleDraweeView) header.findViewById(R.id.iv_logo);
        iv_header_cancel = (ImageView) header.findViewById(R.id.iv_header_cancel);
        iv_header_cancel.setOnClickListener(this);

        return header;
    }




}
