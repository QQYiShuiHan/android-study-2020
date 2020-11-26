package com.vincent.android_study_2020.algorithm;

import android.telephony.mbms.MbmsErrors;
import android.util.Log;

import java.util.Arrays;

/**
 * @author: hqq
 * @date: 2020/10/5
 * @description 整理常见的排序算法
 */
public class SimpleSort {


    public static void printlnArray(String tag, int[] arr) {
        System.out.println(tag + " : " + Arrays.toString(arr));
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**********************************************************************************************
     * 冒泡
     */
    public static void bubbleSort(int[] arr) {
        printlnArray("origin  arr", arr);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        printlnArray("bubble sort", arr);
    }

    public static void bubbleSort_1(int[] arr) {
        printlnArray("origin  arr", arr);
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        printlnArray("bubble sort ", arr);
    }

    /************************************************************************************************
     * 选择
     */
    public static void selectSort(int[] arr) {
        printlnArray("origin  arr", arr);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        printlnArray("select sort", arr);
    }

    /**********************************************************************************************
     * 插入(感觉是冒泡的优化)
     */
    public static void insertSort(int[] arr) {
        printlnArray("origin  arr", arr);
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }
            }
        }
        printlnArray("insert sort", arr);
    }


    /**********************************************************************************************
     * 希尔排序 （分组的插入排序）
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        printlnArray("origin  arr", arr);
        // 排序分组的取之方法
        int h = 1;
        while (h < arr.length / 2) {
            h = 2 * h + 1;
        }
        // 开始希尔排序
        while (h >= 1) {// 当分组的长等于1时，时最后一次排序
            for (int i = h; i < arr.length; i++) {// 从h之后都是待插入的元素
                for (int j = i; j >= h; j -= h) {
                    if (arr[j - h] > arr[j]) {
                        int temp = arr[j - h];
                        arr[j - h] = arr[j];
                        arr[j] = temp;
                    } else {
                        break;
                    }
                }
            }
            h = h / 2;
        }
        printlnArray("shell  sort", arr);
    }

    /************************************************************************************************
     * 归并（分 -> 治）
     *
     * @param arr
     */
    private static int[] assist = null;

    public static void mergeSort(int[] arr) {
        printlnArray("origin  arr", arr);
        assist = new int[arr.length];
        int lo = 0;
        int hi = arr.length - 1;
        mSrot(arr, lo, hi);
        printlnArray("merge  sort", arr);
    }

    private static void mSrot(int[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mSrot(arr, lo, mid);
        mSrot(arr, mid + 1, hi);

        merge(arr, lo, mid, hi);
    }

    private static void merge(int[] arr, int lo, int mid, int hi) {
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= hi) {

            if (arr[p1] < arr[p2]) {
                assist[i++] = arr[p1++];
            } else {
                assist[i++] = arr[p2++];
            }
        }
        while (p1 <= mid) {
            assist[i++] = arr[p1++];
        }
        while (p2 <= hi) {
            assist[i++] = arr[p2++];
        }
        for (int j = lo; j <= hi; j++) {
            arr[j] = assist[j];
        }
    }

    /*********************************************************************************************
     * 快速排序
     * @param arr
     */
    public static void quickSort(int[] arr) {
        printlnArray("origin  arr", arr);
        int lo = 0;
        int hi = arr.length - 1;
        qSort(arr, lo, hi);
        printlnArray("quick  sort", arr);

    }


    private static void qSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int partition = partition(arr, lo, hi);

        qSort(arr, lo, partition - 1);

        qSort(arr, partition + 1, hi);

    }


    private static int partition(int[] arr, int lo, int hi) {
        int key = arr[lo];
        int left = lo + 1;
        int right = hi;
        while (left < right) {
            while (left < right && key < arr[right]) {
                right--;
            }

            while (left < right && arr[left] < key) {
                left++;
            }
            swap(arr, left, right);
        }
        swap(arr, lo, right);
        return right;
    }


}
