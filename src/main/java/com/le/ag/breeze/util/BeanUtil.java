/** 
 * @Project_Name breeze 
 * @File_Name BeanUtil.java 
 * @Date 2016年11月17日下午5:00:43 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.breeze.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年11月17日
 * @since JDK 1.7
 * @Function bean实体工具类
 * @Reason
 */
public class BeanUtil {

	/**
	 * 给bean对象的某个属性赋值
	 * 
	 * @param bean
	 * @param paramName
	 * @param value
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void setProperty(Object bean, String paramName, String value)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 取得这个参数在Bean中的数据类型
		Class<?> cls = PropertyUtils.getPropertyType(bean, paramName.toString());
		// 把相应的数据转换成对应的数据类型
		Object beanValue = ConvertUtil.convert(value, cls);
		// 添充Bean值
		PropertyUtils.setProperty(bean, paramName.toString(), beanValue);
	}
}
