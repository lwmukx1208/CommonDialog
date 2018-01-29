package com.ccj.dialog.dialogImp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ccj.dialog.R;
import com.ccj.dialoglib.dialog.BaseNormalDialog;


/**
 * 自定义样式
 * Created by chenchangjun on 17/10/11.
 */

public class MyDialogDialog extends BaseNormalDialog implements View.OnClickListener {


    private ImageView card_pic;
    private TextView card_price;
    private TextView card_title;

    /**
     * 构造 实现类 内容布局
     *
     * @return
     */
    @Override
    public View createContentView() {
        View myView = View.inflate(context, R.layout.dialog_my_dialog, null);


        card_pic= (ImageView) myView.findViewById(R.id.card_pic);

        card_price= (TextView) myView.findViewById(R.id.card_price);
        card_title= (TextView) myView.findViewById(R.id.card_title);
        card_pic.setOnClickListener(this);
        return myView;
    }

    /**
     * 处理 实现类的 view数据
     *
     * @param parents view 的ViewGroup容器
     * @param v       view本身
     */
    @Override
    public void handleContentView(ViewGroup parents, View v) {
        card_pic.setImageResource(R.mipmap.ic_launcher_round);

    }

    /**
     * 子类view 监听器
     *
     * @param v 事件分发 view
     */
    @Override
    public void onContentClick(View v) {

        int i = v.getId();
        if (i == R.id.card_pic) {
            Toast.makeText(context,"hello MyDialog!!!!",Toast.LENGTH_SHORT).show();
        }
    }



    public MyDialogDialog setMyView(String st) {
        card_title.setText(st);
        return this;
    }

    public MyDialogDialog(Context context) {
        super(context);
    }


}
