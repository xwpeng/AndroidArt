package com.xwpeng;

/**
 * Created by Administrator on 2018/2/28.
 */

public class JniTest {
    static {
        //so库的完整名称为libjni-test.so,这是加载so库的规范
        System.loadLibrary("jni-test");
    }

    public static void main(String[] args) {
        JniTest jniTest = new JniTest();
        System.out.println(jniTest.get());
    }

    public native String get();
    public native void set(String str);
}
