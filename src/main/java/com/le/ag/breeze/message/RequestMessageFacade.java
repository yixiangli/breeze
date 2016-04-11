package com.le.ag.breeze.message;

import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月7日
 * @use request请求门面
 */
public class RequestMessageFacade implements HttpRequestMessageContext{

	protected RequestMessage requestMessage;

	public RequestMessageFacade(FullHttpRequest request) {
		// TODO Auto-generated constructor stub
		requestMessage = new RequestMessage(request);
	}
	
	@Override
	public String getParameter(String name){		
		return requestMessage.getParameter(name);
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entry<String, String>> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return requestMessage.getRequestURI();
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
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
}
