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
 * 账户管理，从设置界面点击进入，点击返回MainActivity
 * @date 2015年6月23日15:33:27
 * @author lin
 *
 */
public class MyAccountActivity extends Activity{
	private TextView userText;//用户名
	private TextView passwordText;//密码
	private EditText password_pre;//原密码
	private EditText password_new;//新密码
	private EditText password_confirm;//确认密码
	private Button pwdNew;//重设密码
	private Button ok;//确定
	private Button updateConfirm;//确认修改密码
	private TableLayout newPwdTab;//表格布局
	private ImageView back;//返回按钮
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account);
		userText=(TextView)findViewById(R.id.account_user);
		passwordText=(TextView)findViewById(R.id.account_password);
		pwdNew=(Button)findViewById(R.id.account_update_pwd);//修改密码按钮
		ok=(Button)findViewById(R.id.account_ok);//确定按钮
		updateConfirm=(Button)findViewById(R.id.update_confirm);//确认修改按钮
		password_pre=(EditText)findViewById(R.id.password_pre);//原密码
		password_new=(EditText)findViewById(R.id.password_new);//第一次输入的新密码
		password_confirm=(EditText)findViewById(R.id.password_confirm);//确认输入的密码
		
		newPwdTab=(TableLayout)findViewById(R.id.account_password_tab);
		back=(ImageView)findViewById(R.id.account_back);
		
		userText.setText(Constant.USER);
		passwordText.setText(Constant.PASSWORD);
		pwdNew.setOnClickListener(newPwdListener);
		back.setOnClickListener(backListener);
		ok.setOnClickListener(backListener);
		updateConfirm.setOnClickListener(updateConfirmListener);
	}
	
	private  OnClickListener newPwdListener=new OnClickListener(){//点击重新设置密码响应，显示重设密码的输入框

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			newPwdTab.setVisibility(0);
			
		}
		
	};
	private  OnClickListener backListener=new OnClickListener() {//点击返回按钮相应时事件,返回MainActivity

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}

	};
	/**
	 * 点击确定修改密码后的相应事件
	 * 1.判断输入的原密码是否与保存的用户密码相同，相同则继续操作，不相同则提示
	 * 2.判断重新输入的密码两次是否相同，相同则进行修改保存，关闭重设密码的显示，不同则提示进行修改
	 * 
	 */
	private  OnClickListener updateConfirmListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			String password_pre_txt=password_pre.getText().toString();//获取输入的原密码
			String password_new_txt=password_new.getText().toString();//获取输入的新密码
			String password_confirm_txt=password_confirm.getText().toString();//获取重新输入的新密码
			Log.e("原密码",password_pre_txt);
			Log.e("Constant.PASSWORD",Constant.PASSWORD);
			if(password_pre_txt.equals(Constant.PASSWORD)==false){//输入的原密码与保存的不相同
				AlertDialog.Builder builder = new Builder(MyAccountActivity.this);
				builder.setTitle("重设密码提示")
						.setMessage("与原密码不符，请重新输入！")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).create().show();
				
			}else if(password_new_txt.equals(password_confirm_txt)==false){//两次输入的新密码不一致
				AlertDialog.Builder builder=new Builder(MyAccountActivity.this);
				builder.setTitle("输入密码提示")
						.setMessage("两次输入的密码不一致，请重新输入")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).create().show();
				
			}else{//确定输入合法，则连接后台进行密码修改
				Log.e("USER:",Constant.USER);
				boolean result=UserService.updatePassword(Constant.USER, password_new_txt);
				if(result==true){
					Toast.makeText(getApplicationContext(), "重设密码成功",
							Toast.LENGTH_SHORT).show();
					newPwdTab.setVisibility(4);

				}else{
					
				}
				
			}
			
		}

	};


}
