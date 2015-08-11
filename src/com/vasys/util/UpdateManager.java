package com.vasys.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import android.app.ProgressDialog;
import android.os.Environment;
import android.util.Xml;

import com.vasys.bean.UpdateInfo;




/**
 * 软件更新管理，自动检测有无最新版本，有的话则进行下载最新版本进行安装更新
 * @date 2015-7-8 10:25:52
 * @author lin
 *
 */
public class UpdateManager{
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
			File file=new File(Environment.getExternalStorageDirectory(), "updata.apk");
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
	
	

}