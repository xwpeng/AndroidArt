# Copyright (C) 2009 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

#每个Android.mk文件必须以定义LOCAL_PATH为开始。它用于在开发tree中查找源文件。
#宏my-dir 则由Build System提供。返回包含Android.mk的目录路径。
LOCAL_PATH := $(call my-dir)

#CLEAR_VARS 变量由Build System提供。并指向一个指定的GNU Makefile，由它负责清理很多LOCAL_xxx.
#例如：LOCAL_MODULE, LOCAL_SRC_FILES, LOCAL_STATIC_LIBRARIES等等。但不清理LOCAL_PATH.
#这个清理动作是必须的，因为所有的编译控制文件由同一个GNU Make解析和执行，其变量是全局的。所以清理后才能避免相互影响
include $(CLEAR_VARS)

#LOCAL_MODULE模块必须定义，以表示Android.mk中的每一个模块。名字必须唯一且不包含空格。
#Build System会自动添加适当的前缀和后缀。例如，foo，要产生动态库，则生成libfoo.so. 但请注意：如果模块名被定为：libfoo.则生成libfoo.so. 不再加前缀。
LOCAL_MODULE := jni-test

#LOCAL_SRC_FILES变量必须包含将要打包如模块的C/C++ 源码。
#不必列出头文件，build System 会自动帮我们找出依赖文件。
#缺省的C++源码的扩展名为.cpp. 也可以修改，通过LOCAL_CPP_EXTENSION。
LOCAL_SRC_FILES := test.cpp

#BUILD_SHARED_LIBRARY：是Build System提供的一个变量，指向一个GNU Makefile Script。
#它负责收集自从上次调用 include $(CLEAR_VARS)  后的所有LOCAL_XXX信息。并决定编译为什么。
#BUILD_STATIC_LIBRARY：编译为静态库。
#BUILD_SHARED_LIBRARY ：编译为动态库
#BUILD_EXECUTABLE：编译为Native C可执行程序
include $(BUILD_SHARED_LIBRARY)