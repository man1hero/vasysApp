package com.vasys.util;

public class Constant {

	// Btn的标识
	public static final int BTN_FLAG_MESSAGE = 0x01;
	public static final int BTN_FLAG_CONTACTS = 0x01 << 1;
	public static final int BTN_FLAG_NEWS = 0x01 << 2;
	public static final int BTN_FLAG_SETTINGS = 0x01 << 3;
	public static final int BTN_FLAG_MORE = 0x01 << 4;

	// Fragment的标识
	public static final String FRAGMENT_FLAG_CAM = "监控";
	public static final String FRAGMENT_FLAG_ALARM = "报警";
	public static final String FRAGMENT_FLAG_REPORT = "报表";
	public static final String FRAGMENT_FLAG_SETTINGS = "设置";
	public static final String FRAGMENT_FLAG_MORE = "更多";
	public static final String FRAGMENT_FLAG_SIMPLE = "simple";
	
	//服务器IP地址
	public static String SERVER_IP="192.168.4.76";

	//服务器地址
	public static String SERVER_URL="http://192.168.4.76:8080/WildDuck/";

	//用户名
	public static String USER="";
	
	//密码
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
