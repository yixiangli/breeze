package com.le.ag.breeze.support;

import com.le.ag.breeze.LifecycleMBeanBase;
import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.listener.ServiceListener;
import com.le.ag.breeze.server.Server;
import com.le.ag.breeze.service.Service;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月18日
 * @since JDK 1.7
 * @Function 服务中心实例
 */
public class ServerSupport extends LifecycleMBeanBase implements Server {

	protected Service service = null;

	private int port = 9040;
	private String address = "localhost";
	
	public ServerSupport(){
		
	}
	
	@Override
	public void init() throws LifecycleException{
		// TODO Auto-generated method stub
		//实例化service
		service = new ServiceSupport();
		//注册事件
		service.addLifecycleListener(new ServiceListener());
		//service纳入server管理
		this.addService(service);
		//初始化service
		service.init();
	}

	@Override
	public void start() throws LifecycleException{
		// TODO Auto-generated method stub
		service.start();
	}
	
	@Override
	public void addService(Service service) {
		// TODO Auto-generated method stub
		//建立service与server的依赖关系 
		service.setServer(this);
		this.service = service;
	}

	@Override
	public Service findService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public void setPort(int port) {
		// TODO Auto-generated method stub
		this.port = port;
	}

	@Override
	public int getPort() {
		// TODO Auto-generated method stub
		return this.port;
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		this.address = address;
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return this.address;
	}

	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void startInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void destoryInternal() {
		// TODO Auto-generated method stub
		service.destory();
	}
	
	@Override
	public void destory()  {
		// TODO Auto-generated method stub
		destoryInternal();
	}
	
}
