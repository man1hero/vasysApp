package com.vasys.bean;

/**
 * �û�ʵ����
 * 
 * @date 2015-6-15 09:12:50
 * @author lin
 * 
 */
public class User {
	private String username;// �û���
	private String password;// ����
	private boolean isAlive;// �Ƿ�����

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
