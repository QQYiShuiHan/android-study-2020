package com.vincent.android_study_2020.view;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vincent.android_study_2020.R;


/**
 * Time: 2020/8/27
 * Author: HQQ
 * Description:
 */

public class RevealAnimationLayout extends FrameLayout {

    private static final String TAG = RevealAnimationLayout.class.getSimpleName();

    public Path mClipPath;                 // 剪裁区域路径
    public Path mOpClipPath;
    public Paint mPaint;                   // 画笔
    public Region mAreaRegion;             // 内容区域
    public RectF mLayer;                   // 画布图层大小

    public ValueAnimator.AnimatorUpdateListener mUpdateListener;
    public Animator.AnimatorListener mAnimatorListener;
    public ValueAnimator mStartingAnimator;
    public float mAnimatorValue;
    private int defaultDuration = 4000;
    // 实现渐变效果
    private int layerId;
    private LinearGradient linearGradient;
    private boolean isFadeOut = false;


    public enum AnimaType {
        FadeIn, FadeOut
    }

    public AnimaType mAnimaType = AnimaType.FadeIn;


    public RevealAnimationLayout(@NonNull Context context) {
        this(context, null);
    }

    public RevealAnimationLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RevealAnimationLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RevealAnimationLayout);
        boolean isReveal = typedArray.getBoolean(R.styleable.RevealAnimationLayout_content_is_reveal, true);
        if (isReveal) {
            mAnimaType = AnimaType.FadeOut;
        }
        initAttr();
    }

    private void initAttr() {
        Log.d(TAG, " onSizeChanged ");
        mLayer = new RectF();
        mClipPath = new Path();
        mOpClipPath = new Path();
        mAreaRegion = new Region();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

        setWillNotDraw(false);

        initAnimator();

        setLayerType(LAYER_TYPE_HARDWARE, mPaint);
    }

    /**
     * 初始化动画类
     */

    private void initAnimator() {
        Log.d(TAG, " onSizeChanged ");
        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //拿到动画的执行的百分比mAnimatorValue
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };

        mStartingAnimator = new ValueAnimator().setDuration(defaultDuration);
        mStartingAnimator.setInterpolator(new AccelerateInterpolator());
        mStartingAnimator.addUpdateListener(mUpdateListener);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, " onSizeChanged ");
        super.onSizeChanged(w, h, oldw, oldh);
        mLayer.set(0, 0, w, h);
    }


    /**
     * 如果没有设置背景是不会调用这个的
     *
     * @param canvas
     */

    @Override
    public void draw(Canvas canvas) {
        Log.d(TAG, " draw ");
        canvas.save();
        super.draw(canvas);
        if (AnimaType.FadeOut == mAnimaType) {
            onClipDrawFadeOut(canvas);
        } else {
            onClipDrawFadeIn(canvas);
        }
        canvas.restore();
    }

    private void fadeDraw(){

    }


    public void onClipDraw(Canvas canvas) {
        Log.d(TAG, " onClipDraw ");
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        mOpClipPath.reset();
        mOpClipPath.addRect(0, 0, mLayer.width(), mLayer.height(), Path.Direction.CW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mOpClipPath.op(mClipPath, Path.Op.DIFFERENCE);
        }
        canvas.drawPath(mOpClipPath, mPaint);

    }


    public void onClipDrawFadeOut(Canvas canvas) {
        isFadeOut = true;
        Log.d(TAG, " onClipDraw ");
        RectF rectF = new RectF(-getWidth(), 0, getWidth() * 2, getHeight() * 2);
        // dst_in 模式，实现底层透明度随上层透明度进行同步显示（即上层为透明时，下层就透明，并不是上层覆盖下层)
        final Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mPaint.setXfermode(xfermode);
        // 当linearGradient为空即第一次绘制 或 Recyclerview宽度发生改变时，重新计算透明位置

        // 透明位置从最后一个 itemView 的一半处到 Recyclerview 的最右边
        linearGradient = new LinearGradient(0, 0, rectF.right / 2, 0, new int[]{0, Color.BLACK}, null, Shader.TileMode.CLAMP);

        mPaint.setXfermode(xfermode);
        mPaint.setShader(linearGradient);
        canvas.translate(getWidth() * 2 * mAnimatorValue - getWidth(), 0);
        canvas.drawRect(rectF, mPaint);
        layerId = canvas.saveLayer(0.0f, 0.0f, (float) this.getWidth(), (float) this.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }

    public void onClipDrawFadeIn(Canvas canvas) {
        isFadeOut = false;
        Log.d(TAG, " onClipDraw ");
        RectF rectF = new RectF(0, 0, getWidth() * 2, getHeight());
        // dst_in 模式，实现底层透明度随上层透明度进行同步显示（即上层为透明时，下层就透明，并不是上层覆盖下层)
        final Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mPaint.setXfermode(xfermode);
        // 当linearGradient为空即第一次绘制 或 Recyclerview宽度发生改变时，重新计算透明位置

        // 透明位置从最后一个 itemView 的一半处到 Recyclerview 的最右边
        linearGradient = new LinearGradient(0, 0, rectF.right / 2, 0, new int[]{Color.BLACK, 0}, null, Shader.TileMode.CLAMP);

        mPaint.setXfermode(xfermode);
        mPaint.setShader(linearGradient);
        canvas.translate(getWidth() * 2 * mAnimatorValue - getWidth(), 0);
        canvas.drawRect(rectF, mPaint);
        layerId = canvas.saveLayer(0.0f, 0.0f, (float) this.getWidth(), (float) this.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }


    @Override
    public void invalidate() {
        super.invalidate();
    }


    @Override
    protected void onDetachedFromWindow() {
        Log.d(TAG, " onClipDraw ");
        super.onDetachedFromWindow();
        if (mStartingAnimator != null) {
            mStartingAnimator.cancel();
            mStartingAnimator.removeAllUpdateListeners();
            mStartingAnimator.removeAllListeners();
        }
    }

    /**
     * 开启动画
     *
     * @param animaType 动画类型
     */

    public void startAnimal(AnimaType animaType) {
        this.mAnimaType = animaType;
        setVisibility(View.VISIBLE);
        mStartingAnimator.cancel();
        mStartingAnimator.setFloatValues(0, 1);
        mStartingAnimator.start();
    }

}
