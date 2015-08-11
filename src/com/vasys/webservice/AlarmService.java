package com.vasys.webservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.vasys.util.Constant;

import android.util.Log;

public class AlarmService {
	private static String processURL = Constant.SERVER_URL;
	private static final int TIME_OUT_DELAY = 10000;//超时时间,10秒
	static HttpClient httpClient;
	
	public static JSONObject getAlarm() {
		JSONObject json = null;
		String url = "";
		try {
            
			// 创建一个HttpClient对象
			httpClient = new DefaultHttpClient();
			httpClient.getParams().setIntParameter(  
                    HttpConnectionParams.SO_TIMEOUT, TIME_OUT_DELAY); // 超时设置  
			httpClient.getParams().setIntParameter(  
                    HttpConnectionParams.CONNECTION_TIMEOUT, TIME_OUT_DELAY);// 连接超时  
			// 远程登录url
			url = processURL + "getAlarm.do?cam=&&page=1&&rows=10";
			HttpGet request = new HttpGet(url);
			Log.e("request", request.toString());
			// 获取响应的结果
			HttpResponse response = httpClient.execute(request);
			Log.e("responseStatus", response.getStatusLine().getStatusCode()+"");
			Log.e("response", response.toString());
			// 获取HttpEntity
			HttpEntity entity = response.getEntity();
			// 获取响应的结果信息
			String jsonText = EntityUtils.toString(entity, "UTF-8");

			json = new JSONObject(jsonText);
			// JSON的解析过程

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("unreachable", "连接超时");
		}finally{
			httpClient.getConnectionManager().shutdown();  
		}
		return json;
	}
}
