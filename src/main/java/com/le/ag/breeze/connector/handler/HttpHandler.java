package com.le.ag.breeze.connector.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import com.le.ag.breeze.connector.processor.HttpProcessorTemplateSupport;
import com.le.ag.breeze.connector.processor.ProcessorTemplate;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月21日
 * @since JDK 1.7
 * @Function 请求处理
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
		if(cause.getMessage().equals(HttpResponseStatus.NOT_FOUND.reasonPhrase())){
			sendErrorResponse(ctx,HttpResponseStatus.NOT_FOUND);
		}else if(cause.getMessage().equals(HttpResponseStatus.METHOD_NOT_ALLOWED.reasonPhrase())){
			sendErrorResponse(ctx,HttpResponseStatus.METHOD_NOT_ALLOWED);
		}else if(cause.getMessage().equals(HttpResponseStatus.INTERNAL_SERVER_ERROR.reasonPhrase())){
			super.exceptionCaught(ctx, cause);
			sendErrorResponse(ctx,HttpResponseStatus.INTERNAL_SERVER_ERROR);
		}else {
			//未知exception
			super.exceptionCaught(ctx, cause);
		}
	}
	
	/**
	 * 
	 * @use 异常处理返回
	 * @param
	 * @return
	 */
	private void sendErrorResponse(ChannelHandlerContext ctx,HttpResponseStatus status){
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
		HttpHeaders.setHeader(response, HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8");
		// 将bytebuf的引用计数器＋1
		// 否则会报会报io.netty.util.IllegalReferenceCountException: refCnt:
		// 0, decrement: 1
		ReferenceCountUtil.retain(response);
		response.content().writeBytes(Unpooled.copiedBuffer(status.reasonPhrase(), CharsetUtil.UTF_8));
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
	

}
