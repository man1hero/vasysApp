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
 * ��ȡ����
 * @author lin
 *
 */
public class ProductionService {
	private static String processURL = Constant.SERVER_URL;
	
	public JSONObject getProduction(){
		JSONObject json = null;
		String url = "";
		try {
            
			// ����һ��HttpClient����
			HttpClient httpClient = new DefaultHttpClient();
			// Զ�̵�¼url
			url = processURL + "getPerformance.do?cam=&&page=1&&rows=10";
			HttpGet request = new HttpGet(url);
			Log.e("request", request.toString());
			// ��ȡ��Ӧ�Ľ��
			HttpResponse response = httpClient.execute(request);
			Log.e("responseStatus", response.getStatusLine().getStatusCode()+"");
			// ��ȡHttpEntity
			HttpEntity entity = response.getEntity();
			// ��ȡ��Ӧ�Ľ����Ϣ
			String jsonText = EntityUtils.toString(entity, "UTF-8");

			json = new JSONObject(jsonText);
			// JSON�Ľ�������

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
