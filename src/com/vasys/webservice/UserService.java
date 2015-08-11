package com.vasys.webservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.vasys.util.Constant;

/**
 * 用户管理服务类，管理与用户相关的操作
 * @author lin
 *
 */
public class UserService {
	private static String ProcessURL=Constant.SERVER_URL;
	/**
	 * 输入新用户名和密码进行更新密码
	 * @param username
	 * @param pwdNew
	 * @return 成功则返回1，失败则返回0
	 */
	public static boolean updatePassword(String username,String pwdNew){
		String url = "";
		boolean flag=false;
		try {
			// 创建一个HttpClient对象
			HttpClient httpClient = new DefaultHttpClient();
			// 远程登录url
			url = ProcessURL + "AndroidUpdateUser.do?username=" + username + "&&password=" + pwdNew;
			HttpGet request = new HttpGet(url);
			Log.d("request", request.toString());
			// 获取响应的结果
			HttpResponse response = httpClient.execute(request);
			Log.d("response", response.toString());
			// 获取HttpEntity
			HttpEntity entity = response.getEntity();
			// 获取响应的结果信息
			String json = EntityUtils.toString(entity, "UTF-8");
			Log.d("json", json);
			// JSON的解析过程
			if (Boolean.parseBoolean(json) == true) {
				flag=true;
			} else {
				flag=false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}

}
