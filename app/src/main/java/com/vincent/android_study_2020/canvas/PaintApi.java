package com.vincent.android_study_2020.canvas;

import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Shader;
import android.graphics.Typeface;


public class PaintApi {

    private static final Paint mPaint = new Paint();


    // 设置画笔的颜色。四个参数分别代表透明度和颜色的RGB值，取值范围为0-255
    public static void setPaintARGB(int a, int r, int g, int b) {
        mPaint.setARGB(a, r, g, b);
    }

    //设置画笔的Alpha值。范围为0-255。0代表完全透明，255代表完全不透明
    public static void setPaintAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    // 设置画笔的锯齿效果。true代表抗锯齿，false代表不抗锯齿
    public static void setPaintAntiAlias(boolean antiAlias) {
        mPaint.setAntiAlias(antiAlias);
    }


    // 设置画笔的颜色。参数为int类型。
    public static void setColor(int color) {
        mPaint.setColor(color);
    }

    // 设置颜色过滤器，可以在绘制颜色时实现不用颜色的变换效果
    public static void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    //设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰.
    public static void setDither() {
        mPaint.setDither(true);
    }

    // 模拟实现粗体文字，设置在小字体上效果会非常差
    public static void setFakeBoldText(boolean fakeBoldText) {
        mPaint.setFakeBoldText(fakeBoldText);
    }

    // 如果该项设置为true，则图像在动画进行中会滤掉对Bitmap图像的优化操作，
    // 加快显示速度，本设置项依赖于dither和xfermode的设置
    public static void setFilterBitmap(boolean filterBitmap) {
        mPaint.setFilterBitmap(filterBitmap);
    }

    // 根据flag值来对画笔进行设置。例如这里设置的是抗锯齿
    public static void setFlags(int flags) {
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    // 设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，如滤化，立体等
    public static void setMaskFilter(MaskFilter maskFilter) {
        mPaint.setMaskFilter(maskFilter);
    }

    // 设置绘制路径的效果，如点画线等
    public static void setPathEffect(PathEffect pathEffect) {
        mPaint.setPathEffect(pathEffect);
    }

    //设置图像效果，使用Shader可以绘制出各种渐变效果
    public static void setShader(Shader shader) {
        mPaint.setShader(shader);
    }

    // 在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，
    // dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色
    public static void setShadowLayer(float radius, float dx, float dy, int color) {
        mPaint.setShadowLayer(radius, dx, dy, color);
    }

    // 设置带有删除线的效果
    public static void setStrikeThruText(boolean strikeThruText) {
        mPaint.setStrikeThruText(strikeThruText);
    }

    // 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，
    // 如圆角形样式Cap.ROUND,或方形样式Cap.SQUARE。这个会影响画笔的始末端
    public static void setStrokeCap(Paint.Cap strokeCap) {
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    // 设置绘制时各图形的结合方式，如平滑效果等
    public static void setStrokeJoin(Paint.Join join) {
        mPaint.setStrokeJoin(join);

    }

    // 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度即宽度。
    public static void setStrokeWidth(float width) {
        mPaint.setStrokeWidth(width);
    }

    // 设置画笔的样式，为FILL(实心的)，FILL_OR_STROKE，或STROKE（空心的）
    public static void setPaintARGB(Paint.Style style) {
        mPaint.setStyle(style);
    }

    // 设置该项为true，将有助于文本在LCD屏幕上的显示效果
    public static void setSubpixelText(boolean subpixelText) {
        mPaint.setSubpixelText(subpixelText);
    }

    // 设置绘制文字的对齐方向
    public static void setTextAlign(Paint.Align align) {
        mPaint.setTextAlign(align);
    }

    //设置绘制文字x轴的缩放比例，可以实现文字的拉伸的效果
    public static void setTextScaleX(float scaleX) {
        mPaint.setTextScaleX(scaleX);
    }

    //设置绘制文字的字号大小
    public static void setTextSize(float textSize) {
        mPaint.setTextSize(textSize);
    }

    //设置斜体文字，skewX为倾斜弧度
    public static void setTextSkewX(float skewX) {
        mPaint.setTextSkewX(skewX);
    }

    //设置Typeface对象，即字体风格，包括粗体，斜体以及衬线体，非衬线体等
    public static void setTypeface(Typeface typeface) {
        mPaint.setTypeface(typeface);
    }

    //设置带有下划线的文字效果
    public static void setUnderlineText(boolean underlineText) {
        mPaint.setUnderlineText(underlineText);
    }

}
