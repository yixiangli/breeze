package com.le.ag.breeze.server.facade;

import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.listener.ServiceListener;
import com.le.ag.breeze.server.Server;
import com.le.ag.breeze.service.Service;
import com.le.ag.breeze.support.ServerSupport;
import com.le.ag.breeze.support.ServiceSupport;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月17日
 * @use breeze服务门面 所有application都需要继承这个类
 */
public class BreezeServerFacade{
	
	private Server server;
	private Service service;
	
	//启动服务
	public void startup(int port) {
		// TODO Auto-generated method stub
		//初始化服务
		try {
			initServer(port);
		}catch (Exception e){
			 
		}
		//启动服务
		try{
			server.start();
		}catch (Exception e){
			
		}
		
	}
	
	private void initServer(int port) throws LifecycleException{
		getServer(port);
		server.init();
	}
	
	public void getServer(int port){
		//实例化server
		server = new ServerSupport();
		//设置启动端口
		server.setPort(port);
		//实例化service
		service = new ServiceSupport();
		//注册事件
		service.addLifecycleListener(new ServiceListener());
		//service纳入server管理
		server.addService(service);	
	}
	
	
}
