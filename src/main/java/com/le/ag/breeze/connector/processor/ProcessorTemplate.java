package com.le.ag.breeze.connector.processor;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月7日
 * @use 处理模版
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
