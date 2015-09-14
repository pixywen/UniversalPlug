#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <AES.h>
#include <MD5.h>
#include <android/log.h>
#include <KeyStore.h>

#ifdef __cplusplus

extern "C" {
#endif




JNIEXPORT jstring JNICALL Java_com_tatian_up_utils_EncryptUtil_nativeKey(
		JNIEnv* env, jclass clazz, jstring str);


JNIEXPORT jbyteArray JNICALL Java_com_tatian_up_utils_EncryptUtil_nativeDecode(
		JNIEnv* env, jclass clazz, jstring str);

#ifdef __cplusplus
}
#endif

char* jstring2str(JNIEnv* env, jstring jstr) {
	char* rtn = NULL;
	jclass clsstring = env->FindClass("java/lang/String");
	jstring strencode = env->NewStringUTF("utf-8");
	jmethodID mid = env->GetMethodID(clsstring, "getBytes",
			"(Ljava/lang/String;)[B");
	jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, mid, strencode);
	jsize alen = env->GetArrayLength(barr);
	jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);
	if (alen > 0) {
		rtn = (char*) malloc(alen + 1);
		memcpy(rtn, ba, alen);
		rtn[alen] = 0;
	}
	env->ReleaseByteArrayElements(barr, ba, 0);
	return rtn;
}

jstring str2Jstring(JNIEnv* env, const char* pat) {
	jclass strClass = env->FindClass("java/lang/String");
	jmethodID ctorID = env->GetMethodID(strClass, "<init>",
			"([BLjava/lang/String;)V");
	jbyteArray bytes = env->NewByteArray(strlen(pat));
	env->SetByteArrayRegion(bytes, 0, strlen(pat), (jbyte*) pat);
	jstring encoding = env->NewStringUTF("utf-8");
	return (jstring) env->NewObject(strClass, ctorID, bytes, encoding);
}

char hexDigits[16] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
		'b', 'c', 'd', 'e', 'f' };

char hexInt2char(int i) {
	return hexDigits[i];
}

int char2hexInt(char c) {
	for (int i = 0; i < 16; i++) {
		if (hexDigits[i] == c) {
			return i;
		}
	}
	__android_log_print(ANDROID_LOG_ERROR, "JNI",
			"char2hexInt unexcept char=%c ", c);
	return -1;
}


jclass STAgent;

jmethodID getChannelID;
jmethodID getPackageName;

char map[] = { 'A', 'a', 'B', 'b', 'M', 'm', 'C', 'c', 'F', 'f' };

char* join(char *a, char *b) {
	char *c = (char *) malloc(strlen(a) + strlen(b) + 1);
	if (c == NULL)
		return NULL;
	char *tempc = c;
	while (*a != '\0') {
		*c++ = *a++;
	}
	while ((*c++ = *b++) != '\0') {

	}

	return tempc;
}


JNIEXPORT jstring JNICALL Java_com_tatian_up_utils_EncryptUtil_nativeKey(
		JNIEnv* env, jclass clazz, jstring str) {

	jstring result = NULL;

	if (STAgent == NULL) {
		STAgent = env->FindClass("com/tivicloud/engine/unity/STAgent");
		if (STAgent == NULL) {
			return result;
		}
	}

	if (getChannelID == NULL) {
		getChannelID = env->GetStaticMethodID(STAgent, "getChannelID",
				"()Ljava/lang/String;");
		if (getChannelID == NULL) {
			env->DeleteLocalRef(STAgent);
			return result;
		}

	}

	if (getPackageName == NULL) {
		getPackageName = env->GetStaticMethodID(STAgent, "getPackageName",
				"()Ljava/lang/String;");
		if (getChannelID == NULL) {
			env->DeleteLocalRef(STAgent);
			return result;
		}
	}

	jstring content_id, content_name;

	content_id = (jstring) env->CallStaticObjectMethod(STAgent,
			getChannelID);
	content_name = (jstring) env->CallStaticObjectMethod(STAgent,
			getPackageName);

	char* ccontent_id = NULL;
	char* ccontent_name = NULL;

	ccontent_id = (char*) env->GetStringUTFChars(content_id, 0);
	ccontent_name = (char*) env->GetStringUTFChars(content_name, 0);



	char* content = join(ccontent_id, ccontent_name);

	char salt[] = { map[5], map[0], map[0], map[9], map[2], '\0' };

	char* content_result = join(content, salt);

//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "ChannelID , Value = %s",
//			ccontent_id);
//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "PackageName , Value = %s",
//			ccontent_name);
//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "Content , Value = %s",
//			content);
//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "Salt , Value = %s",
//			content_result);

	//释放 ccontent_id
	env->ReleaseStringUTFChars(content_id,ccontent_id);
	env->DeleteLocalRef(content_id);

	//释放 ccontent_name
	env->ReleaseStringUTFChars(content_name,ccontent_name);
	env->DeleteLocalRef(content_name);

	// content 需要使用free 释放
	free(content);

	// Md5 编码
	unsigned char decrypt[17];
	MD5_CTX md5;
	MD5Init(&md5);
	MD5Update(&md5, (unsigned char *) content_result,
			strlen((char *) content_result));
	MD5Final(&md5, decrypt);

	// content 需要使用free 释放
    free(content_result);

	char* md5_result = new char[33];
	md5_result[32] = '\0';

	for (int i = 0; i < 16; i++) {
		char temp[2];
		sprintf(temp, "%02x", decrypt[i]);
		md5_result[2 * i] = temp[0];
		md5_result[2 * i + 1] = temp[1];
	}

//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "md5 = %s", md5_result);
	result = str2Jstring(env, md5_result);
	//KeyStore::getInstance()->setKey(result);

	delete md5_result;
	return result;
}



JNIEXPORT jbyteArray JNICALL Java_com_tatian_up_utils_EncryptUtil_nativeDecode(
		JNIEnv* env, jclass clazz, jstring str) {

	unsigned char pData[] = { //
			90, 121, 56, 101, //
							98, 112, 113, 65, //
							33, 27, 13, 49, //
							27, 107, 118, 127
			};//

	jbyte *by = (jbyte*) pData;
	jbyteArray jarray = env->NewByteArray(16);
	env->SetByteArrayRegion(jarray, 0, 16, by);

	//jstring result = str2Jstring(env,"comtivicloud");
	return jarray;

}

