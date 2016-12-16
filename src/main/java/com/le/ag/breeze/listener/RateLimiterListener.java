/** 
 * @Project_Name breeze 
 * @File_Name RateLimiterListener.java 
 * @Date 2016年12月15日下午1:58:41 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.breeze.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.Constants;
import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.component.Component;
import com.le.ag.breeze.component.support.ConfigurationComponent;
import com.le.ag.breeze.component.support.RateLimiterComponent;
import com.le.ag.breeze.event.LifecycleEvent;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年12月15日
 * @since JDK 1.7
 * @Function
 * @Reason
 */
public class RateLimiterListener implements LifecycleListener {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterListener.class);
	
	protected Component component;
	
	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		// TODO Auto-generated method stub
		try{
			component = (RateLimiterComponent)event.getLifecycle();
		}catch (ClassCastException e){
			logger.error("limit component cast error",e);
		}
		
		//初始化之前
		if(event.getType().equals(Lifecycle.BEFORE_INIT_EVENT)){
			String limitConfig = ConfigurationComponent.getString(Constants.RATE_LIMITER_CONFIG);
			
			if(component instanceof RateLimiterComponent){
				((RateLimiterComponent)component).setLimitConfig(limitConfig);
			}
		}
	}

}
