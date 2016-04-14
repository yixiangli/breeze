package com.le.ag.breeze.message;

import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.le.ag.breeze.Constants;
import com.le.ag.breeze.util.StringUtils;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月7日
 * @use request message packaged
 */
public class RequestMessage {

	//netty 提供的request
	private FullHttpRequest request;
	private String rewriteUrl;
	
	private Map<String, String[]> parameterMap;
	
	public RequestMessage(FullHttpRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
		parameterMap = new HashMap<String, String[]>();
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

	/**
	 * 
	 * @use 获取请求参数
	 * @param
	 * @return
	 */
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String[] values = parameterMap.get(name);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
	}

	public Enumeration getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return parameterMap.get(name);
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
	
	public void setRewriteUrl(String rewriteUrl){
		this.rewriteUrl = rewriteUrl;
	}
	
	public String getRewriteUrl(){
		return this.rewriteUrl;
	}
	
	
	/**
	 * 
	 * @use 参数拼接
	 * @param
	 * @return
	 */
	private String spliceParam(String rewriteUrl){
		String requestParam = interceptParam(getRequestURI());
		String param = StringUtils.concat(rewriteUrl,Constants.AND,requestParam);
		return param;
	}
	
	/**
	 * 
	 * @use 截取uri的参数
	 * @param
	 * @return
	 */
	private String interceptParam(String uri){
		return StringUtils.trim(StringUtils.substringAfter(uri, Constants.QUESTION_MARK));
	}
	

	/**
	 * 
	 * @use 请求参数提取
	 * @param
	 * @return
	 */
	public void extractRequest(){
		
		QueryStringDecoder parameterDecoder = new QueryStringDecoder(spliceParam(this.rewriteUrl));
		Map<String, List<String>> parameterList = new HashMap<String, List<String>>();
		parameterList.putAll(parameterDecoder.parameters());
		
		for (Entry<String, List<String>> entry : parameterList.entrySet()) {
			parameterMap.put(entry.getKey(), entry.getValue().toArray(new String[]{}));
        }
	}
	
}
