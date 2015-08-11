package com.vasys.webservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.vasys.util.Constant;

import android.util.Log;

/**
 * 登录service
 * 
 * @author lin
 * 
 */
public class LoginService {
	private static String processURL = Constant.SERVER_URL;
	private static final int TIME_OUT_DELAY = 3000;//超时时间
	static HttpClient httpClient;
	/**
	 * 验证登录
	 * 
	 * @param name
	 * @param pass
	 * @return
	 */
	public static String login(String name, String pass) {
		String result = "";
		String url = "";
		try {
			// 创建一个HttpClient对象
			 httpClient = new DefaultHttpClient();
			 httpClient.getParams().setIntParameter(  
	                    HttpConnectionParams.SO_TIMEOUT, TIME_OUT_DELAY); // 超时设置  
				httpClient.getParams().setIntParameter(  
	                    HttpConnectionParams.CONNECTION_TIMEOUT, TIME_OUT_DELAY);// 连接超时  
			// 远程登录url
			url = processURL + "AndroidLogin.do?username=" + name + "&&password=" + pass;
			HttpGet request = new HttpGet(url);
			Log.e("request", request.getRequestLine().toString());
			// 获取响应的结果
			HttpResponse response = httpClient.execute(request);
			Log.e("response", response.getStatusLine().toString());
			// 获取HttpEntity
			HttpEntity entity = response.getEntity();
			
			// 获取响应的结果信息
			String json = EntityUtils.toString(entity, "UTF-8");
			Log.e("json", json);
			// JSON的解析过程
			if (Boolean.parseBoolean(json) == true) {
				result = "登录成功";
			} else {
				result = "登录失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("unreachable", "连接超时");

		}finally{
			httpClient.getConnectionManager().shutdown();  
			Log.e("shutdown", "httpClient shutdown");
		}
		return result;

	}

}
