package com.le.ag.breeze.server.facade;

import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.exception.ServerException;
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
 * 		  breeze server center facade, all application needs extends this class
 */
public class BreezeServerFacade{
	
	//服务器 
	private Server server;
	//服务
	private Service service;
	
	//服务启动
	public void startup(int port) {
		// TODO Auto-generated method stub
		//初始化服务
		try {
			initServer(port);
		}catch (Exception e){			
			//初始化异常抛出
			e.printStackTrace();
			server.destory();
		}
		//启动服务
		try{
			server.start();
		}catch (Exception e){
			e.printStackTrace();
			server.destory();
		}
		
	}
	
	private void initServer(int port) throws LifecycleException{
		//初始化Server
		getServer(port);
		server.init();
	}
	
	public void getServer(int port){
		//实例化server
		server = new ServerSupport();
		//设置启动端口
		server.setPort(port);
	}
	
	
}
