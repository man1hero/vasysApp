package com.vasys.util;

/**
 * 对日期格式的数据进行格式化，因为解析出来的json日期数据后面会多.0，所以要把它去掉
 * @author lin
 *
 */
public class DateFormat {
	public static String format(String date){
		date=date.substring(0, 19);
		return date;
	}

}
