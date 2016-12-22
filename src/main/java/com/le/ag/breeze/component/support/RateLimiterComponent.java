/** 
 * @Project_Name breeze 
 * @File_Name LimitComponent.java 
 * @Date 2016年12月15日上午10:57:55 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.breeze.component.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Splitter;
import com.google.common.util.concurrent.RateLimiter;
import com.le.ag.breeze.Constants;
import com.le.ag.breeze.component.StandardComponent;
import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.util.StringUtils;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年12月15日
 * @since JDK 1.7
 * @Function 限流组件
 * @Reason
 */
public class RateLimiterComponent extends StandardComponent {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterComponent.class);

    //限流配置 配置格式 url1:number1; url2:number2;   例: /user/order:400;  意为/user/order这个请求最大支持每秒400并发，超出的部分会被限流拦截 
    private String limitConfig;
    //
    private static Map<String, RateLimiter> rateMap;
    
    /**
     * 初始化组件时将配置中需要限流的url与频次加载进map
     */
	@Override
	public void init() throws LifecycleException {
		// TODO Auto-generated method stub
		fireLifecycleEvent(BEFORE_INIT_EVENT, null);
		initInternal();
	}
	
	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		//初始化限流map
		initRateLimitMap(getLimitConfig());
	}

	/**
	 * 
	 * @author liyixiang
	 * @date 2016年12月16日 上午11:33:21
	 * @descriptor 限流流程
	 * @param uri
	 * @return
	 */
	public static boolean limit(String uri){
		//获取请求前缀
		String url = interceptParam(uri);
		//获取具体限流配置对象RateLimiter
        RateLimiter limiter = rateMap.get(url);
        //
        if (limiter == null || limiter.tryAcquire()) {
            return true;
        }
        return false;
	}
	
	/**
	 * 
	 * @use 截取uri的路径
	 * @param
	 * @return
	 */
	private static String interceptParam(String uri){
		//截取?之前的url
		return StringUtils.trim(StringUtils.substringBefore(uri, Constants.QUESTION_MARK));
	}
	
	/**
	 * 
	 * @author liyixiang
	 * @date 2016年12月15日 下午2:41:56
	 * @descriptor 初始化限流map
	 */
	private void initRateLimitMap(String config){
		rateMap = new ConcurrentHashMap<String, RateLimiter>();
		if(StringUtils.isNotBlank(config)){
	        Map<String, String> map = Splitter.on(";").omitEmptyStrings().trimResults().withKeyValueSeparator(":").split(config);
	        for (Map.Entry<String, String> entry : map.entrySet()) {
	        	long limit = 0 ;
	        	try {
		            limit = Long.parseLong(entry.getValue());
	        	}catch (Exception e){
	        		logger.error("rate limit config error",e);
	        		limit = 0;
	        	}
	            if (limit != 0) {
	            	rateMap.put(entry.getKey(), RateLimiter.create(limit));
	            	logger.info("limit key : "+entry.getKey() + "  limit number :"+limit);
	            }
	        }
	        logger.info("rateLimit config is: "+config);
		}
	}

	@Override
	protected void destoryInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		//将map清空
		rateMap = null;
	}

	public String getLimitConfig() {
		return limitConfig;
	}

	public void setLimitConfig(String limitConfig) {
		this.limitConfig = limitConfig;
	}
	
}
