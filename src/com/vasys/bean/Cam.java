package com.vasys.bean;

/**
 * �����ʵ����
 * 
 * @date 2015-6-15 09:37:38
 * @author lin
 * 
 */
public class Cam {
	private String camId;// ��������
	private String camIp;// �����IP
	private String camLocation;// �����λ��
	private String camFunction;// ���������
	private String manufactureInfo;// ������Ϣ
	private String type;// �ͺ�

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
