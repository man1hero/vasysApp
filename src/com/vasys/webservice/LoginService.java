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
 * ��¼service
 * 
 * @author lin
 * 
 */
public class LoginService {
	private static String processURL = Constant.SERVER_URL;
	private static final int TIME_OUT_DELAY = 3000;//��ʱʱ��
	static HttpClient httpClient;
	/**
	 * ��֤��¼
	 * 
	 * @param name
	 * @param pass
	 * @return
	 */
	public static String login(String name, String pass) {
		String result = "";
		String url = "";
		try {
			// ����һ��HttpClient����
			 httpClient = new DefaultHttpClient();
			 httpClient.getParams().setIntParameter(  
	                    HttpConnectionParams.SO_TIMEOUT, TIME_OUT_DELAY); // ��ʱ����  
				httpClient.getParams().setIntParameter(  
	                    HttpConnectionParams.CONNECTION_TIMEOUT, TIME_OUT_DELAY);// ���ӳ�ʱ  
			// Զ�̵�¼url
			url = processURL + "AndroidLogin.do?username=" + name + "&&password=" + pass;
			HttpGet request = new HttpGet(url);
			Log.e("request", request.getRequestLine().toString());
			// ��ȡ��Ӧ�Ľ��
			HttpResponse response = httpClient.execute(request);
			Log.e("response", response.getStatusLine().toString());
			// ��ȡHttpEntity
			HttpEntity entity = response.getEntity();
			
			// ��ȡ��Ӧ�Ľ����Ϣ
			String json = EntityUtils.toString(entity, "UTF-8");
			Log.e("json", json);
			// JSON�Ľ�������
			if (Boolean.parseBoolean(json) == true) {
				result = "��¼�ɹ�";
			} else {
				result = "��¼ʧ��";
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("unreachable", "���ӳ�ʱ");

		}finally{
			httpClient.getConnectionManager().shutdown();  
			Log.e("shutdown", "httpClient shutdown");
		}
		return result;

	}

}
