package com.le.ag.breeze.connector.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月21日
 * @use 分发
 */
public class DispatcherHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
