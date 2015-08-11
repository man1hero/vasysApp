package com.vasys.bean;

/**
 * 摄像机实体类
 * 
 * @date 2015-6-15 09:37:38
 * @author lin
 * 
 */
public class Cam {
	private String camId;// 摄像机编号
	private String camIp;// 摄像机IP
	private String camLocation;// 摄像机位置
	private String camFunction;// 摄像机功能
	private String manufactureInfo;// 生产信息
	private String type;// 型号

	public String getCamId() {
		return camId;
	}

	public void setCamId(String camId) {
		this.camId = camId;
	}

	public String getCamIp() {
		return camIp;
	}

	public void setCamIp(String camIp) {
		this.camIp = camIp;
	}

	public String getCamLocation() {
		return camLocation;
	}

	public void setCamLocation(String camLocation) {
		this.camLocation = camLocation;
	}

	public String getCamFunction() {
		return camFunction;
	}

	public void setCamFunction(String camFunction) {
		this.camFunction = camFunction;
	}

	public String getManufactureInfo() {
		return manufactureInfo;
	}

	public void setManufactureInfo(String manufactureInfo) {
		this.manufactureInfo = manufactureInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
