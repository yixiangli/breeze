package com.le.ag.breeze.connector;

import com.le.ag.breeze.Lifecycle;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月8日
 * @since JDK 1.7
 * @Function  接入层基础接口
 */
public interface Connector extends Lifecycle{

	public void setPort(int port);
	
	public int getPort();
}
