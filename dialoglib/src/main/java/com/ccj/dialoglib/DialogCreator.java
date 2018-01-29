package com.ccj.dialoglib;

import android.content.Context;

import com.ccj.dialoglib.dialog.BaseDialog;
import com.ccj.dialoglib.dialog.imp.CommonCardDialog;
import com.ccj.dialoglib.dialog.imp.CommonImgHeaderDialog;
import com.ccj.dialoglib.dialog.imp.CommonLogoHeaderDialog;
import com.ccj.dialoglib.dialog.imp.CommonNormalDialog;
import com.ccj.dialoglib.listener.OnLeftListener;
import com.ccj.dialoglib.listener.OnRightListener;


/**
 *
 * 通用弹框 生产类
 *
 * 这种写法不会造成内存泄漏。为什么不会呢？要想造成内存泄漏，你的工具类对象本身要持有指向传入对象的引用才行！但是当你的业务方法调用工具类的静态方法时，
 * 会生产一个称为方法栈帧的东西（每次方法调用，JVM都会生成一个方法栈帧），当方法调用结束返回的时候，当前方法栈帧就已经被弹出了并且被释放掉了。 整个过程结束时，工具类对象本身并不会持有传入对象的引用。
 * Created by chenchangjun on 17/10/10.
 */

public class DialogCreator {



    /**
     * 无标题,单btn弹窗
     *
     * @param mActivity
     * @param msg
     * @param strLeft
     * @param leftListener
     */
    public static BaseDialog showConfirmDialog(Context mActivity, String msg, String strLeft, OnLeftListener leftListener) {
        CommonNormalDialog confrimDialog = new CommonNormalDialog(mActivity);
        confrimDialog.setDialogType(DialogConfig.TYPE_HAS_NO_HEADER)
                .setContent(msg)
                .setContentGravity(DialogConfig.TYPE_GRIVITY_CENTER)
                .setConfirmBtn(strLeft, leftListener)
                .start();
        return confrimDialog;
    }



    /**
     * 无标题,单btn弹窗
     *
     * @param mActivity
     * @param msg
     * @param strLeft
     * @param leftListener
     */
    public static BaseDialog showTitleConfirmDialog(Context mActivity, String title, String msg, String strLeft, OnLeftListener leftListener) {
        CommonNormalDialog confrimDialog = new CommonNormalDialog(mActivity);
        confrimDialog.setDialogType(DialogConfig.TYPE_HAS_HEADER)
                .setTitle(title)
                .setContent(msg)
                .setContentGravity(DialogConfig.TYPE_GRIVITY_CENTER)
                .setConfirmBtn(strLeft, leftListener)
                .start();
        return confrimDialog;
    }

    /**
     * 无标题,双btn弹窗
     *
     * @param mActivity
     * @param msg
     * @param strLeft
     * @param leftListener
     * @param strRight
     * @param onRightListener
     */
    public static BaseDialog show2BtnDialog(Context mActivity, String msg,
                                            String strLeft, OnLeftListener leftListener, String strRight, OnRightListener onRightListener) {
        CommonNormalDialog confrimDialog = new CommonNormalDialog(mActivity);
        confrimDialog.setDialogType(DialogConfig.TYPE_HAS_NO_HEADER)
                .setContent(msg)
                .setContentGravity(DialogConfig.TYPE_GRIVITY_CENTER)
                .setLeftBtn(strLeft, leftListener)
                .setRightBtn(strRight, onRightListener)
                .start();

        return confrimDialog;
    }

    /**
     * 有标题,双btn弹窗
     *
     * @param mActivity
     * @param title
     * @param msg
     * @param strLeft
     * @param leftListener
     * @param strRight
     * @param onRightListener
     */
    public static BaseDialog showTitle2BtnDialog(Context mActivity, String title, String msg, String strLeft, OnLeftListener leftListener, String strRight, OnRightListener onRightListener) {
        CommonNormalDialog confrimDialog = new CommonNormalDialog(mActivity);
        confrimDialog
                .setDialogType(DialogConfig.TYPE_HAS_NO_HEADER)
                .setTitle(title)
                .setContent(msg)
                .setLeftBtn(strLeft, leftListener)
                .setRightBtn(strRight, onRightListener)
                .start();

        return confrimDialog;
    }

