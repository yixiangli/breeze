package com.le.ag.breeze.message;

import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.CharsetUtil;

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
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年4月7日
 * @since JDK 1.7
 * @Function request message packaged
 */
@SuppressWarnings("deprecation")
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

	public Set<String> getParameterNames() {
		// TODO Auto-generated method stub
		return parameterMap.keySet();
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

	/**
	 * 
	 * @use 获取X-Forward-For
	 * @param
	 * @return
	 */
	public String getXForwardFor(){
		return HttpHeaders.getHeader(request, Constants.HTTP_HEADER_X_FORWARD_FOR);
	}
	
	/**
	 * 
	 * @use 获取X-Real-Ip
	 * @param
	 * @return
	 */
	public String getRealIp() {
		// TODO Auto-generated method stub
		return HttpHeaders.getHeader(request, Constants.HTTP_HEADER_X_REAL_IP);
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
		//参数截取 形如a=5&b=7
		String requestParam = interceptParam(getRequestURI());
		//修复post请求参数问题
		if(StringUtils.isBlank(requestParam)){
			return rewriteUrl;
		}
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
		//截取?之后的url
		return StringUtils.trim(StringUtils.substringAfter(uri, Constants.QUESTION_MARK));
	}
	

	/**
	 * 
	 * @use 请求参数提取
	 * @param
	 * @return
	 */
	public void extractRequest(String method){
		
		Map<String, List<String>> parameterList = new HashMap<String, List<String>>();
		
		//存放service/method param
		QueryStringDecoder parameterDecoder = new QueryStringDecoder(spliceParam(this.rewriteUrl));	
		parameterList.putAll(parameterDecoder.parameters());
		//post请求特殊处理
		if(HttpMethod.POST.name().equals(method)){
			 String contentType = HttpHeaders.getHeader(request, HttpHeaders.Names.CONTENT_TYPE);
	         if (HttpHeaders.Values.APPLICATION_X_WWW_FORM_URLENCODED.equalsIgnoreCase(contentType) && (HttpHeaders.getContentLength(request) > 0 || request.content().readableBytes() > 0)) {
	                // FIXME 修复解析post参数的bug,必须将第二个参数hashPath设置为false才可以
	            	QueryStringDecoder postParameterDecoder = new QueryStringDecoder(request.content().toString(CharsetUtil.UTF_8), false);
	            	parameterList.putAll(postParameterDecoder.parameters());
	         }
		}

		for (Entry<String, List<String>> entry : parameterList.entrySet()) {
			parameterMap.put(entry.getKey(), entry.getValue().toArray(new String[]{}));
        }
	}
	
}
