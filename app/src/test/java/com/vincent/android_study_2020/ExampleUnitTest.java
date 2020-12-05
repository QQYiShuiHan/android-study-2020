package com.vincent.android_study_2020;

import android.util.Log;

import com.vincent.android_study_2020.datastructure_and_algorithm.DataStructureTest;
import com.vincent.android_study_2020.datastructure_and_algorithm.SimpleSort;

import org.junit.Test;
import org.w3c.dom.Text;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    int[] arr = new int[]{9, 1, 2, 5, 7, 4, 8, 6, 3, 5};

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSort(){
        SimpleSort.bubbleSort(arr);
        arr = new int[]{9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        SimpleSort.selectSort(arr);
        arr = new int[]{9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        SimpleSort.insertSort(arr);
        arr = new int[]{9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        SimpleSort.shellSort(arr);
        arr = new int[]{9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        SimpleSort.mergeSort(arr);
        arr = new int[]{9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        SimpleSort.quickSort(arr);
    }

    @Test
    public void testSequenceList(){
        DataStructureTest.testSequenceList();
    }


}