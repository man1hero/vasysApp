package com.vasys.activity;

import cn.jpush.android.api.JPushInterface;
import android.app.Application;
import android.util.Log;

public class VasysApplication extends Application{
	public void onCreate(){
		Log.e("application", "��������ʱ���õ��⡣");
		super.onCreate();
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
	}
}
