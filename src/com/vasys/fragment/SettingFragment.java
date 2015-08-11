package com.vasys.fragment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.xmlpull.v1.XmlPullParser;

import com.vasys.R;
import com.vasys.activity.FeedbackActivity;
import com.vasys.activity.GuideActivity;
import com.vasys.activity.LogActivity;
import com.vasys.activity.MainActivity;
import com.vasys.activity.MyAccountActivity;
import com.vasys.activity.SettingActivity;
import com.vasys.activity.VideoViewActivity;
import com.vasys.bean.UpdateInfo;
import com.vasys.util.Constant;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.Toast;

public class SettingFragment extends BaseFragment {

	private TableRow accountManage;//�˺Ź���
	private TableRow mySetting;//�ҵ�����
	private TableRow myFavorite;//���˰���
	private TableRow record;//������¼
	private TableRow setting;//����
	private TableRow feedback;//����
	private TableRow checkUpdate;//������
	private TableRow about;//���ڡ��°����
	private View settingLayout;
	
	private String versionName;
	private UpdateInfo info;
	private MainActivity mMainActivity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 settingLayout = inflater.inflate(R.layout.setting_layout,
				container, false);
		 findView();
		return settingLayout;
	}
	
	public void findView(){
		mMainActivity = (MainActivity) getActivity();
		accountManage=(TableRow)settingLayout.findViewById(R.id.more_page_row0);//�˻�����
			accountManage.setOnClickListener(accountManageListener);
		mySetting=(TableRow)settingLayout.findViewById(R.id.more_page_row1);//�ҵ�����
			mySetting.setOnClickListener(mySettingListener);
		myFavorite=(TableRow)settingLayout.findViewById(R.id.more_page_row2);//���˰���
			myFavorite.setOnClickListener(myFavoriteListener);
		record=(TableRow)settingLayout.findViewById(R.id.more_page_row3);//������¼
			record.setOnClickListener(recordListener);
		setting=(TableRow)settingLayout.findViewById(R.id.more_page_row4);//����
			setting.setOnClickListener(settingListener);
		feedback=(TableRow)settingLayout.findViewById(R.id.more_page_row5);//����
			feedback.setOnClickListener(feedbackListener);
		checkUpdate=(TableRow)settingLayout.findViewById(R.id.more_page_row6);//������
			checkUpdate.setOnClickListener(checkUpdateListener);
		about=(TableRow)settingLayout.findViewById(R.id.more_page_row7);//����
			about.setOnClickListener(aboutListener);

	}

	//�û��������¼�
	private  OnClickListener accountManageListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getActivity(), MyAccountActivity.class);
			startActivity(intent);
		}
	};
	
	//�ҵ����õ���¼�
	private  OnClickListener mySettingListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}

	};
	//���˰��õ���¼�
	private  OnClickListener myFavoriteListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(getActivity(), VideoViewActivity.class);
			startActivity(intent);
		}

	};
	//������¼
	private  OnClickListener recordListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(getActivity(), LogActivity.class);
			startActivity(intent);
		}

	};
	//����
	private  OnClickListener settingListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(getActivity(), SettingActivity.class);
			startActivity(intent);
		}

	};
	//�������
	private  OnClickListener feedbackListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getActivity(), FeedbackActivity.class);
			startActivity(intent);
		}

	};
	//������
	private  OnClickListener checkUpdateListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			try {
				versionName=getVersionName();//��ȡ��ǰ�汾��Ϣ
				try{
					//����Դ�ļ���ȡ��������ַ
					String path=Constant.SERVER_URL+"Resources/xml/update.xml";
					System.out.println(path);
					//��װ��URL����
					URL url=new URL(path);
					HttpURLConnection conn=(HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);
					InputStream is=conn.getInputStream();
					info = getUpdateInfo(is);
					if(info.getVersion().equals(versionName)){
						Toast.makeText(getActivity(), "��ǰ�����°汾������������", Toast.LENGTH_SHORT).show();
				
					}else{
						 showUpdataDialog();
					}
				}catch(Exception e){
			           
					e.printStackTrace();
				}
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			}
		

	};
	//�°����
	private  OnClickListener aboutListener=new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.e("about", "����˹���");
			Intent intent=new Intent(getActivity(), GuideActivity.class);
			startActivity(intent);

		}

	};
	
	private String getVersionName() throws NameNotFoundException{
		//��ȡpackagemanager��ʵ��
		PackageManager packageManager=this.getActivity().getPackageManager();
		//getPackageName()�ǵ�ǰ����İ�����0������ǻ�ȡ�汾��Ϣ
		PackageInfo packInfo=packageManager.getPackageInfo(this.getActivity().getPackageName(), 0);
		return packInfo.versionName;
	}
	/**
	 * ��ȡ������Ϣ
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static UpdateInfo getUpdateInfo(InputStream is)throws Exception{
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(is, "utf-8");//���ý���������Դ
		int type=parser.getEventType();
		UpdateInfo info=new UpdateInfo();//ʵ��
		while(type!=XmlPullParser.END_DOCUMENT){
			switch (type) {
			case XmlPullParser.START_TAG:
				if("version".equals(parser.getName())){
					info.setVersion(parser.nextText());//��ȡ�汾��
				}else if("url".equals(parser.getName())){
					 info.setUrl(parser.nextText()); //��ȡҪ������APK�ļ� 
				}else if("description".equals(parser.getName())){
					info.setDescription(parser.nextText());//��ȡ���ļ�����Ϣ
				}
				break;

			}
			type =parser.next();
		}
		return info;
	}
	
	/**
	 * �ӷ���������APK
	 * @param path
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static File getFileFromServer(String path,ProgressDialog pd)throws Exception{
		//�����ȵĻ���ʾ��ǰ��sd���������ֻ��ϲ����ǿ��õ�
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			URL url=new URL(path);
			HttpURLConnection conn =(HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);//���ó�ʱʱ��
			//��ȡ���ļ��Ĵ�С
			pd.setMax(conn.getContentLength());
			InputStream is=conn.getInputStream();
			File file=new File(Environment.getExternalStorageDirectory(), "vasys.apk");
			Log.e("file", file.toString());
			FileOutputStream fos=new FileOutputStream(file);
			BufferedInputStream bis=new BufferedInputStream(is);
			byte[] buffer=new byte[1024];
			int len;
			int total=0;
			while((len =bis.read(buffer))!=-1){
				fos.write(buffer,0,len);
				total+=len;
				//��ȡ��ǰ������
				pd.setProgress(total);
			}
			fos.close();
			bis.close();
			is.close();
			return file;
		}else{
			return null;
		}
		
	}

	/* 
	 *  
	 * �����Ի���֪ͨ�û����³���  
	 *  
	 * �����Ի���Ĳ��裺 
	 *  1.����alertDialog��builder.   
	 *  2.Ҫ��builder��������, �Ի��������,��ʽ,��ť 
	 *  3.ͨ��builder ����һ���Ի��� 
	 *  4.�Ի���show()����   
	 */  
	protected void showUpdataDialog() {  
	    AlertDialog.Builder builer = new Builder(this.getActivity()) ;   
	    builer.setTitle("�汾����");  
	    builer.setMessage(info.getDescription());  
	    //����ȷ����ťʱ�ӷ����������� �µ�apk Ȼ��װ   
	    builer.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {  
	    public void onClick(DialogInterface dialog, int which) {  
	            Log.i("","����apk,����");  
	            downLoadApk();  
	        }     
	    });  
	    //����ȡ����ťʱ���е�¼  
	    builer.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {  
	        public void onClick(DialogInterface dialog, int which) {  
	            // TODO Auto-generated method stub 
	        	dialog.dismiss();
	        }  
	    });  
	    AlertDialog dialog = builer.create();  
	    dialog.show();  
	}  
	  
	/* 
	 * �ӷ�����������APK 
	 */  
	protected void downLoadApk() {  
	    final ProgressDialog pd;    //�������Ի���  
	    pd = new  ProgressDialog(getActivity());  
	    pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
	    pd.setMessage("�������ظ���");  
	    pd.show();  
	    new Thread(){  
	        @Override  
	        public void run() {  
	            try {  
	                File file = getFileFromServer(info.getUrl(), pd);  
	                sleep(3000);  
	                installApk(file);  
	                pd.dismiss(); //�������������Ի���  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }}.start();  
	}  
	  
	//��װapk   
	protected void installApk(File file) {  
	    Intent intent = new Intent();  
	    //ִ�ж���  
	    intent.setAction(Intent.ACTION_VIEW);  
	    //ִ�е���������  
	    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");  
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    startActivity(intent);  
	}  
	
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_SETTINGS;

	}

}
