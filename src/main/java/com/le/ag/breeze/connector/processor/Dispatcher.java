package com.le.ag.breeze.connector.processor;

import com.le.ag.breeze.message.RequestMessageFacade;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月13日
 * @use 分发器
 */
public class Dispatcher {

	public static void doService(RequestMessageFacade requestMsg){
		requestMsg.parseParam();
	}
	
}
