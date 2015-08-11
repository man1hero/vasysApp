package com.vasys.activity;

import com.vasys.R;
import com.vasys.util.Constant;
import com.vasys.webservice.UserService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �˻����������ý��������룬�������MainActivity
 * @date 2015��6��23��15:33:27
 * @author lin
 *
 */
public class MyAccountActivity extends Activity{
	private TextView userText;//�û���
	private TextView passwordText;//����
	private EditText password_pre;//ԭ����
	private EditText password_new;//������
	private EditText password_confirm;//ȷ������
	private Button pwdNew;//��������
	private Button ok;//ȷ��
	private Button updateConfirm;//ȷ���޸�����
	private TableLayout newPwdTab;//��񲼾�
	private ImageView back;//���ذ�ť
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account);
		userText=(TextView)findViewById(R.id.account_user);
		passwordText=(TextView)findViewById(R.id.account_password);
		pwdNew=(Button)findViewById(R.id.account_update_pwd);//�޸����밴ť
		ok=(Button)findViewById(R.id.account_ok);//ȷ����ť
		updateConfirm=(Button)findViewById(R.id.update_confirm);//ȷ���޸İ�ť
		password_pre=(EditText)findViewById(R.id.password_pre);//ԭ����
		password_new=(EditText)findViewById(R.id.password_new);//��һ�������������
		password_confirm=(EditText)findViewById(R.id.password_confirm);//ȷ�����������
		
		newPwdTab=(TableLayout)findViewById(R.id.account_password_tab);
		back=(ImageView)findViewById(R.id.account_back);
		
		userText.setText(Constant.USER);
		passwordText.setText(Constant.PASSWORD);
		pwdNew.setOnClickListener(newPwdListener);
		back.setOnClickListener(backListener);
		ok.setOnClickListener(backListener);
		updateConfirm.setOnClickListener(updateConfirmListener);
	}
	
	private  OnClickListener newPwdListener=new OnClickListener(){//�����������������Ӧ����ʾ��������������

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			newPwdTab.setVisibility(0);
			
		}
		
	};
	private  OnClickListener backListener=new OnClickListener() {//������ذ�ť��Ӧʱ�¼�,����MainActivity

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}

	};
	/**
	 * ���ȷ���޸���������Ӧ�¼�
	 * 1.�ж������ԭ�����Ƿ��뱣����û�������ͬ����ͬ���������������ͬ����ʾ
	 * 2.�ж�������������������Ƿ���ͬ����ͬ������޸ı��棬�ر������������ʾ����ͬ����ʾ�����޸�
	 * 
	 */
	private  OnClickListener updateConfirmListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			String password_pre_txt=password_pre.getText().toString();//��ȡ�����ԭ����
			String password_new_txt=password_new.getText().toString();//��ȡ�����������
			String password_confirm_txt=password_confirm.getText().toString();//��ȡ���������������
			Log.e("ԭ����",password_pre_txt);
			Log.e("Constant.PASSWORD",Constant.PASSWORD);
			if(password_pre_txt.equals(Constant.PASSWORD)==false){//�����ԭ�����뱣��Ĳ���ͬ
				AlertDialog.Builder builder = new Builder(MyAccountActivity.this);
				builder.setTitle("����������ʾ")
						.setMessage("��ԭ���벻�������������룡")
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).create().show();
				
			}else if(password_new_txt.equals(password_confirm_txt)==false){//��������������벻һ��
				AlertDialog.Builder builder=new Builder(MyAccountActivity.this);
				builder.setTitle("����������ʾ")
						.setMessage("������������벻һ�£�����������")
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).create().show();
				
			}else{//ȷ������Ϸ��������Ӻ�̨���������޸�
				Log.e("USER:",Constant.USER);
				boolean result=UserService.updatePassword(Constant.USER, password_new_txt);
				if(result==true){
					Toast.makeText(getApplicationContext(), "��������ɹ�",
							Toast.LENGTH_SHORT).show();
					newPwdTab.setVisibility(4);

				}else{
					
				}
				
			}
			
		}

	};


}
