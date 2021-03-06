package com.vincent.android_study_2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vincent.android_study_2020.datastructure_and_algorithm.SimpleAlgorithm;
import com.vincent.android_study_2020.datastructure_and_algorithm.SimpleSort;
import com.vincent.android_study_2020.domain.Bean;
import com.vincent.android_study_2020.view.AudienceListView;
import com.vincent.android_study_2020.view.MessagePanelView;
import com.vincent.android_study_2020.view.RevealAnimationLayout;
import com.vincent.apt_annotation.BindView;
import com.vincent.apt_library.BindViewTools;

import org.w3c.dom.Text;

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

    @BindView(R.id.tv_fade_in_or_out)
    TextView mFadeInOrOut;


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

        mFadeInOrOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationLayout.startAnimal(RevealAnimationLayout.AnimaType.FadeIn);
            }
        });

    }


    private void getAudiences() {
        for (int i = 0; i < 3; i++) {
            Bean bean = new Bean();
            audienceBeanList.add(bean);
            messagePanelList.add(bean);
        }
    }

    @Override
    public void onBackPressed() {
        getAudiences();
        audience.refreshData(audienceBeanList);
        messagePanel.refreshData(messagePanelList);
    }

}
