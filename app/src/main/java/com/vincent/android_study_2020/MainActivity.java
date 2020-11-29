package com.vincent.android_study_2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.vincent.android_study_2020.algorithm.SimpleAlgorithm;
import com.vincent.android_study_2020.algorithm.SimpleSort;
import com.vincent.android_study_2020.domain.Bean;
import com.vincent.android_study_2020.view.AudienceListView;
import com.vincent.android_study_2020.view.MessagePanelView;
import com.vincent.android_study_2020.view.RevealAnimationLayout;
import com.vincent.apt_library.BindViewTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Context mContext;
    // 观众列表（横向Recyclerview)
    private AudienceListView audience;
    private List<Bean> audienceBeanList = new ArrayList<>();
    // 聊天面板（竖向Recyclerview)
    private MessagePanelView messagePanel;
    private List<Bean> messagePanelList = new ArrayList<>();
    private RevealAnimationLayout animationLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 测试apt
        BindViewTools.bind(this);


        new TransformTest().init();


        mContext = this;
        audience = (AudienceListView) findViewById(R.id.audience);
        messagePanel = (MessagePanelView) findViewById(R.id.message_panel);
        getAudiences();
        audience.refreshData(audienceBeanList);
        messagePanel.refreshData(messagePanelList);
        animationLayout = findViewById(R.id.ral_fade_in_or_out);

        findViewById(R.id.tv_fade_in_or_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationLayout.startAnimal(RevealAnimationLayout.AnimaType.FadeIn);
            }
        });


        reverseWords("Let's take LeetCode contest");

        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = SimpleAlgorithm.twoSum(nums, 26);
        Log.d("twoSum", " result = " + Arrays.toString(result));

        boolean isValid = SimpleAlgorithm.isValid("{(dfa{}[]sd())}");
        Log.d("isValid", " isValid = " + isValid);


        int[] arr = new int[]{9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        SimpleSort.bubbleSort(arr);
        SimpleSort.selectSort(arr);
        SimpleSort.insertSort(arr);
        SimpleSort.shellSort(arr);
        SimpleSort.mergeSort(arr);


    }


    private void getAudiences() {
        for (int i = 0; i < 3; i++) {
            Bean bean = new Bean();
            audienceBeanList.add(bean);
            messagePanelList.add(bean);
        }
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onBackPressed() {
        getAudiences();
        audience.refreshData(audienceBeanList);
        messagePanel.refreshData(messagePanelList);
    }

    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int length = str.length();
            String temp = "";
            for (int j = length - 1; j >= 0; j--) {
                temp = temp + str.charAt(j);
            }
            Log.d("reverseWords", " temp = " + temp);
            if (i != 0) {
                sb.append(" ");
            }
            sb.append(temp);
        }
        Log.d("reverseWords", " sb = " + sb);
        return sb.toString();
    }


}
