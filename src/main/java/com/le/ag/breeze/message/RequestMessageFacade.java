package com.le.ag.breeze.message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.FullHttpRequest;

import java.net.InetSocketAddress;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.le.ag.breeze.util.StringUtils;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月7日
 * @use request请求门面
 */
@SuppressWarnings("deprecation")
public class RequestMessageFacade implements HttpRequestMessageContext{

	protected RequestMessage requestMessage;
	private ChannelHandlerContext context;

	public RequestMessageFacade(FullHttpRequest request,ChannelHandlerContext ctx) {
		// TODO Auto-generated constructor stub
		requestMessage = new RequestMessage(request);
		this.context = ctx;
	}
	
	@Override
	public String getParameter(String name){		
		return requestMessage.getParameter(name);
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return requestMessage.getHeader(name);
	}

	@Override
	public List<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return requestMessage.getHeaders(name);
	}

	@Override
	public Set<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return requestMessage.getHeaderNames();
	}

	@Override
	public List<Entry<String, String>> getHeaders() {
		// TODO Auto-generated method stub
		return requestMessage.getHeaders();
	}
	
	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return requestMessage.getRequestURI();
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return requestMessage.getMethod();
	}

	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return requestMessage.getParameterValues(name);
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return requestMessage.getProtocol();
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取远程调用地址
	 * 先取X-Real_Ip 如果不存在 取X-Forward-For
	 */
	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		//先获取real-ip
		String remoteAddr = requestMessage.getRealIp();
		//获取x-forward-for
		if(StringUtils.isBlank(remoteAddr)){
			remoteAddr = requestMessage.getXForwardFor();
		}
		//获取hostAddress
		if(StringUtils.isBlank(remoteAddr)){
			remoteAddr = ((InetSocketAddress) context.channel().remoteAddress()).getAddress().getHostAddress();
		}
		return remoteAddr;
	}

	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getRewriteUrl() {
		// TODO Auto-generated method stub
		return requestMessage.getRewriteUrl();
	}
	
	@Override
	public void setRewriteUrl(String rewriteUrl){
		requestMessage.setRewriteUrl(rewriteUrl);
	}

	/**
	 * 
	 * @use
	 * @param
	 * @return
	 */
	public void parseParam(){
		requestMessage.extractRequest(this.getMethod());
	}
	
	
}
