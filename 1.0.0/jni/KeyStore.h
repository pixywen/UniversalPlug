class KeyStore {

private:
	jstring key;
	static KeyStore *instance;

public:
	static KeyStore *getInstance();

	jstring getKey();

	void setKey(jstring k);

};
