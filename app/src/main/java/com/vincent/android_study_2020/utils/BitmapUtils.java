package com.vincent.android_study_2020.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils {

    /**
     * 加载资源图片到内存
     *
     * @param res
     * @param resId
     * @param reqHeight
     * @param reqWidth
     * @return
     */
    public static Bitmap decodeBitmapFromResource(Resources res, int resId, int reqHeight, int reqWidth) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // 预加载获取bitmap参数（没有真正的加载到内存）
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 设置参数，加载bitmap（加载到内存）
        options.inSampleSize = calculateInSampleSize(options, reqHeight, reqWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);

    }

    /**
     * 计算符合要求的 inSampleSize 值
     *
     * @param options
     * @param reqHeight
     * @param reqWidth
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqHeight, int reqWidth) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqHeight) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight &&
                    (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
