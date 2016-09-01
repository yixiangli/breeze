package com.le.ag.breeze.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.component.support.ConfigurationComponent;
import com.le.ag.breeze.component.support.ServiceLocatorComponent;
import com.le.ag.breeze.component.support.UrlRewriteComponent;
import com.le.ag.breeze.connector.support.NettyConnectorSupport;
import com.le.ag.breeze.event.LifecycleEvent;
import com.le.ag.breeze.exception.ServerException;
import com.le.ag.breeze.service.Service;
import com.le.ag.breeze.support.ServiceSupport;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年9月1日
 * @since JDK 1.7
 * @Function service监听器
 */
public class ServiceListener implements LifecycleListener {

    private static final Logger logger = LoggerFactory.getLogger(ServiceListener.class);
	
	protected Service service = null;
	
	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		// TODO Auto-generated method stub
		try{
			service = (ServiceSupport)event.getLifecycle();
		}catch (ClassCastException e){
			logger.error("class cast exception",e);
			throw new ServerException("class cast exception");
		}
		
		//初始化之前
		if(event.getType().equals(Lifecycle.BEFORE_INIT_EVENT)){
			//加载组件
			service.addComponent(new UrlRewriteComponent());
			service.addComponent(new ConfigurationComponent());
			service.addComponent(new ServiceLocatorComponent());
			//加载引擎
			service.addConnector(new NettyConnectorSupport(),new NettyConnectorListener());
			
		}
	}

}
