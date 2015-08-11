package com.vasys.bean;

/**
 * 用户实体类
 * 
 * @date 2015-6-15 09:12:50
 * @author lin
 * 
 */
public class User {
	private String username;// 用户名
	private String password;// 密码
	private boolean isAlive;// 是否在线

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
