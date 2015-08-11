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
 * �û����ⷴ��
 * @date 2015-6-23 14:34:07
 * @author lin
 *
 */
public class FeedbackActivity extends Activity{
	public static final String TAG="feedback ";
	private Button submit;//�ύ��ť
	private ImageView back;//���ذ�ť
	private EditText feedback;//�����༭��
	private String feedbackConttent;//��������
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.feedback);
		submit=(Button)findViewById(R.id.btn_feedback);//�ύ
		back=(ImageView)findViewById(R.id.feedback_back);//����
		feedback=(EditText)findViewById(R.id.feedback_input);//�����
		submit.setOnClickListener(submitListener);
		back.setOnClickListener(backListener);
		
	}
	/**
	 * ����ύ������ť֮�󣬻Ὣ������Ϣͨ���ʼ���ʽ���͵���Ӧ��������
	 */
	private  OnClickListener submitListener=new OnClickListener() {//����ύ��ť��Ӧʱ�¼�
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//��ȡ����ķ�������
			try{
			feedbackConttent=feedback.getText().toString();
			 MailSenderInfo mailInfo = new MailSenderInfo();    
             mailInfo.setMailServerHost("smtp.163.com");    
             mailInfo.setMailServerPort("25");    
             mailInfo.setValidate(true);    
             mailInfo.setUserName("man1hero@163.com");  //��������ַ  
             mailInfo.setPassword("ltz779441120521");//������������    
             mailInfo.setFromAddress("man1hero@163.com");    
             mailInfo.setToAddress("779441120@qq.com");    
             mailInfo.setSubject(Constant.USER+"��������");    
             mailInfo.setContent(feedbackConttent);  
             SimpleMailSender sms = new SimpleMailSender();
           //���������ʽ    
             boolean result=sms.sendTextMail(mailInfo);
             Log.d(TAG+"result:", String.valueOf(result));
             if(result==false){
            	 AlertDialog.Builder builder = new Builder(
	     					FeedbackActivity.this);
	     			builder.setTitle("ʧ����ʾ")
	     					.setMessage("û�з��ͳɹ�����ȷ�Ͽ������磡")
	     					.setPositiveButton("ȷ��",
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
		     			builder.setTitle("������ʾ")
		     					.setMessage("���Ľ��������Ѿ��յ�����л������ϣ�")
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
			
			}catch (Exception e) {
				// TODO: handle exception
					Log.e(TAG+"senMail", e.getMessage());
			}
		
		}

	};
	private  OnClickListener backListener=new OnClickListener() {//������ذ�ť��Ӧʱ�¼�,����MainActivity

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}

	};

}
