package com.le.ag.breeze.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.exception.ServerException;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月8日
 * @use url重定向工具类
 */
public class UrlRewriteUtil {

    private static final Logger logger = LoggerFactory.getLogger(UrlRewriteUtil.class);
	
    private static final Pattern tokenPattern = Pattern.compile("(\\$\\d+)", Pattern.CASE_INSENSITIVE);
    
    /**
     * 
     * @use url匹配
     * 		遍历Rule Map<Pattern, String> 匹配传入的url是否是当前服务提供的规则
     * @param
     * @return
     */
	public static String urlMatcher(Map<Pattern, String> urlRewriteRules,String url) {
		//规则
        Pattern pattern = null;
        //匹配器
        Matcher matcher = null;
        //重定向后的url
        String targetUrl = null;
        
        for (Entry<Pattern, String> rewriteRule : urlRewriteRules.entrySet()) {
            pattern = rewriteRule.getKey();
            // FIXME 增加异常处理
            try {
                matcher = pattern.matcher(url);
                //匹配
                if (matcher.find()) {
                	//重定向规则匹配
                    targetUrl = rewriteTargetUrl(rewriteRule.getValue(), matcher);
                    return targetUrl;
                }
            } catch (Throwable ex) {
                logger.error("url matcher fail!", ex);
                throw new ServerException("url matcher exception",ex);
            }
        }
        //url为空或者未匹配上
        return StringUtils.EMPTY;
    }

	/**
	 * 
	 * @use 重定向url 映射
	 * 		  根据urlrewrite.xml文件中的配置，将<from>与<to>一一映射
	 * @param
	 * @return
	 */
    private static String rewriteTargetUrl(String ruleValue, Matcher matcher) {
    	//匹配器校验
        Matcher toMatcher = tokenPattern.matcher(ruleValue);
        
        int groupIdx = 1;
        StringBuffer sb = new StringBuffer();
        while (toMatcher.find()) {
            String token = toMatcher.group();
            groupIdx = StringUtils.toInt(token.substring(token.indexOf("$") + 1));
            toMatcher.appendReplacement(sb, matcher.group(groupIdx));
        }
        toMatcher.appendTail(sb);
        toMatcher = null;
        return sb.toString();
    }
}
