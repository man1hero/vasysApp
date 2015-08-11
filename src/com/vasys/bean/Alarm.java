package com.vasys.bean;


/**
 * 报警实体类
 * 
 * @date 2015-6-15 09:45:19
 * @author lin
 * 
 */
public class Alarm {
	private String alarmId;// 报警编号
	private String camId;// 摄像机编号
	private String alarmTime;// 报警时间
	private String alarmType;// 报警类型
	private String duration;// 持续时间
	private String alarmLocation;// 报警位置

	public Alarm() {

	}

	public Alarm(String AlarmId, String CamId, String AlarmTime,
			String AlarmType, String Duration, String AlarmLocation) {
		super();
		alarmId = AlarmId;
		camId = CamId;
		alarmTime = AlarmTime;
		alarmType = AlarmType;
		duration = Duration;
		alarmLocation = AlarmLocation;

	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getCamId() {
		return camId;
	}

	public void setCamId(String camId) {
		this.camId = camId;
	}

	public String getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getAlarmLocation() {
		return alarmLocation;
	}

	public void setAlarmLocation(String alarmLocation) {
		this.alarmLocation = alarmLocation;
	}

}
