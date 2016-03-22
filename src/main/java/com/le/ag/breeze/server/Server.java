package com.le.ag.breeze.server;

import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.service.Service;


/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月14日
 * @use 服务上下文环境 对外提供的顶层服务 
 */
public interface Server extends Lifecycle{

	public void addService(Service service);
	
	public Service findService();
	
	public void setPort(int port);

	public int getPort();
	
	public void setAddress(String address);
	
	public String getAddress();
}
