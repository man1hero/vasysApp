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
 * ������¹����Զ�����������°汾���еĻ�������������°汾���а�װ����
 * @date 2015-7-8 10:25:52
 * @author lin
 *
 */
public class UpdateManager{
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
			File file=new File(Environment.getExternalStorageDirectory(), "updata.apk");
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
	
	

}