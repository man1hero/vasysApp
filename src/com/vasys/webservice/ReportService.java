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
 * ��ȡ�������ݣ�����״ͼ
 * @date 2015��6��24��13:44:18
 * @author lin
 *
 */
public class ReportService {
	private static final int TIME_OUT_DELAY = 3000;//��ʱʱ��
	private static String processURL = Constant.SERVER_URL;//��������ַ
	static HttpClient httpClient;

	public static JSONObject getMonthAlarm(String year,String month) {//��ȡһ���µı�����Ϣ
		JSONObject json = null;
		String url = "";
		try {
			// ����һ��HttpClient����
			httpClient = new DefaultHttpClient();
			httpClient.getParams().setIntParameter(  
                    HttpConnectionParams.SO_TIMEOUT, TIME_OUT_DELAY); // ��ʱ����  
			httpClient.getParams().setIntParameter(  
                    HttpConnectionParams.CONNECTION_TIMEOUT, TIME_OUT_DELAY);// ���ӳ�ʱ  
			// Զ�̵�¼url
			url = processURL + "getMonthAlarm.do?year="+year+"&&month="+month;
			Log.e("��������ַ", url);
			HttpGet request = new HttpGet(url);
			// ��ȡ��Ӧ�Ľ��
			HttpResponse response = httpClient.execute(request);
			Log.e("response", response.toString());
			// ��ȡHttpEntity
			HttpEntity entity = response.getEntity();
			// ��ȡ��Ӧ�Ľ����Ϣ
			String jsonText = EntityUtils.toString(entity, "UTF-8");

			json = new JSONObject(jsonText);
			// JSON�Ľ�������

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("unreachable", "���ӳ�ʱ");
			
		}finally{
			httpClient.getConnectionManager().shutdown();  
		}
		return json;
	}
	
	public static JSONObject getYearAlarm(String year){//��ȡһ���ڵı�������
		JSONObject json = null;
		String url = "";
		try {
			// ����һ��HttpClient����
			HttpClient httpClient = new DefaultHttpClient();
			// Զ�̵�¼url
			url = processURL + "getYearAlarm.do?year="+year;
			HttpGet request = new HttpGet(url);
			Log.e("request", request.toString());
			// ��ȡ��Ӧ�Ľ��
			HttpResponse response = httpClient.execute(request);
			Log.e("response", response.toString());
			// ��ȡHttpEntity
			HttpEntity entity = response.getEntity();
			// ��ȡ��Ӧ�Ľ����Ϣ
			String jsonText = EntityUtils.toString(entity, "UTF-8");

			json = new JSONObject(jsonText);//ת��jsonArray

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
		
	}

}
