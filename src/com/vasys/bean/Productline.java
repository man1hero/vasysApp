package com.vasys.bean;
/**
 * ������ʵ����
 * @author lin
 *@date 2015��7��22��15:06:59
 */
public class Productline {
	private String productlineId;//�����߱��
	private String productlineName;//����������
	private String camAmount;//���������
	private String workerAmount;//������Ա����
	private String stationAmount;//��λ��
	
	public String getProductlineId() {
		return productlineId;
	}
	public void setProductlineId(String productlineId) {
		this.productlineId = productlineId;
	}
	public String getProductlineName() {
		return productlineName;
	}
	public void setProductlineName(String productlineName) {
		this.productlineName = productlineName;
	}
	public String getCamAmount() {
		return camAmount;
	}
	public void setCamAmount(String camAmount) {
		this.camAmount = camAmount;
	}
	public String getWorkerAmount() {
		return workerAmount;
	}
	public void setWorkerAmount(String workerAmount) {
		this.workerAmount = workerAmount;
	}
	public String getStationAmount() {
		return stationAmount;
	}
	public void setStationAmount(String stationAmount) {
		this.stationAmount = stationAmount;
	}
	
	
}
