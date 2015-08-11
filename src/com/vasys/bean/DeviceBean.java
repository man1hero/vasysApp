package com.vasys.bean;

import java.io.File;

import android.os.Environment;

/**
 * �豸ʵ����
 * 
 * @author lin
 * 
 */
public class DeviceBean {
	private String ip;// ip
	private String port;// �˿ں� 8000
	private String userName;// �û���
	private String passWord;// ����
	private String channel;// ͨ����
	
	//ץͼ���·��
	public String filePath=getSDRootPath()+"/HCNetSDK";
	//
	public static String getSDRootPath(){
		File sdDir=null;
		boolean sdCardExist=hasSdcard();
		//�ж�
		if(sdCardExist){
			sdDir=Environment.getExternalStorageDirectory();
			return sdDir.toString();
		}else{
			return null;
		}
	}
	/**
	 * �Ƿ����sd���� 
	 * @return true ���ڣ�false ������
	 */
	public static boolean hasSdcard(){
		String status=Environment.getExternalStorageState();
		if(status.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
