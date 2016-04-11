package com.le.ag.breeze.message;

import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月7日
 * @use request message packaged
 */
public class RequestMessage {

	//netty 提供的request
	private FullHttpRequest request;
	
	public RequestMessage(FullHttpRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
	}
	
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return request.headers().get(name);
	}

	public List<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return request.headers().getAll(name);
	}

	public Set<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return request.headers().names();
	}

	public List<Map.Entry<String, String>> getHeaders() {
		// TODO Auto-generated method stub
		return request.headers().entries();
	}
	
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return request.getUri();
	}

	public String getMethod() {
		// TODO Auto-generated method stub
		return request.getMethod().name();
	}

	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProtocol() {
		// TODO Auto-generated method stub
		return request.getProtocolVersion().text();
	}

	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
