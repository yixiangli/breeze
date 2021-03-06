package com.le.ag.breeze.component.support;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.Constants;
import com.le.ag.breeze.component.StandardComponent;
import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.util.UrlRewriteUtil;
import com.le.ag.breeze.util.XMLUtil;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月9日
 * @since JDK 1.7
 * @Function  url重写组件
 */
public class UrlRewriteComponent extends StandardComponent {

    private static final Logger logger = LoggerFactory.getLogger(UrlRewriteComponent.class);

	//重定向规则 保证文件的顺序性 使用LinkedHashMap
	private static Map<Pattern, String> urlRewriteRules = new LinkedHashMap<>();
	
	@Override
	public void init() throws LifecycleException{
		// TODO Auto-generated method stub
		//加载重定向文件
		try {
			urlRewriteRules.putAll(XMLUtil.parse(Constants.DEFAULT_URL_REWRITE_FILE));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("url rewriter parse error",e);
			throw new LifecycleException("url rewriter parse error",e.getCause());
		}	
	}

	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
	}

	@Override
	protected void startInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @use url映射
	 * @param
	 * @return
	 */
	public static String urlMapping(String url) throws Exception {
		return UrlRewriteUtil.urlMatcher(urlRewriteRules, url);
	}

	@Override
	protected void destoryInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		urlRewriteRules.clear();
	}

}
