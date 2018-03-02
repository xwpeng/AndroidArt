//
// Created by Administrator on 2018/2/28.
//
#include "com_xwpeng_JniTest.h"
#include <stdio.h>

JNIEXPORT jstring JNICALL Java_com_xwpeng_JniTest_get(JNIEnv *env, jobject thisz);{
printf("invoke get from C++\n");
return env->NewStringUTF("Hello from JNI !");
}

JNIEXPORT void JNICALL Java_com_xwpeng_JniTest_set
  (JNIEnv *env, jobject thisz, jstring string){
  printf("invoke set from C++\n");
  char* str = (char*)env->GetStringUTFChars(string, NULL);
  printf("%s\n", str);
  env->ReleaseStringUTFChars(string, str);
}
