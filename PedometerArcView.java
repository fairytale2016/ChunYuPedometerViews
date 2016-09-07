package com.fairytale110.mycustomviews.Widget.CY;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.fairytale110.mycustomviews.R;

/**
 * Created by   fairytale110
 * Creat date   2016/9/5 16:54
 * Copy Right   www.xkyiliao.com
 * function  环形 刻度计数器
 * me.chunyu.Pedometer.Widget.PedometerArcView
 */
@SuppressWarnings("all")
public class PedometerArcView extends RelativeLayout implements CYAnimator {


    private static final int a = 72;
    private Paint b;
    private float torusWidth = 0.0F;
    private float d = 5.0F;
    private float e = 4.0F;
    private int backgroundColor = Color.parseColor("#E0E0E0");
    private int processColor = Color.parseColor("#F36C60");;
    private int startIndex = 0;
    private int processIndex = 0;
    private int j = 0;
    private ValueAnimator k;
    private long showTime = 1500L;
    private Context mContext;
    private  AttributeSet paramAttributeSet;

    public PedometerArcView(Context paramContext) {
        this(paramContext, null);
    }

    public PedometerArcView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public PedometerArcView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        mContext = paramContext;
        this.paramAttributeSet = paramAttributeSet;
        inti();
        TypedArray typedarray = mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PedometerArcView, paramInt, 0);
        this.torusWidth = typedarray.getDimensionPixelSize(0, (int) this.torusWidth);
        this.b.setStrokeWidth(this.torusWidth);
        this.backgroundColor = typedarray.getColor(1, this.backgroundColor);
        this.processColor = typedarray.getColor(2, this.processColor);
        this.startIndex = typedarray.getInt(3, this.startIndex);
        this.processIndex = typedarray.getInt(4, this.processIndex);
        this.j = this.processIndex;
        typedarray.recycle();
    }

    private void setorusWidth(float paramFloat) {
        this.torusWidth = paramFloat;
        this.b.setStrokeWidth(this.torusWidth);
        invalidate();
    }

    private void a(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        TypedArray typedarray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PedometerArcView, paramInt, 0);
        this.torusWidth = typedarray.getDimensionPixelSize(0, (int) this.torusWidth);
        this.b.setStrokeWidth(this.torusWidth);
        this.backgroundColor = typedarray.getColor(1, this.backgroundColor);
        this.processColor = typedarray.getColor(2, this.processColor);
        this.startIndex = typedarray.getInt(3, this.startIndex);
        //this.processIndex = typedarray.getInt(4, this.processIndex);
        this.processIndex = paramInt;
        this.j = this.processIndex;
        typedarray.recycle();
    }

    private void c(int paramInt) {
        this.backgroundColor = paramInt;
    }

    private void inti() {
        setWillNotDraw(false);
        this.torusWidth = TypedValue.applyDimension(1, 15.0F, getResources().getDisplayMetrics());
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setStrokeWidth(this.torusWidth);
        this.b.setStyle(Paint.Style.STROKE);
    }

    private void d(int paramInt) {
        this.processColor = paramInt;
    }

    @Override
    public final void reset() {
        if ((Build.VERSION.SDK_INT >= 11) && (this.k != null)) {
            this.k.cancel();
        }
        this.j = 0;
        invalidate();
    }

    public final void a(int paramInt) {
        this.startIndex = paramInt;
        invalidate();
    }

    @Override
    public final void setShowTime(long paramLong) {
        this.showTime = paramLong;
    }

    @Override
    public final void setShowData() {
        this.j = this.processIndex;
        invalidate();
    }

    public final void b(int paramInt) {
        int m = paramInt;
        if (paramInt > 72) {
            m = 72;
        }
        this.processIndex = m;
        this.j = m;
        invalidate();
    }

    @Override
    public final void startAnim() {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        this.k = ValueAnimator.ofInt(new int[]{0, this.processIndex});
        this.k.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator) {
                a(mContext, paramAttributeSet,((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
                PedometerArcView.this.invalidate();
            }
        });
        this.k.setDuration(this.showTime);
        this.k.start();
    }

    @Override
    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);
        float f1 = this.torusWidth / 2.0F;
        RectF localRectF = new RectF(f1, f1, getWidth() - f1, getHeight() - f1);
        this.b.setColor(this.backgroundColor);
        int m = 0;
        while (m < 72) {
            paramCanvas.drawArc(localRectF, m * this.d - 90.0F, this.e, false, this.b);
            m += 1;
        }
        m = this.startIndex;
        int n = this.j;
        int i1 = this.startIndex;
        this.b.setColor(this.processColor);
        while (m < n + i1) {
            paramCanvas.drawArc(localRectF, m % 72 * this.d - 90.0F, this.e, false, this.b);
            m += 1;
        }
    }
}
