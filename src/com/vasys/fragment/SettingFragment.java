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

	private TableRow accountManage;//账号管理
	private TableRow mySetting;//我的设置
	private TableRow myFavorite;//个人爱好
	private TableRow record;//操作记录
	private TableRow setting;//设置
	private TableRow feedback;//反馈
	private TableRow checkUpdate;//检查更新
	private TableRow about;//关于、新版介绍
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
		accountManage=(TableRow)settingLayout.findViewById(R.id.more_page_row0);//账户管理
			accountManage.setOnClickListener(accountManageListener);
		mySetting=(TableRow)settingLayout.findViewById(R.id.more_page_row1);//我的设置
			mySetting.setOnClickListener(mySettingListener);
		myFavorite=(TableRow)settingLayout.findViewById(R.id.more_page_row2);//个人爱好
			myFavorite.setOnClickListener(myFavoriteListener);
		record=(TableRow)settingLayout.findViewById(R.id.more_page_row3);//操作记录
			record.setOnClickListener(recordListener);
		setting=(TableRow)settingLayout.findViewById(R.id.more_page_row4);//设置
			setting.setOnClickListener(settingListener);
		feedback=(TableRow)settingLayout.findViewById(R.id.more_page_row5);//反馈
			feedback.setOnClickListener(feedbackListener);
		checkUpdate=(TableRow)settingLayout.findViewById(R.id.more_page_row6);//检查更新
			checkUpdate.setOnClickListener(checkUpdateListener);
		about=(TableRow)settingLayout.findViewById(R.id.more_page_row7);//关于
			about.setOnClickListener(aboutListener);

	}

	//用户管理点击事件
	private  OnClickListener accountManageListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getActivity(), MyAccountActivity.class);
			startActivity(intent);
		}
	};
	
	//我的设置点击事件
	private  OnClickListener mySettingListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}

	};
	//个人爱好点击事件
	private  OnClickListener myFavoriteListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(getActivity(), VideoViewActivity.class);
			startActivity(intent);
		}

	};
	//操作记录
	private  OnClickListener recordListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(getActivity(), LogActivity.class);
			startActivity(intent);
		}

	};
	//设置
	private  OnClickListener settingListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(getActivity(), SettingActivity.class);
			startActivity(intent);
		}

	};
	//反馈点击
	private  OnClickListener feedbackListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getActivity(), FeedbackActivity.class);
			startActivity(intent);
		}

	};
	//检查更新
	private  OnClickListener checkUpdateListener=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			try {
				versionName=getVersionName();//获取当前版本信息
				try{
					//从资源文件获取服务器地址
					String path=Constant.SERVER_URL+"Resources/xml/update.xml";
					System.out.println(path);
					//包装成URL对象
					URL url=new URL(path);
					HttpURLConnection conn=(HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);
					InputStream is=conn.getInputStream();
					info = getUpdateInfo(is);
					if(info.getVersion().equals(versionName)){
						Toast.makeText(getActivity(), "当前是最新版本，无需升级。", Toast.LENGTH_SHORT).show();
				
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
	//新版介绍
	private  OnClickListener aboutListener=new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.e("about", "点击了关于");
			Intent intent=new Intent(getActivity(), GuideActivity.class);
			startActivity(intent);

		}

	};
	
	private String getVersionName() throws NameNotFoundException{
		//获取packagemanager的实例
		PackageManager packageManager=this.getActivity().getPackageManager();
		//getPackageName()是当前的类的包名，0代表的是获取版本信息
		PackageInfo packInfo=packageManager.getPackageInfo(this.getActivity().getPackageName(), 0);
		return packInfo.versionName;
	}
	/**
	 * 获取更新信息
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static UpdateInfo getUpdateInfo(InputStream is)throws Exception{
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(is, "utf-8");//设置解析的数据源
		int type=parser.getEventType();
		UpdateInfo info=new UpdateInfo();//实体
		while(type!=XmlPullParser.END_DOCUMENT){
			switch (type) {
			case XmlPullParser.START_TAG:
				if("version".equals(parser.getName())){
					info.setVersion(parser.nextText());//获取版本号
				}else if("url".equals(parser.getName())){
					 info.setUrl(parser.nextText()); //获取要升级的APK文件 
				}else if("description".equals(parser.getName())){
					info.setDescription(parser.nextText());//获取该文件的信息
				}
				break;

			}
			type =parser.next();
		}
		return info;
	}
	
	/**
	 * 从服务器下载APK
	 * @param path
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static File getFileFromServer(String path,ProgressDialog pd)throws Exception{
		//如果相等的话表示当前的sd卡挂载在手机上并且是可用的
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			URL url=new URL(path);
			HttpURLConnection conn =(HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);//设置超时时间
			//获取到文件的大小
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
				//获取当前下载量
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
	 * 弹出对话框通知用户更新程序  
	 *  
	 * 弹出对话框的步骤： 
	 *  1.创建alertDialog的builder.   
	 *  2.要给builder设置属性, 对话框的内容,样式,按钮 
	 *  3.通过builder 创建一个对话框 
	 *  4.对话框show()出来   
	 */  
	protected void showUpdataDialog() {  
	    AlertDialog.Builder builer = new Builder(this.getActivity()) ;   
	    builer.setTitle("版本升级");  
	    builer.setMessage(info.getDescription());  
	    //当点确定按钮时从服务器上下载 新的apk 然后安装   
	    builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
	    public void onClick(DialogInterface dialog, int which) {  
	            Log.i("","下载apk,更新");  
	            downLoadApk();  
	        }     
	    });  
	    //当点取消按钮时进行登录  
	    builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
	        public void onClick(DialogInterface dialog, int which) {  
	            // TODO Auto-generated method stub 
	        	dialog.dismiss();
	        }  
	    });  
	    AlertDialog dialog = builer.create();  
	    dialog.show();  
	}  
	  
	/* 
	 * 从服务器中下载APK 
	 */  
	protected void downLoadApk() {  
	    final ProgressDialog pd;    //进度条对话框  
	    pd = new  ProgressDialog(getActivity());  
	    pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
	    pd.setMessage("正在下载更新");  
	    pd.show();  
	    new Thread(){  
	        @Override  
	        public void run() {  
	            try {  
	                File file = getFileFromServer(info.getUrl(), pd);  
	                sleep(3000);  
	                installApk(file);  
	                pd.dismiss(); //结束掉进度条对话框  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }}.start();  
	}  
	  
	//安装apk   
	protected void installApk(File file) {  
	    Intent intent = new Intent();  
	    //执行动作  
	    intent.setAction(Intent.ACTION_VIEW);  
	    //执行的数据类型  
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
