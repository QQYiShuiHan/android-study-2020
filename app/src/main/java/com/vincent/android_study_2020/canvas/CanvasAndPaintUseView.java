package com.vincent.android_study_2020.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.vincent.android_study_2020.R;

public class CanvasAndPaintUseView extends View {


    public CanvasAndPaintUseView(Context context) {
        this(context, null);
    }

    public CanvasAndPaintUseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasAndPaintUseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }


    private void init() {
        LinearLayout view = new LinearLayout(getContext());
        view.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        view.addView(this);
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
        super.onDraw(canvas);


        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);// 设置红色

        canvas.drawText("画圆：", 10, 20, paint);// 画文本
        canvas.drawCircle(60, 20, 10, paint);// 小圆
        paint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        canvas.drawCircle(120, 20, 20, paint);// 大圆

        canvas.drawText("画线及弧线：", 10, 60, paint);
        paint.setColor(Color.GREEN);// 设置绿色
        canvas.drawLine(60, 40, 100, 40, paint);// 画线
        canvas.drawLine(110, 40, 190, 80, paint);// 斜线
        //画笑脸弧线
        paint.setStyle(Paint.Style.STROKE);//设置空心
        RectF oval1 = new RectF(150, 20, 180, 40);
        canvas.drawArc(oval1, 180, 180, false, paint);//小弧形
        oval1.set(190, 20, 220, 40);
        canvas.drawArc(oval1, 180, 180, false, paint);//小弧形
        oval1.set(160, 30, 210, 60);
        canvas.drawArc(oval1, 0, 180, false, paint);//小弧形

        canvas.drawText("画矩形：", 10, 80, paint);
        paint.setColor(Color.GRAY);// 设置灰色
        paint.setStyle(Paint.Style.FILL);//设置填满
        canvas.drawRect(60, 60, 80, 80, paint);// 正方形
        canvas.drawRect(60, 90, 160, 100, paint);// 长方形

        canvas.drawText("画扇形和椭圆:", 10, 120, paint);

        Shader mShader = new LinearGradient(0, 0, 100, 100,
                new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                        Color.LTGRAY}, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
        paint.setShader(mShader);
        // paint.setColor(Color.BLUE);
        RectF oval2 = new RectF(60, 100, 200, 240);// 设置个新的长方形，扫描测量
        canvas.drawArc(oval2, 200, 130, true, paint);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        // 画椭圆，把oval改一下
        oval2.set(210, 100, 250, 130);
        canvas.drawOval(oval2, paint);

        canvas.drawText("画三角形：", 10, 200, paint);
        // 绘制这个三角形,你可以绘制任意多边形
        Path path = new Path();
        path.moveTo(80, 200);// 此点为多边形的起点
        path.lineTo(120, 250);
        path.lineTo(80, 250);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, paint);

        // 你可以绘制很多任意多边形，比如下面画六连形
        paint.reset();//重置
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);//设置空心
        Path path1 = new Path();
        path1.moveTo(180, 200);
        path1.lineTo(200, 200);
        path1.lineTo(210, 210);
        path1.lineTo(200, 220);
        path1.lineTo(180, 220);
        path1.lineTo(170, 210);
        path1.close();//封闭
        canvas.drawPath(path1, paint);


        //画圆角矩形
        paint.setStyle(Paint.Style.FILL);//充满
        paint.setColor(Color.LTGRAY);
        paint.setAntiAlias(true);// 设置画笔的锯齿效果
        canvas.drawText("画圆角矩形:", 10, 260, paint);
        RectF oval3 = new RectF(80, 260, 200, 300);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 20, 15, paint);//第二个参数是x半径，第三个参数是y半径

        //画贝塞尔曲线
        canvas.drawText("画贝塞尔曲线:", 10, 310, paint);
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        Path path2 = new Path();
        path2.moveTo(100, 320);//设置Path的起点
        path2.quadTo(150, 310, 170, 400); //设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(path2, paint);//画出贝塞尔曲线

        //画点
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("画点：", 10, 390, paint);
        canvas.drawPoint(60, 390, paint);//画一个点
        canvas.drawPoints(new float[]{60, 400, 65, 400, 70, 400}, paint);//画多个点

        //画图片，就是贴图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_2);
        canvas.drawBitmap(bitmap, 250, 360, paint);

    }
}
