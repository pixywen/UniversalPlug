LOCAL_PATH:=$(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE:=NativeEncrypt
LOCAL_SRC_FILES:=EncryptUtil.cpp\
AES.cpp\
MD5.cpp\
KeyStore.cpp
LOCAL_LDLIBS+=-llog
include $(BUILD_SHARED_LIBRARY)