package com.le.ag.breeze.connector.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

import com.le.ag.breeze.connector.processor.HttpProcessorTemplateSupport;
import com.le.ag.breeze.connector.processor.ProcessorTemplate;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月21日
 * @use 请求处理
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

	private static final ProcessorTemplate template = new HttpProcessorTemplateSupport();
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		// TODO Auto-generated method stub
		messageReceived(ctx,request);
	}
	
	
	
	/**
	 * 
	 * @use 方便今后升级netty5
	 * @param
	 * @return
	 */
	private void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		template.process(ctx, request);
	}
	
	
	/**
	 * netty统一异常处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
	

}
