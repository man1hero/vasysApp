package com.vasys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

/**
 * 此方法是用来测试主机地址是否可用
 * @date 2015年7月21日09:15:30
 * @author lin
 *
 */
public class HostPing {
	/**
	 * 输入ip地址，返回ping的结果
	 * @param ip
	 * @return
	 */
	public static final boolean ping(String ip) {

		String result = null;

		try {

		Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);//ping3次

		// 读取ping的内容，可不加。

		InputStream input = p.getInputStream();

		BufferedReader in = new BufferedReader(new InputStreamReader(input));

		StringBuffer stringBuffer = new StringBuffer();

		String content = "";

		while ((content = in.readLine()) != null) {

		stringBuffer.append(content);

		}

		Log.e("TTT", "result content : " + stringBuffer.toString());


		// PING的状态

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
