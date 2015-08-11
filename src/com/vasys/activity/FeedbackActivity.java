package com.vasys.activity;

import com.vasys.R;
import com.vasys.bean.MailSenderInfo;
import com.vasys.util.Constant;
import com.vasys.util.SimpleMailSender;

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
/**
 * 用户问题反馈
 * @date 2015-6-23 14:34:07
 * @author lin
 *
 */
public class FeedbackActivity extends Activity{
	public static final String TAG="feedback ";
	private Button submit;//提交按钮
	private ImageView back;//返回按钮
	private EditText feedback;//反馈编辑框
	private String feedbackConttent;//反馈内容
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.feedback);
		submit=(Button)findViewById(R.id.btn_feedback);//提交
		back=(ImageView)findViewById(R.id.feedback_back);//返回
		feedback=(EditText)findViewById(R.id.feedback_input);//输入框
		submit.setOnClickListener(submitListener);
		back.setOnClickListener(backListener);
		
	}
	/**
	 * 点击提交反馈按钮之后，会将反馈信息通过邮件方式发送到相应的邮箱里
	 */
	private  OnClickListener submitListener=new OnClickListener() {//点击提交按钮相应时事件
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//获取输入的反馈内容
			try{
			feedbackConttent=feedback.getText().toString();
			 MailSenderInfo mailInfo = new MailSenderInfo();    
             mailInfo.setMailServerHost("smtp.163.com");    
             mailInfo.setMailServerPort("25");    
             mailInfo.setValidate(true);    
             mailInfo.setUserName("man1hero@163.com");  //你的邮箱地址  
             mailInfo.setPassword("ltz779441120521");//您的邮箱密码    
             mailInfo.setFromAddress("man1hero@163.com");    
             mailInfo.setToAddress("779441120@qq.com");    
             mailInfo.setSubject(Constant.USER+"反馈建议");    
             mailInfo.setContent(feedbackConttent);  
             SimpleMailSender sms = new SimpleMailSender();
           //发送文体格式    
             boolean result=sms.sendTextMail(mailInfo);
             Log.d(TAG+"result:", String.valueOf(result));
             if(result==false){
            	 AlertDialog.Builder builder = new Builder(
	     					FeedbackActivity.this);
	     			builder.setTitle("失败提示")
	     					.setMessage("没有发送成功，请确认开启网络！")
	     					.setPositiveButton("确定",
	     							new DialogInterface.OnClickListener() {
	     								@Override
	     								public void onClick(
	     										DialogInterface dialog,
	     										int which) {
	     									dialog.dismiss();
	     								}
	     							}).create().show();	

             }else{
					 AlertDialog.Builder builder = new Builder(
		     					FeedbackActivity.this);
		     			builder.setTitle("反馈提示")
		     					.setMessage("您的建议我们已经收到，感谢您的配合！")
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
			
			}catch (Exception e) {
				// TODO: handle exception
					Log.e(TAG+"senMail", e.getMessage());
			}
		
		}

	};
	private  OnClickListener backListener=new OnClickListener() {//点击返回按钮相应时事件,返回MainActivity

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}

	};

}
