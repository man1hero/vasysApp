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
 * �����¼����ʾ��¼�С�������
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
		//��ȡintent�д�����û���������
		Intent intent = this.getIntent();
		name = intent.getStringExtra("name");
		password = intent.getStringExtra("password");
		// ����Handler��postDelayed�������ȴ�10000������ִ��run������
		// ��Activity�����Ǿ�����Ҫʹ��Handler��������UI����ִ��һЩ��ʱ�¼���
		// ����Handler��post�����ȿ���ִ�к�ʱ�¼�Ҳ������һЩUI���µ����飬�ȽϺ��ã��Ƽ�ʹ��
		new Handler().postDelayed(new Runnable() {
			public void run() {
				loginResult = LoginService.login(name, password);// �����û��������¼��
				if (loginResult == "��¼ʧ��") {// ʧ���򵯳���ʾ
					// ������ʾ�������Ƿ��¼�ɹ�
					AlertDialog.Builder builder = new Builder(
							LoadingActivity.this);
					builder.setTitle("��¼��ʾ")
							.setMessage("��¼ʧ�ܣ������¼��")
							.setPositiveButton("ȷ��",
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
				if (loginResult == "��¼�ɹ�") {// �ɹ�����ת
					LoadingActivity.this.finish();
					Toast.makeText(getApplicationContext(), "��¼�ɹ�",
							Toast.LENGTH_SHORT).show();
					//���û������뱣�浽Constant����
					Constant.setUSER(name);
					Constant.setPASSWORD(password);
					
					//��ת������
					Intent intent = new Intent();
					intent.setClass(LoadingActivity.this, MainActivity.class);
					LoadingActivity.this.startActivity(intent);

				}
			}
		}, 1000);
	}

}
