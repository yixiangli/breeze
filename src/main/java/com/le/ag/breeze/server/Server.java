package com.le.ag.breeze.server;

import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.service.Service;

/**
 * 
 * @developer liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月14日
 * @since JDK 1.7
 * @Function 服务中心
 *             server center
 * @Reason
 */
public interface Server extends Lifecycle{

	/**
	 * 
	 * @developer liyixiang
	 * @date 2016年3月14日 上午11:49:08
	 * @descriptor
	 * @Function 添加service 对外所需提供的服务，一种抽象的概念
	 * @param service
	 */
	public void addService(Service service);
	
	/**
	 * 
	 * @use 获取service
	 * @param
	 * @return
	 */
	public Service findService();
	
	/**
	 * 
	 * @use 设置服务端口
	 * @param port 端口
	 * @return
	 */
	public void setPort(int port);

	/**
	 * 
	 * @use 获取服务端口
	 * @param
	 * @return
	 */
	public int getPort();
	
	/**
	 * 
	 * @use 设置服务地址
	 * @param address 地址
	 * @return
	 */
	public void setAddress(String address);
	
	/**
	 * 
	 * @use 获取服务地址
	 * @param
	 * @return
	 */
	public String getAddress();
}