    /**
     * 有标题,单btn弹窗
     *
     * @param mActivity
     * @param title
     * @param msg
     * @param strBtn
     * @param leftListener
     */
    public static BaseDialog showTitle1BtnDialog(Context mActivity, String title, String msg, String strBtn, OnLeftListener leftListener) {
        CommonNormalDialog confrimDialog = new CommonNormalDialog(mActivity);
        confrimDialog
                .setCancelable(true)
                .setDialogType(DialogConfig.TYPE_HAS_NO_HEADER)
                .setTitle(title)
                .setContent(msg)
                .setLeftBtn(strBtn, leftListener)
                .start();

        return confrimDialog;
    }


    /**
     * 有标题,单btn弹窗 ,带输入dialog
     *
     * @param mActivity
     * @param title
     * @param ediHhintmsg
     * @param strLeft
     * @param leftListener
     */
    public static BaseDialog showEditTitle1BtnDialog(Context mActivity, String title, String ediHhintmsg, String strLeft, OnLeftListener leftListener) {
        CommonNormalDialog confrimDialog = new CommonNormalDialog(mActivity);
        confrimDialog
                .setDialogType(DialogConfig.TYPE_HAS_NO_HEADER)
                .setTitle(title)
                .setShowEditHint(ediHhintmsg)
                .setConfirmBtn(strLeft, leftListener)
                .start();

        return confrimDialog;
    }


    /**
     * 有头部图片背景,有标题,btn
     *
     * @param mActivity
     * @param imgURL
     * @param title
     * @param msg
     * @param strLeft
     * @param leftListener
     */
    public static BaseDialog showImageHeader1BtnDialog(Context mActivity, String imgURL, String title, String msg, String strLeft, OnLeftListener leftListener) {
        CommonImgHeaderDialog dialog = new CommonImgHeaderDialog(mActivity);
        dialog
                .setHeaderUrl(imgURL)
                .setDialogType(DialogConfig.TYPE_HAS_HEADER)
                .setTitle(title)
                .setContent(msg)
                .setConfirmBtn(strLeft, leftListener)
                .start();

        return dialog;
    }

    /**
     * 带logo 头部的弹窗
     *
     * @param mActivity
     * @param logoURL
     * @param msg
     * @param strLeft
     * @param leftListener
     */
    public static BaseDialog showLogoHeader1BtnDialog(Context mActivity, String logoURL, String title, String msg, String strLeft, OnLeftListener leftListener) {
        CommonLogoHeaderDialog confrimDialog = new CommonLogoHeaderDialog(mActivity);
        confrimDialog
                .setLOGOUrl(logoURL)
                .setDialogType(DialogConfig.TYPE_HAS_HEADER)
                .setTitle(title)
                .setContent(msg)
                .setConfirmBtn(strLeft, leftListener)
                .start();

        return confrimDialog;
    }

    /**
     * 带好价卡的dialog
     *
     * @param mActivity
     * @param title
     * @param cardUrl
     * @param cardTitle
     * @param cardPrice
     * @param strLeft
     * @param leftListener
     * @param strRight
     * @param onRightListener
     */
    public static BaseDialog showCard2BtnDialog(Context mActivity, String title, String cardUrl, String cardTitle, String cardPrice, String strLeft,
                                                OnLeftListener leftListener, String strRight, OnRightListener onRightListener) {
        CommonCardDialog confrimDialog = new CommonCardDialog(mActivity);
        confrimDialog
                .setTitle(title)
                .setCardPicUrl(cardUrl)
                .setCardTitle(cardTitle)
                .setCardPrice(cardPrice)
                .setLeftBtn(strLeft, leftListener)
                .setRightBtn(strRight, onRightListener)
                .start();

        return confrimDialog;

    }


}
