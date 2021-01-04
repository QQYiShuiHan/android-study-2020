package com.vincent.android_study_2020.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasApi extends View {

    /**
     * 构建矩形区域, 类型：int
     * Rect(int left,int top,int right,int bottom)
     */
    private Rect mRect = new Rect();


    /**
     * 构建矩形区域, 类型：float
     * RectF（float left,float top,float right,float bottom）
     */
    private RectF mRectF = new RectF();

    /**
     * 画笔
     * 详细使用详见：PaintApi
     */
    private Paint mPaint;
    private Canvas canvas;
    private Canvas mCanvas;


    public CanvasApi(Context context) {
        this(context, null);
    }

    public CanvasApi(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasApi(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(mCanvas);

        mCanvas = canvas;

    }


    // 绘制区域，参数一为RectF一个区域
    public void drawRect(RectF rect, Paint paint) {
        mCanvas.drawRect(rect, paint);
    }

    // 绘制一个路径，参数一为Path路径对象
    public void drawPath(Path path, Paint paint) {
        mCanvas.drawPath(path, paint);
    }

    // 贴图，参数一就是我们常规的Bitmap对象，参数二是源区域(这里是bitmap)，
    // 参数三是目标区域(应该在canvas的位置和大小)，参数四是Paint画刷对象，
    // 因为用到了缩放和拉伸的可能，当原始Rect不等于目标Rect时性能将会有大幅损失
    public void drawBitmap(Bitmap bitmap, Rect src, Rect dst, Paint paint) {
        mCanvas.drawBitmap(bitmap, src, dst, paint);
    }

    // 画线，参数一起始点的x轴位置，参数二起始点的y轴位置，参数三终点的x轴水平位置，
    // 参数四y轴垂直位置，最后一个参数为Paint 画刷对象。
    public void drawLine(float startX, float startY, float stopX, float stopY, Paint paint) {
        mCanvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    // 画点，参数一水平x轴，参数二垂直y轴，第三个参数为Paint对象。
    public void drawPoint(float x, float y, Paint paint) {

        mCanvas.drawPoint(x, y, paint);
    }

    // 渲染文本，Canvas类除了上面的还可以描绘文字，参数一是String类型的文本，
    // 参数二x轴，参数三y轴，参数四是Paint对象。
    public void drawText(String text, float x, float y, Paint paint) {
        mCanvas.drawText(text, x, y, paint);
    }

    // 画椭圆，参数一是扫描区域，参数二为paint对象；
    public void drawOval(RectF oval, Paint paint) {
        mCanvas.drawOval(oval, paint);
    }

    // 绘制圆，参数一是中心点的x轴，参数二是中心点的y轴，参数三是半径，参数四是paint对象；
    public void drawCircle(float cx, float cy, float radius, Paint paint) {
        mCanvas.drawCircle(cx, cy, radius, paint);
    }

    // 画弧， 参数一是RectF对象，一个矩形区域椭圆形的界限用于定义在形状、大小、电弧，参数二是起始角(度)在电弧的开始，
    // 参数三扫描角(度)开始顺时针测量的，参数四是如果这是真的话,包括椭圆中心的电弧,并关闭它,
    // 如果它是假这将是一个弧线,参数五是Paint对象；
    public void drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        mCanvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint);
    }


}
