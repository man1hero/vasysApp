package com.vasys.bean;

import java.io.File;

import android.os.Environment;

/**
 * 设备实体类
 * 
 * @author lin
 * 
 */
public class DeviceBean {
	private String ip;// ip
	private String port;// 端口号 8000
	private String userName;// 用户名
	private String passWord;// 密码
	private String channel;// 通道号
	
	//抓图存放路径
	public String filePath=getSDRootPath()+"/HCNetSDK";
	//
	public static String getSDRootPath(){
		File sdDir=null;
		boolean sdCardExist=hasSdcard();
		//判断
		if(sdCardExist){
			sdDir=Environment.getExternalStorageDirectory();
			return sdDir.toString();
		}else{
			return null;
		}
	}
	/**
	 * 是否存在sd卡， 
	 * @return true 存在；false 不存在
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
