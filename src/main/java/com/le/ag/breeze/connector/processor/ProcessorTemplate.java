package com.le.ag.breeze.connector.processor;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年9月1日
 * @since JDK 1.7
 * @Function 处理模版
 */
public interface ProcessorTemplate {

	/**
	 * 
	 * @use netty-http处理规则模版
	 * @param
	 * @return
	 */
	public void process(ChannelHandlerContext ctx,FullHttpRequest request) throws Exception;
		
}
