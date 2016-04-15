package com.le.ag.breeze.connector.processor;

import io.netty.handler.codec.http.HttpMethod;

import com.le.ag.breeze.message.RequestMessageFacade;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月13日
 * @use 分发器
 */
public class Dispatcher {

	/**
	 * 
	 * @use 服务分发
	 * @param
	 * @return
	 */
	public static void doService(RequestMessageFacade requestMsg){
		//参数解析
		requestMsg.parseParam();
	}
	
}
