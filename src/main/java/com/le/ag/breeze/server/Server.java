package com.le.ag.breeze.server;

import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.service.Service;


/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月14日
 * @use 服务器 服务中心 特指breeze 
 */
public interface Server extends Lifecycle{

	/**
	 * 
	 * @use 添加service 对外所需提供的服务，一种抽象的概念
	 * @param service: 一种具体的服务
	 * @return
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
