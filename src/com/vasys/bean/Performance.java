package com.vasys.bean;
/**
 * 生产绩效实体类
 * @date 2015年7月22日15:17:42
 * @author lin
 *
 */
public class Performance {
	private String performanceId;//绩效编号
	private String productlineId;//生产线编号
	private String date;//日期
	private String atd_factor;//缺勤系数
	private String qty_factor;//产量系数
	private String performance_factor;//绩效系数
	public String getPerformanceId() {
		return performanceId;
	}
	public void setPerformanceId(String performanceId) {
		this.performanceId = performanceId;
	}
	public String getProductlineId() {
		return productlineId;
	}
	public void setProductlineId(String productlineId) {
		this.productlineId = productlineId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAtd_factor() {
		return atd_factor;
	}
	public void setAtd_factor(String atd_factor) {
		this.atd_factor = atd_factor;
	}
	public String getQty_factor() {
		return qty_factor;
	}
	public void setQty_factor(String qty_factor) {
		this.qty_factor = qty_factor;
	}
	public String getPerformance_factor() {
		return performance_factor;
	}
	public void setPerformance_factor(String performance_factor) {
		this.performance_factor = performance_factor;
	}
	
	
}
