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

/**
 * 获取报表数据，填充饼状图
 * @date 2015年6月24日13:44:18
 * @author lin
 *
 */
public class ReportService {
	private static final int TIME_OUT_DELAY = 3000;//超时时间
	private static String processURL = Constant.SERVER_URL;//服务器地址
	static HttpClient httpClient;

	public static JSONObject getMonthAlarm(String year,String month) {//获取一个月的报警信息
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
			url = processURL + "getMonthAlarm.do?year="+year+"&&month="+month;
			Log.e("服务器地址", url);
			HttpGet request = new HttpGet(url);
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
			Log.e("unreachable", "连接超时");
			
		}finally{
			httpClient.getConnectionManager().shutdown();  
		}
		return json;
	}
	
	public static JSONObject getYearAlarm(String year){//获取一年内的报警数据
		JSONObject json = null;
		String url = "";
		try {
			// 创建一个HttpClient对象
			HttpClient httpClient = new DefaultHttpClient();
			// 远程登录url
			url = processURL + "getYearAlarm.do?year="+year;
			HttpGet request = new HttpGet(url);
			Log.e("request", request.toString());
			// 获取响应的结果
			HttpResponse response = httpClient.execute(request);
			Log.e("response", response.toString());
			// 获取HttpEntity
			HttpEntity entity = response.getEntity();
			// 获取响应的结果信息
			String jsonText = EntityUtils.toString(entity, "UTF-8");

			json = new JSONObject(jsonText);//转成jsonArray

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
		
	}

}
