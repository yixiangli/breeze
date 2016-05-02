package com.le.ag.breeze.connector.processor;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

import com.le.ag.breeze.Constants;
import com.le.ag.breeze.component.support.ServiceLocatorComponent;
import com.le.ag.breeze.component.support.UrlRewriteComponent;
import com.le.ag.breeze.exception.ServerException;
import com.le.ag.breeze.message.HttpRequestMessageContext;
import com.le.ag.breeze.message.RequestMessageFacade;
import com.le.ag.breeze.util.ReflectUtil;
import com.le.ag.breeze.util.StringUtils;


/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月5日
 * @use http处理模版实现类
 */
public class HttpProcessorTemplateSupport extends HttpProcessorTemplate{

	@Override
	protected RequestMessageFacade encapsulate(FullHttpRequest request,ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		return new RequestMessageFacade(request,ctx);	
	}

	@Override
	protected String interceptRequest(RequestMessageFacade request) throws Exception {
		// TODO Auto-generated method stub
		//请求url拦截 
		String uri = request.getRequestURI();
		
		//拦截favicon.ico请求
		if(uri.endsWith(Constants.FAVICON_ICO)){
			throw new ServerException(HttpResponseStatus.NOT_FOUND.reasonPhrase());
		}
		//urlrewriter匹配
		String reUri =  UrlRewriteComponent.urlMapping(uri);
		if(StringUtils.isEmpty(reUri)){
			throw new ServerException(HttpResponseStatus.NOT_FOUND.reasonPhrase());
		}
		//重定向url注入
		request.setRewriteUrl(reUri);
		//请求method过滤 目前只是支持get post请求
		String method = request.getMethod();
		if(method != HttpMethod.POST.name() && method != HttpMethod.GET.name()){	
			throw new ServerException(HttpResponseStatus.METHOD_NOT_ALLOWED.reasonPhrase());
		}
		return reUri;
	}

	@Override
	protected Object serviceLocate(RequestMessageFacade request) throws Exception {
		// TODO Auto-generated method stub
		//服务注入
		Dispatcher.doService(request);
		//服务获取
		return ServiceLocatorComponent.getServiceLocator().getService(request.getParameter(Constants.SERVICE_PARAM));
	}

	@Override
	protected Object execute(Object invokeService,RequestMessageFacade requestMsgCtx) throws Exception {
		// TODO Auto-generated method stub
		// 根据service method invoke服务 参数为MessageContext
		Method _invokeMethod = ReflectUtil.getMethod(invokeService.getClass(), requestMsgCtx.getParameter(Constants.METHOD_PARAM),new Class[] { HttpRequestMessageContext.class });
		Object invokeResult = _invokeMethod.invoke(invokeService, new Object[] { requestMsgCtx });
		return invokeResult;
	}

	@Override
	protected void sendResponse(ChannelHandlerContext ctx,Object result) {
		// TODO Auto-generated method stub
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		HttpHeaders.setHeader(response, HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8");
		// 将bytebuf的引用计数器＋1
		// 否则会报会报io.netty.util.IllegalReferenceCountException: refCnt:
		// 0, decrement: 1
		ReferenceCountUtil.retain(response);
		
		if (result instanceof String) {
			response.content().writeBytes(Unpooled.copiedBuffer((String) result, CharsetUtil.UTF_8));
		} else if (result instanceof InputStream) {
			//response.content().writeBytes(Unpooled.copiedBuffer(toByteArray((InputStream) invokeResult)));
		} else if (result instanceof byte[]) {
			response.content().writeBytes(Unpooled.copiedBuffer((byte[]) result));
		} else if (result instanceof Map) {
			// 302 307 or other status
			Map<String, Object> resMap = (Map) result;
			if (resMap != null) {
				Object statusObj = resMap.get("status");
				if (statusObj != null && statusObj instanceof HttpResponseStatus) {
					response.setStatus((HttpResponseStatus) statusObj);
				}
				Object locationObj = resMap.get("location");
				if (locationObj != null && locationObj instanceof String) {
					response.headers().set(HttpHeaders.Names.LOCATION, locationObj.toString());
				}
			}
		}
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

}
