package com.vincent.android_study_2020.ndk;

public class Hello {

    public static native String sayHello();

    public static native void cassStaticMethod(int i);

    public native void getInstanceMethod(String s, long l);

}
