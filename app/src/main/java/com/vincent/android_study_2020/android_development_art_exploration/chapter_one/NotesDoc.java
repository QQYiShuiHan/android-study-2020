package com.vincent.android_study_2020.android_development_art_exploration.chapter_one;

public class NotesDoc {

    // 第一章

    /*
       一、Activity的生命周期和启动模式
          1.activity的生命周期
            （1）onCreate ——> onRestore -> onStart -> onResume -> onPause -> onStop -> onDestroy
            （2）onStart : activity可见但是没有焦点，不可交互
            （3）onResume ： activity 可见，并且可交互
            （4）前一个activity的onPause执行完毕之后才能启动下一个activity，执行下一个activity的onResume方法
            （5）activity的启动：
                发送启动activity的请求由Instrumentation来处理，
                然后通过Binder向AMS发起请求。AMS内部维护这一个ActivityStack并负责activity的的状态同步，
                AMS通过ActivityThread去同步Activity的状态从而完成生命周期的调用。
          2.activity的异常生命周期
            原因 ：
                （1）资源相关的系统配置发生改变（activity 杀死 -> 重启）
                    杀死 ：Activity ——》 onSaveInstanceState（保存数据到 Instance） ——》 onStop
                    重启 ：Activity ——》 onStart ——》 onRestoreInstanceState（从Instance 取出数据）/ onCreate的Instance中取出数据
                    Activity也会委托View保存和恢复数据
                （2）系统内存不足时，activity可能被杀死
                    进程的优先级
                        前台Activity ——》 可见但非前台Activity ——》 后台Activity
           配置Activity 不要销毁重建的
                android:configChanges = "orientation" 常用 ： locale orientation keyboardHidden，最用
                会调用Activity的OnConfigurationChange(Configuration newConfig)
        二、Activity的启动模式
            1.standard : 按顺序压如栈中
            2.singleTop ：栈顶服用模式
            3.singleTask ：栈内复用模式，并清除这个Activity之上的其它Activity
            4.singleInstance ：开辟一个新的栈，并以singleTask形式存在
            指定Activity的栈名 ： TaskAffinity

            数据结构



     */
}
