package com.le.ag.breeze.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.component.Component;
import com.le.ag.breeze.component.support.UrlRewriteComponent;
import com.le.ag.breeze.event.LifecycleEvent;

public final class UrlRewriteListener implements LifecycleListener {

    private static final Logger logger = LoggerFactory.getLogger(UrlRewriteListener.class);
	
	protected Component component;
	
	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		// TODO Auto-generated method stub
		try{
			component = (UrlRewriteComponent)event.getLifecycle();
		}catch (ClassCastException e){
			logger.error("",e);
		}
		
		//初始化之前
		if(event.getType().equals(Lifecycle.BEFORE_INIT_EVENT)){

		//初始化
		}else if(event.getType().equals(Lifecycle.INIT_EVENT)){
			//component.init();
		//初始化之后
		}else if(event.getType().equals(Lifecycle.AFTER_INIT_EVENT)) {
			
		}
	}

}
