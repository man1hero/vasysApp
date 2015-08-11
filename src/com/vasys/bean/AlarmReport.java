package com.vasys.bean;

/**
 * 
 */
public class AlarmReport {
	private String date;// 日期
	private String fireNum;// 火焰报警数量
	private String smokeNum;// 烟雾报警数量

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFireNum() {
		return fireNum;
	}

	public void setFireNum(String fireNum) {
		this.fireNum = fireNum;
	}

	public String getSmokeNum() {
		return smokeNum;
	}

	public void setSmokeNum(String smokeNum) {
		this.smokeNum = smokeNum;
	}

}
