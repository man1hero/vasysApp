package com.vasys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

/**
 * �˷�������������������ַ�Ƿ����
 * @date 2015��7��21��09:15:30
 * @author lin
 *
 */
public class HostPing {
	/**
	 * ����ip��ַ������ping�Ľ��
	 * @param ip
	 * @return
	 */
	public static final boolean ping(String ip) {

		String result = null;

		try {

		Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);//ping3��

		// ��ȡping�����ݣ��ɲ��ӡ�

		InputStream input = p.getInputStream();

		BufferedReader in = new BufferedReader(new InputStreamReader(input));

		StringBuffer stringBuffer = new StringBuffer();

		String content = "";

		while ((content = in.readLine()) != null) {

		stringBuffer.append(content);

		}

		Log.e("TTT", "result content : " + stringBuffer.toString());


		// PING��״̬

		int status = p.waitFor();

		if (status == 0) {

		result = "successful~";

		return true;

		} else {

		result = "failed~ cannot reach the IP address";

		}

		} catch (IOException e) {

		result = "failed~ IOException";

		} catch (InterruptedException e) {

		result = "failed~ InterruptedException";

		} finally {

		Log.e("TTT", "result = " + result);

		}

		return false;

		}



}
