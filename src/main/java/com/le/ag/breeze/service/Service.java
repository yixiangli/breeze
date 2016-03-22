package com.le.ag.breeze.service;

import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.component.Component;
import com.le.ag.breeze.connector.Connector;
import com.le.ag.breeze.server.Server;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月9日
 * @use Service统一服务接口 用于连接connector与component
 */
public interface Service extends Lifecycle{

	public void setServer(Server server);
	
	public Server getServer();
		
	//添加组件
	public void addComponent(Component component);
	
	//添加引擎
	public void addConnector(Connector connector);
	
	//获取容器
	public Component[] findComponents();
	
	//获取引擎
	public Connector findConnector();
	
}
