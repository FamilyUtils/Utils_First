/*
 *
 *  * Copyright (c) 2011-2015. ShenZhen iBOXPAY Information Technology Co.,Ltd.
 *  *
 *  * All right reserved.
 *  *
 *  * This software is the confidential and proprietary
 *  * information of iBoxPay Company of China.
 *  * ("Confidential Information"). You shall not disclose
 *  * such Confidential Information and shall use it only
 *  * in accordance with the terms of the contract agreement
 *  * you entered into with iBoxpay inc.
 *
 */

package com.iboxpay.platform.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.iboxpay.platform.R;

/**
 * Created by yangzhiying on 16/8/25.
 */
public class DialogUtil {

    private  TextView mMobile;

    private  TextView mCancle;

    private  TextView mEnsure;

    private  View.OnClickListener mCancleListenr;

    private  View.OnClickListener mEnsureListenr;

    public TextView getmMobile() {
        return mMobile;
    }

    public TextView getmCancle() {
        return mCancle;
    }

    public TextView getmEnsure() {
        return mEnsure;
    }

    public View.OnClickListener getmCancleListenr() {
        return mCancleListenr;
    }

    public void setmCancleListenr(View.OnClickListener mCancleListenr) {
        this.mCancleListenr = mCancleListenr;
    }

    public View.OnClickListener getmEnsureListenr() {
        return mEnsureListenr;
    }

    public void setmEnsureListenr(View.OnClickListener mEnsureListenr) {
        this.mEnsureListenr = mEnsureListenr;
    }

    public  Dialog creatDialog(Activity context, int width) {

        Dialog builder = new Dialog(context, R.style.MyAlertDialogStyle);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View view = View.inflate(context, R.layout.layout_call_dialog, null);

        mMobile = (TextView) view.findViewById(R.id.tv_dialoge_mobile);
        mCancle = (TextView) view.findViewById(R.id.tv_dialog_cancle);
        mEnsure = (TextView) view.findViewById(R.id.tv_dialog_sure);

        builder.setContentView(view);

        if (mCancleListenr != null) {
            mCancle.setOnClickListener(mCancleListenr);
        }

        if (mEnsureListenr != null) {
            mEnsure.setOnClickListener(mEnsureListenr);
        }

        Window dialogWindow = builder.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);

        lp.width = Util.convertDIP2PX(context, width);
        dialogWindow.setAttributes(lp);

        return builder;
    }
}

---------------------------我是分割线--------------------------------------
<!-- AlertDialog去掉背景 -->
<style name="MyAlertDialogStyle"
parent="Theme.AppCompat.Light.Dialog.Alert">
<item name="background">@null</item>
</style>
