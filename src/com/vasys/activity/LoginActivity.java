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
 * ��¼����
 * 
 * @date 2015��6��23��08:59:22
 * @author lin
 * 
 */
public class LoginActivity extends Activity {
	public static LoginActivity loginActivity = null;
	private EditText nameText;// �û��������
	private EditText passwdText;// ���������
	private Button button;// ��¼��ť
	private String name;// �û���
	private String password;// ����
	private TextView register_link;//��ϵ����Ա����
	private CheckBox checkBox;//�����
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
		if(sharedPreferences.getString("username", "").equals("")){//�������û�д�ŵ����û���Ϣ�Ļ�
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
				builder.setTitle("��ϵ����Ա")
						.setMessage("Q Q:779441120\n�绰��18560665150\n���䣺tianzhen.lin@qaii.ac.cn\n")
						.setPositiveButton("ȷ��",
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
		
		// ��¼��ť����¼�
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// �������
				name = nameText.getText().toString();// ��ȡ������û���
				password = passwdText.getText().toString();// ��ȡ���������
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

				
				if(name.equals("")==true||password.equals("")==true){//����û���������Ϊ��,�򵯳���ʾ
					AlertDialog.Builder builder = new Builder(
							LoginActivity.this);
					builder.setTitle("������ʾ")
							.setMessage("�������û��������룡")
							.setPositiveButton("ȷ��",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									}).create().show();
					
				}else{//����Ϸ����ټ��������ַ�Ƿ����
					
					//���IP��ַ�Ƿ����
					//boolean isReachable=HostPing.ping(Constant.SERVER_IP);//
					
					if(true){
					Intent intent = new Intent();
					intent.putExtra("name", name);
					intent.putExtra("password", password);
					intent.setClass(LoginActivity.this, LoadingActivity.class);
					LoginActivity.this.startActivity(intent);
					
					}/*else{
						Log.e("ping:","ping ��ͨ");
						Toast.makeText(LoginActivity.this, "������ַ�����ã������¼��", Toast.LENGTH_SHORT).show();
					}*/
				}
				}
			}

		});
	}

	/**
	 * �������״̬
	 */
	public boolean checkNetworkState() {
	
		ConnectivityManager manager = (ConnectivityManager) getSystemService(LoginActivity.CONNECTIVITY_SERVICE);
		State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		// ���3G��wifi��2G������״̬�����ӵģ����˳���������ʾ��ʾ��Ϣ�����������ý���
		if (mobile == State.CONNECTED || mobile == State.CONNECTING)
			return true;
		if (wifi == State.CONNECTED || wifi == State.CONNECTING)
			return true;
		return false;
		
	}
	//������ʾ
	private void showTips() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle("û�п�������");
		builder.setMessage("��ǰ���粻���ã��Ƿ��������磿");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// ���û���������ӣ�������������ý���
				startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

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
	 * �˵������ؼ���Ӧ
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // ����˫���˳�����
		}
		return false;
	}

	/**
	 * ˫���˳�����
	 */
	private static Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // ׼���˳�
			Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // ȡ���˳�
				}
			}, 2000); // ���2������û�а��·��ؼ�����������ʱ��ȡ�����ղ�ִ�е�����

		} else {// �˳�
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