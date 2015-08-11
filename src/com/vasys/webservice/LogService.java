package com.vasys.webservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

import com.vasys.util.Constant;

/**
 * 访问服务器获取日志
 * @author lin
 *
 */
public class LogService {
	private static String processURL = Constant.SERVER_URL;
	public static JSONObject getLog(String username) {
		JSONObject json = null;
		String url = "";
		try {
			// 创建一个HttpClient对象
			HttpClient httpClient = new DefaultHttpClient();
			// 远程登录url
			url = processURL + "getAllLog.do?username="+username+"&&page=1&&rows=100";
			HttpGet request = new HttpGet(url);
			Log.e("request", request.toString());
			// 获取响应的结果
			HttpResponse response = httpClient.execute(request);
			Log.e("response", response.toString());
			// 获取HttpEntity
			HttpEntity entity = response.getEntity();
			// 获取响应的结果信息
			String jsonText = EntityUtils.toString(entity, "UTF-8");

			json = new JSONObject(jsonText);
			// JSON的解析过程

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
