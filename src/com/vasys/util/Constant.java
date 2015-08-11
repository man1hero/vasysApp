package com.vasys.util;

public class Constant {

	// Btn�ı�ʶ
	public static final int BTN_FLAG_MESSAGE = 0x01;
	public static final int BTN_FLAG_CONTACTS = 0x01 << 1;
	public static final int BTN_FLAG_NEWS = 0x01 << 2;
	public static final int BTN_FLAG_SETTINGS = 0x01 << 3;
	public static final int BTN_FLAG_MORE = 0x01 << 4;

	// Fragment�ı�ʶ
	public static final String FRAGMENT_FLAG_CAM = "���";
	public static final String FRAGMENT_FLAG_ALARM = "����";
	public static final String FRAGMENT_FLAG_REPORT = "����";
	public static final String FRAGMENT_FLAG_SETTINGS = "����";
	public static final String FRAGMENT_FLAG_MORE = "����";
	public static final String FRAGMENT_FLAG_SIMPLE = "simple";
	
	//������IP��ַ
	public static String SERVER_IP="192.168.4.76";

	//��������ַ
	public static String SERVER_URL="http://192.168.4.76:8080/WildDuck/";

	//�û���
	public static String USER="";
	
	//����
	public static String PASSWORD="";
	
	public static String getUSER() {
		return USER;
	}

	public static void setUSER(String uSER) {
		USER = uSER;
	}

	public static String getPASSWORD() {
		return PASSWORD;
	}

	public static void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public static String getSERVER_URL() {
		return SERVER_URL;
	}

	public static void setSERVER_URL(String sERVER_URL) {
		SERVER_URL = sERVER_URL;
	}
	
}
