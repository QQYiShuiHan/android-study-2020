package com.vincent.android_study_2020;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.vincent.android_study_2020.algorithm.SimpleSort;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
	@Test
	public void useAppContext() {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		assertEquals("com.vincent.android_study_2020", appContext.getPackageName());
	}

	int[] arr = new int[]{9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
	public void sortText(){
		SimpleSort.quickSort(arr);
		Log.d("Sort", " result : " + Arrays.toString(arr));
	}
}