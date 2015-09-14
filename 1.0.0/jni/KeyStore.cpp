#include <jni.h>
#include <string.h>
#include "KeyStore.h"

KeyStore* KeyStore::instance = NULL;

KeyStore * KeyStore::getInstance() {
	if (instance == NULL)
		 instance = new KeyStore();
	return instance;
}

jstring KeyStore::getKey() {
	return key;
}

void KeyStore::setKey(jstring k) {
	key = k;
}

