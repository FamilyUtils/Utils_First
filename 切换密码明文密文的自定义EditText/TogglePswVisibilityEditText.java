package com.glimmer.newktb.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import com.glimmer.newktb.R;

/**
 * Created by yangzhiying on 16/9/1.
 */
public class TogglePswVisibilityEditText extends EditText {

    private Drawable mRightDrawable;

    private boolean mVisible = false;

    public TogglePswVisibilityEditText(Context context) {
        this(context, null);
    }

    //在xml文件中调用，会调用这个构造函数
    public TogglePswVisibilityEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

//    public TogglePswVisibilityEditText(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }

    /**
     * 当EditText 有rightDrawable 获取到它  否则 填充一个默认的drawable
     */
    private void init() {
        //left top right bottom
        Drawable[] drawables = this.getCompoundDrawables();
        mRightDrawable = drawables[2];
        if (mRightDrawable == null) {
            mRightDrawable = getResources().getDrawable(R.drawable.ic_psw_invis);
        }
    }

    /**
     * 根据点击的坐标判断是否在rightDrawable上面
     *
     * [EditText的width - rightDrawable的marginRight - rightDrawable的width,EditText的width -
     * rightDrawable]
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            if (mRightDrawable != null) {
                boolean xFlag = false;
                //宝宝已经疯掉了
// 方案一：               xFlag = (event.getX() > (getWidth() - getTotalPaddingRight())) && (event.getX() < (getWidth() - getPaddingRight()));
                xFlag = (event.getX() > (getWidth() - getTotalPaddingRight())) && (event.getX() < (getWidth() - getPaddingRight()));
                Log.d(TogglePswVisibilityEditText.class.getSimpleName(), "onTouchEvent: " + xFlag);
                if (xFlag) {
                    mVisible = !mVisible;
                    if (mVisible) {
                        mRightDrawable = getResources().getDrawable(R.drawable.ic_psw_vis);
                        //换成明文
//                        this.setInputType(InputType.TYPE_CLASS_TEXT);
                        this.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        mRightDrawable = getResources().getDrawable(R.drawable.ic_psw_invis);
                        //换成密文  被注释掉的方法不好使。。。。。
//                        this.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        this.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    //将光标定位到指定的位置
                    CharSequence text = this.getText();
                    if (text instanceof Spannable) {
                        Spannable spanText = (Spannable) text;
                        Selection.setSelection(spanText, text.length());
                    }
                    //设置他的绘画范围。否则无法替换，显示不出来
                    mRightDrawable.setBounds(0, 0, 80, 60);
                    //重新设置mRightDrawble
                    this.setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], mRightDrawable, getCompoundDrawables()[3]);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 为什么之前使用三个构造函数，并用this的方式调用，会获取不到焦点，而且无法输入呢？
     */
    /*
        //在xml文件中调用，会调用这个构造函数
    public TogglePswVisibilityEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

      public EditText(Context context, AttributeSet attrs) {
        this(context, attrs, com.android.internal.R.attr.editTextStyle);
    }

    在父类里面，传入的是一个系统自带的bg,但是在我写的这个构造穿的是0，所以导致了这个问题。
     */

}
