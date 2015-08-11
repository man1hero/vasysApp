package com.vasys.activity;

import cn.jpush.android.api.JPushInterface;

import com.vasys.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

/**
 * ���ܣ�ʹ��viewpager ʵ���״ν���Ӧ��ʱ������ҳ ��1���ж��Ƿ��״μ���Ӧ��--���ö�ȡSharedPreferences�ķ���
 * ��2���ǣ����������activity �������LoginActivity ��3��5s��ִ��2����
 * 
 * @author lin
 * 
 */
public class SplashActivity extends Activity {
	boolean isFirstIn = false;
	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;
	// �ӳ�3��
	private static final long SPLASH_DELAY_MILLIS = 3000;

	private static final String SHAREDPREFERENCES_NAME = "first_pref";
	/**
	 * Handler:��ת����ͬ����
	 */
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case GO_HOME:
				goHome();
				break;
			case GO_GUIDE:
				goGuide();
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		init();
	}

	private void init() {
		// ��ȡSharedPreferences����Ҫ������
		// ʹ��SharedPreferences�м�¼�����ʹ�ô���
		SharedPreferences preferences = getSharedPreferences(
				SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		// ȡ����Ӧ��ֵ�����û�и�ֵ��˵����δд�룬ʹ��true��ΪĬ��ֵ
		isFirstIn = preferences.getBoolean("isFirstIn", true);
		if (!isFirstIn) {
			// ʹ��Handler��postDelayed������3���ִ����ת��MainActivity
			mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
		}
	}

	private void goHome() {
		Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
		SplashActivity.this.startActivity(intent);
		SplashActivity.this.finish();
	}

	private void goGuide() {// ��ת��������
		Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
		SplashActivity.this.startActivity(intent);
		SplashActivity.this.finish();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(this);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
	}

}
