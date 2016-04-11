package com.le.ag.breeze.connector.processor;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月11日
 * @use service定位
 */
public interface ServiceLocator {

	public Object getService(String serviceName);
}
