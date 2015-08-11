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
 * �û���������࣬�������û���صĲ���
 * @author lin
 *
 */
public class UserService {
	private static String ProcessURL=Constant.SERVER_URL;
	/**
	 * �������û�����������и�������
	 * @param username
	 * @param pwdNew
	 * @return �ɹ��򷵻�1��ʧ���򷵻�0
	 */
	public static boolean updatePassword(String username,String pwdNew){
		String url = "";
		boolean flag=false;
		try {
			// ����һ��HttpClient����
			HttpClient httpClient = new DefaultHttpClient();
			// Զ�̵�¼url
			url = ProcessURL + "AndroidUpdateUser.do?username=" + username + "&&password=" + pwdNew;
			HttpGet request = new HttpGet(url);
			Log.d("request", request.toString());
			// ��ȡ��Ӧ�Ľ��
			HttpResponse response = httpClient.execute(request);
			Log.d("response", response.toString());
			// ��ȡHttpEntity
			HttpEntity entity = response.getEntity();
			// ��ȡ��Ӧ�Ľ����Ϣ
			String json = EntityUtils.toString(entity, "UTF-8");
			Log.d("json", json);
			// JSON�Ľ�������
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
