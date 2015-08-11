package com.vasys.activity;

import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;

import com.vasys.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登录界面
 * 
 * @date 2015年6月23日08:59:22
 * @author lin
 * 
 */
public class LoginActivity extends Activity {
	public static LoginActivity loginActivity = null;
	private EditText nameText;// 用户名输入框
	private EditText passwdText;// 密码输入框
	private Button button;// 登录按钮
	private String name;// 用户名
	private String password;// 密码
	private TextView register_link;//联系管理员链接
	private CheckBox checkBox;//检验框
	SharedPreferences sharedPreferences;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		loginActivity=this;
		nameText = (EditText) findViewById(R.id.username_edit);
		passwdText = (EditText) findViewById(R.id.password_edit);
		button = (Button) findViewById(R.id.signin_button);
		register_link=(TextView)findViewById(R.id.register_link);
		checkBox=(CheckBox)findViewById(R.id.cb_savename);
		
		sharedPreferences = getSharedPreferences("user", 0);
		if(sharedPreferences.getString("username", "").equals("")){//如果本机没有存放的有用户信息的话
			checkBox.setChecked(false);
		}else{
			checkBox.setChecked(true);
			String username = sharedPreferences.getString("username", "");
			nameText.setText(username);
			String password = sharedPreferences.getString("password", "");
			passwdText.setText(password);
		}
		register_link.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new Builder(
						LoginActivity.this);
				builder.setTitle("联系管理员")
						.setMessage("Q Q:779441120\n电话：18560665150\n邮箱：tianzhen.lin@qaii.ac.cn\n")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).create().show();
			}
		});
		
		// 登录按钮点击事件
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// 检查网络
				name = nameText.getText().toString();// 获取输入的用户名
				password = passwdText.getText().toString();// 获取输入的密码
				if(checkNetworkState()!=true){
					showTips();
				}else{
				if(checkBox.isChecked()){
					 sharedPreferences = getSharedPreferences(
							"user", 0);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("username", name);
					editor.putString("password", password);
					editor.commit();
				}else{
					sharedPreferences = getSharedPreferences(
							"user", 0);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("username", "");
					editor.putString("password", "");
					editor.commit();
				}

				
				if(name.equals("")==true||password.equals("")==true){//如果用户名或密码为空,则弹出提示
					AlertDialog.Builder builder = new Builder(
							LoginActivity.this);
					builder.setTitle("输入提示")
							.setMessage("请输入用户名和密码！")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									}).create().show();
					
				}else{//输入合法则再检查主机地址是否可用
					
					//检查IP地址是否可用
					//boolean isReachable=HostPing.ping(Constant.SERVER_IP);//
					
					if(true){
					Intent intent = new Intent();
					intent.putExtra("name", name);
					intent.putExtra("password", password);
					intent.setClass(LoginActivity.this, LoadingActivity.class);
					LoginActivity.this.startActivity(intent);
					
					}/*else{
						Log.e("ping:","ping 不通");
						Toast.makeText(LoginActivity.this, "主机地址不可用，请重新检查", Toast.LENGTH_SHORT).show();
					}*/
				}
				}
			}

		});
	}

	/**
	 * 检查网络状态
	 */
	public boolean checkNetworkState() {
	
		ConnectivityManager manager = (ConnectivityManager) getSystemService(LoginActivity.CONNECTIVITY_SERVICE);
		State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		// 如果3G、wifi、2G等网络状态是连接的，则退出，否则显示提示信息进入网络设置界面
		if (mobile == State.CONNECTED || mobile == State.CONNECTING)
			return true;
		if (wifi == State.CONNECTED || wifi == State.CONNECTING)
			return true;
		return false;
		
	}
	//弹出提示
	private void showTips() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle("没有可用网络");
		builder.setMessage("当前网络不可用，是否设置网络？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 如果没有网络连接，则进入网络设置界面
				startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				LoginActivity.this.finish();
			}
		});
		builder.create();
		builder.show();
	}

	/**
	 * 菜单、返回键响应
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // 调用双击退出函数
		}
		return false;
	}

	/**
	 * 双击退出函数
	 */
	private static Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

		} else {// 退出
			finish();
			System.exit(0);
		}
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