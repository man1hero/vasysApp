package com.vasys.webservice;

import org.json.JSONObject;

import com.vasys.util.Constant;

public class ProductlineService {
	private static String processURL = Constant.SERVER_URL;

	/**
	 * 获取生产线
	 * @return JSONObject
	 */
	public JSONObject getProductline(){
		String url=processURL+"getProductline.do";
		return BaseService.getService(url);
	}
}
