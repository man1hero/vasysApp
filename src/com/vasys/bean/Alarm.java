package com.vasys.bean;


/**
 * ����ʵ����
 * 
 * @date 2015-6-15 09:45:19
 * @author lin
 * 
 */
public class Alarm {
	private String alarmId;// �������
	private String camId;// ��������
	private String alarmTime;// ����ʱ��
	private String alarmType;// ��������
	private String duration;// ����ʱ��
	private String alarmLocation;// ����λ��

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
