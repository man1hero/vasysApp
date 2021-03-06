package com.vasys.webservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.util.Log;

import com.vasys.util.Constant;

public class CamService {
	private static String processURL = Constant.SERVER_URL;
	
	public static JSONArray getCam(){
		JSONArray json = null;
		String url = "";
		try {
            
			// 创建一个HttpClient对象
			HttpClient httpClient = new DefaultHttpClient();
			// 远程登录url
			url = processURL + "getCam.do?camId=";
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

			json = new JSONArray(jsonText);
			// JSON的解析过程

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
		
	}
}
