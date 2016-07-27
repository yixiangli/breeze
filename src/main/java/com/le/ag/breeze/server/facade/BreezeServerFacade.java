package com.le.ag.breeze.server.facade;

import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.server.Server;
import com.le.ag.breeze.support.ServerSupport;

/**
 * 
 * @developer liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月17日
 * @since JDK 1.7
 * @Function breeze服务外观 所有application的启动入口类都需要继承这个类
 * 			   breeze server center facade, all application needs extends this class used by start the server.
 * @Reason
 */
public class BreezeServerFacade{
	
	//服务器 
	private Server server;

	//服务启动
	public void startup(int port) {
		// TODO Auto-generated method stub
		//初始化服务
		try {
			initServer(port);
		} catch (Exception e){			
			//初始化异常抛出
			e.printStackTrace();
			server.destory();
		}
		//启动服务
		try{
			server.start();
		} catch (Exception e){
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
