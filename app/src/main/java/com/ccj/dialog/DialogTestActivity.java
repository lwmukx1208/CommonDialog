package com.ccj.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ccj.dialog.dialogImp.MyDialogDialog;
import com.ccj.dialoglib.DialogCreator;
import com.ccj.dialoglib.listener.OnLeftListener;
import com.ccj.dialoglib.listener.OnRightListener;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by chenchangjun on 17/10/10.
 */

public class DialogTestActivity extends AppCompatActivity implements View.OnClickListener {


    String url = "https://w4.hoopchina.com.cn/46/cb/04/46cb04b0cbfa425d0281b5a5dbf77d5a002.png";

    Button show_confirm, show_2btn, show_title_2btn, show_edit_title_1btn, show_image_header_1btn, show_logo_header_1btn, show_card_2btn,show_my_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_test);
        Fresco.initialize(this);

        show_confirm = (Button) findViewById(R.id.show_confirm);
        show_2btn = (Button) findViewById(R.id.show_2btn);
        show_title_2btn = (Button) findViewById(R.id.show_title_2btn);
        show_edit_title_1btn = (Button) findViewById(R.id.show_edit_title_1btn);
        show_image_header_1btn = (Button) findViewById(R.id.show_image_header_1btn);
        show_logo_header_1btn = (Button) findViewById(R.id.show_logo_header_1btn);
        show_card_2btn = (Button) findViewById(R.id.show_card_2btn);

        show_my_btn= (Button) findViewById(R.id.show_my_btn);


        show_confirm.setOnClickListener(this);
        show_2btn.setOnClickListener(this);
        show_title_2btn.setOnClickListener(this);
        show_edit_title_1btn.setOnClickListener(this);
        show_image_header_1btn.setOnClickListener(this);
        show_logo_header_1btn.setOnClickListener(this);
        show_card_2btn.setOnClickListener(this);

        show_my_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.show_confirm:

//1
                DialogCreator.showConfirmDialog(this, "是否退出", "确定", new OnLeftListener() {
                    @Override
                    public void onLeftClick(String str) {

                    }
                }).show();
                break;
//2
            case R.id.show_title_2btn:

                DialogCreator.showTitle2BtnDialog(this, "showTitle1BtnDialog",

                        "另外还有客服功能，就是顾客接入咨询时的客服分配，按轮询方式把顾客分配给在线的客服接待。 用开源 Mina 框架实现了 TCP 的长连接接入，用\n" +
                                "\n" +
                                "Tomcat Comet 机制实现了 HTTP 的长轮询服务。 而消息投递的实现是一端发送的消息临时存放在 Redis\n" +
                                "\n" +
                                "中，另一端拉取的生产消费模型。\n" +
                                "\n" +
                                "这个模型的做法导致需要以一种高频率的方式来轮询 Redis 遍历属于自己连接的关联会话消息。\n" +
                                "\n" +
                                "\n" ,
                        "确定", new OnLeftListener() {
                    @Override
                    public void onLeftClick(String str) {

                    }
                }, "取消", new OnRightListener() {
                    @Override
                    public void onRightClick(String str) {

                    }
                }).show();
                break;
            case R.id.show_2btn:
//3
                DialogCreator.show2BtnDialog(this, "show2BtnDialog msg",
                        "确定", new OnLeftListener() {
                            @Override
                            public void onLeftClick(String str) {

                            }
                        }, "取消", new OnRightListener() {
                            @Override
                            public void onRightClick(String str) {

                            }
                        }).show();
                break;

//4
            case R.id.show_edit_title_1btn:

                DialogCreator.showEditTitle1BtnDialog(this, "showEditTitle1BtnDialog", "showEditTitle1BtnDialog hint", "确定", new OnLeftListener() {
                    @Override
                    public void onLeftClick(String str) {

                    }
                }).show();
                break;
            case R.id.show_image_header_1btn:

                DialogCreator.showImageHeader1BtnDialog(this, url, "showImageHeader1BtnDialog", "showEditTitle1BtnDialog msg", "确定", new OnLeftListener() {
                    @Override
                    public void onLeftClick(String str) {

                    }
                }).show();
                break;
            case R.id.show_logo_header_1btn:

                DialogCreator.showLogoHeader1BtnDialog(this, url, "利宝特权说明", "showLogoHeader1BtnDialog ", "确定", new OnLeftListener() {
                    @Override
                    public void onLeftClick(String str) {

                    }
                }).show();
                break;
            case R.id.show_card_2btn:

                DialogCreator.showCard2BtnDialog(this, "showCard2BtnDialog msg", url, "title暗示法大是大非未完全范德萨发的份额范德萨", "70.7包邮(100.7-30)", "去看看", new OnLeftListener() {
                    @Override
                    public void onLeftClick(String str) {

                    }
                }, "换一个", new OnRightListener() {
                    @Override
                    public void onRightClick(String str) {

                    }
                }).show();

                break;
            case R.id.show_my_btn:
                MyDialogDialog myDialogDialog=new MyDialogDialog(this);
                myDialogDialog
                        .setMyView("My View")
                        .setDialogCancelable(true)
                        .setTitle("我的title")
                        /*.setConfirmBtn("点击我的view", new OnLeftListener() {
                            @Override
                            public void onLeftClick(String str) {
                                Toast.makeText(DialogTestActivity.this,"点击了我的view setConfirmBtn",Toast.LENGTH_SHORT).show();

                            }
                        })*/.setRightBtn("lala", new OnRightListener() {
                    @Override
                    public void onRightClick(String str) {
                        Toast.makeText(DialogTestActivity.this,"lala",Toast.LENGTH_SHORT).show();

                    }
                })
                .start();

                myDialogDialog.show();



                break;

            default:
                break;
        }


    }
}
