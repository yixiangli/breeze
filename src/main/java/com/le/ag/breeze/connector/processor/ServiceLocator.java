package com.le.ag.breeze.connector.processor;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年4月11日
 * @since JDK 1.7
 * @Function service定位
 */
public interface ServiceLocator {

	public Object getService(String serviceName);
}
