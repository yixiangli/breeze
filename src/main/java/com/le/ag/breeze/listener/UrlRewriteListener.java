package com.le.ag.breeze.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.component.Component;
import com.le.ag.breeze.component.support.UrlRewriteComponent;
import com.le.ag.breeze.event.LifecycleEvent;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年9月1日
 * @since JDK 1.7
 * @Function url重写监听器
 */
public final class UrlRewriteListener implements LifecycleListener {

    private static final Logger logger = LoggerFactory.getLogger(UrlRewriteListener.class);
	
	protected Component component;
	
	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		// TODO Auto-generated method stub
		try{
			component = (UrlRewriteComponent)event.getLifecycle();
		}catch (ClassCastException e){
			logger.error("url rewriter component cast error",e);
		}
		
		/**   开发示例demo
		//初始化之前
		if(event.getType().equals(Lifecycle.BEFORE_INIT_EVENT)){

		//初始化
		}else if(event.getType().equals(Lifecycle.INIT_EVENT)){
			//component.init();
		//初始化之后
		}else if(event.getType().equals(Lifecycle.AFTER_INIT_EVENT)) {
			
		}
		**/
	}

}
