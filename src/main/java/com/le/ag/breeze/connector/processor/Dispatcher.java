package com.le.ag.breeze.connector.processor;

import com.le.ag.breeze.message.RequestMessageFacade;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年9月1日
 * @since JDK 1.7
 * @Function 分发器
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
