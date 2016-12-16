/** 
 * @Project_Name breeze 
 * @File_Name ConvertUtil.java 
 * @Date 2016年11月17日下午5:28:52 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.breeze.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年11月17日
 * @since JDK 1.7
 * @Function 数据转换工具类
 * @Reason
 */
public class ConvertUtil {

	/**
	 * 将value更改成clazz对应的类型
	 * 
	 * @param value
	 * @param clazz
	 * @return
	 */
	public static Object convert(String value, Class<?> clazz) {
		Object retVal = null;
		if (clazz == null) {
			return retVal;
		}
		String clazzName = clazz.getSimpleName();
		if (clazzName.equalsIgnoreCase("int") || clazzName.equalsIgnoreCase("integer")) {
			retVal = Integer.parseInt(value);
		} else if (clazzName.equalsIgnoreCase("float")) {
			retVal = Float.parseFloat(value);
		} else if (clazzName.equalsIgnoreCase("double")) {
			retVal = Double.parseDouble(value);
		} else if (clazzName.equalsIgnoreCase("date")) {
			retVal = getDate(value);
		} else {
			//对字符进行转码,如果不是中文则转换成中文
			if(!CharacterUtil.isChinese(value)) {
				retVal = CharacterUtil.transcoding(value).trim();
			}else {
				retVal = value.trim();
			}
		}
		return retVal;
	}
	
	public static Date getDate(String value) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d = null;
		try {
			d = sim.parse(value);
		} catch (ParseException e) {
			sim = new SimpleDateFormat("yyyy/MM/dd");
			try {
				d = sim.parse(value);
			} catch (ParseException e1) {
				sim = new SimpleDateFormat("yyyy-MM-dd");
				try {
					d = sim.parse(value);
				} catch (ParseException e2) {
					return d;
				}
			}
		}
		return d;
	}
}
