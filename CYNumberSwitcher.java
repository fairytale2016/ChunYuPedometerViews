package com.fairytale110.mycustomviews.Widget.CY;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.fairytale110.mycustomviews.R;

/**
 * Created by   fairytale110
 * Creat date   2016/9/5 16:55
 * Copy Right   www.xkyiliao.com
 * function    数字 切换控件 继承 android.widget.TextSwitcher
 * 
 */
public class CYNumberSwitcher extends TextSwitcher implements ViewSwitcher.ViewFactory, CYAnimator {


    private ValueAnimator mValueAnimator;
    private long animatorTime = 1500L;  //动画时长
    private int dataToShow = 0;             //要显示的值

    public CYNumberSwitcher(Context paramContext) {
        this(paramContext,null);
    }

    public CYNumberSwitcher(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        setFactory(this);
    }

    private void d() {
        setFactory(this);
    }

    @Override
    public final void reset() {
        if ((Build.VERSION.SDK_INT >= 11) && (this.mValueAnimator != null)) {
            this.mValueAnimator.cancel();
        }
        setText("0");
    }

    /**
     * 设置要显示的值
     * @param paramInt
     */
    public final void setDataToShow(int paramInt) {
        this.dataToShow = paramInt;
        setText(String.valueOf(this.dataToShow));
    }

    @Override
    public final void setShowTime(long paramLong) {
        this.animatorTime = paramLong;
    }

    @Override
    public final void setShowData() {
        setText(String.valueOf(this.dataToShow));
    }

    @Override
    public final void startAnim() {
        if (Build.VERSION.SDK_INT < 11) {
            setShowData();
            return;
        }
        this.mValueAnimator = ValueAnimator.ofInt(new int[]{0, this.dataToShow});
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator) {
                CYNumberSwitcher.this.setText(String.valueOf(paramAnonymousValueAnimator.getAnimatedValue()));
            }
        });
        this.mValueAnimator.setDuration(this.animatorTime);
        this.mValueAnimator.start();
    }

    public View makeView() {
        TextView localTextView = new TextView(getContext());
        localTextView.setTextSize(1, 34.0F);
        localTextView.setTextColor(getResources().getColor(R.color.text_black_dau));
        localTextView.setGravity(17);
        return localTextView;
    }
}
