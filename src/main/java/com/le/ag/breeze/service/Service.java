package com.le.ag.breeze.service;

import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.component.Component;
import com.le.ag.breeze.connector.Connector;
import com.le.ag.breeze.listener.LifecycleListener;
import com.le.ag.breeze.server.Server;

/**
 * 
 * @developer liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月9日
 * @since JDK 1.7
 * @Function Service统一服务接口 用于连接connector与component
 * 			   Service interfacr used by bridging connector and component
 * @Reason 
 */
public interface Service extends Lifecycle{

	public void setServer(Server server);
	
	public Server getServer();
		
	//添加组件
	public void addComponent(Component component,LifecycleListener listener);
	
	//添加引擎
	public void addConnector(Connector connector,LifecycleListener listener);
	
	//获取容器
	public Component[] findComponents();
	
	//获取引擎
	public Connector findConnector();
	
}
