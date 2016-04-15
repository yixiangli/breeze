package com.le.ag.breeze.support;

import com.le.ag.breeze.LifecycleMBeanBase;
import com.le.ag.breeze.component.Component;
import com.le.ag.breeze.connector.Connector;
import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.listener.LifecycleListener;
import com.le.ag.breeze.server.Server;
import com.le.ag.breeze.service.Service;

public class ServiceSupport extends LifecycleMBeanBase implements Service {

	//服务
	protected Server server;
	
	//组件
	protected Component[] components = new Component[0];

	//引擎
	protected Connector connector = null;

	@Override
	public void addComponent(Component component) {
		// TODO Auto-generated method stub
		Component results[] = new Component[components.length+1];
		System.arraycopy(components, 0, results, 0, components.length);
        results[components.length] = component;
        components = results;
	}

	@Override
	public void addConnector(Connector connector,LifecycleListener listener) {
		// TODO Auto-generated method stub
		this.connector = connector;
		connector.addLifecycleListener(listener);
		connector.setPort(server.getPort());	
	}

	@Override
	public Component[] findComponents() {
		// TODO Auto-generated method stub
		return components;
	}

	@Override
	public Connector findConnector() {
		// TODO Auto-generated method stub
		return connector;
	}
	
	@Override
	public void init() throws LifecycleException{
		// TODO Auto-generated method stub
		//初始化前
		fireLifecycleEvent(BEFORE_INIT_EVENT,null);
		//初始化阶段事件 
		initInternal();
	}
	
	@Override
	public void start() throws LifecycleException {
		// TODO Auto-generated method stub
		//启动
		startInternal();
	}

	@Override
	public void setServer(Server server) {
		// TODO Auto-generated method stub
		this.server = server;
	}

	@Override
	public Server getServer() {
		// TODO Auto-generated method stub
		return this.server;
	}

	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		//组件初始化
		for(Component component : components){
			component.init();
		}		
		//引擎初始化
		connector.init();
	}

	@Override
	protected void startInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		connector.start();
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		destoryInternal();
	}
	
	
	@Override
	protected void destoryInternal() {
		// TODO Auto-generated method stub
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {    
                for (Component component : components) {
                    component.destory();
                }                
                connector.destory();
                //系统退出
                //System.exit(-1);
            }
        })); 
		
	}
}
