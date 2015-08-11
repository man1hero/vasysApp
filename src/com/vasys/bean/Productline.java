package com.vasys.bean;
/**
 * 生产线实体类
 * @author lin
 *@date 2015年7月22日15:06:59
 */
public class Productline {
	private String productlineId;//生产线编号
	private String productlineName;//生产线名字
	private String camAmount;//摄像机数量
	private String workerAmount;//工作人员数量
	private String stationAmount;//岗位数
	
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
