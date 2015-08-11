package com.vasys.activity;

import com.vasys.R;
import com.vasys.util.Constant;
import com.vasys.webservice.LoginService;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.Toast;

/**
 * 点击登录后显示登录中。。界面
 * 
 * @author lin
 * 
 */
public class LoadingActivity extends Activity {
	String loginResult = "";
	String name;
	String password;

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		//获取intent中传入的用户名和密码
		Intent intent = this.getIntent();
		name = intent.getStringExtra("name");
		password = intent.getStringExtra("password");
		// 这里Handler的postDelayed方法，等待10000毫秒在执行run方法。
		// 在Activity中我们经常需要使用Handler方法更新UI或者执行一些耗时事件，
		// 并且Handler中post方法既可以执行耗时事件也可以做一些UI更新的事情，比较好用，推荐使用
		new Handler().postDelayed(new Runnable() {
			public void run() {
				loginResult = LoginService.login(name, password);// 根据用户名密码登录。
				if (loginResult == "登录失败") {// 失败则弹出提示
					// 创建提示框提醒是否登录成功
					AlertDialog.Builder builder = new Builder(
							LoadingActivity.this);
					builder.setTitle("登录提示")
							.setMessage("登录失败，请重新检查")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											LoadingActivity.this.finish();
											dialog.dismiss();
										}
									}).create().show();
				}
				if (loginResult == "登录成功") {// 成功则跳转
					LoadingActivity.this.finish();
					Toast.makeText(getApplicationContext(), "登录成功",
							Toast.LENGTH_SHORT).show();
					//将用户名密码保存到Constant类中
					Constant.setUSER(name);
					Constant.setPASSWORD(password);
					
					//跳转主界面
					Intent intent = new Intent();
					intent.setClass(LoadingActivity.this, MainActivity.class);
					LoadingActivity.this.startActivity(intent);

				}
			}
		}, 1000);
	}

}
