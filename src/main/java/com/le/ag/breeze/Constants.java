package com.le.ag.breeze;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月12日
 * @since JDK 1.7
 * @Function 常量
 */
public final class Constants {

	//------------  xml相关常量 -------------
	public static final String DTD_RULE_FILE_PATH = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
	
	public static final String INCLUDE = "//include";
	public static final String RULE = "//rule";
	public static final String FROM = "from/text()";
	public static final String TO = "to/text()";
	public static final String INCLUDE_SOURCE = "resource";
	
	//用户配置的xml
	public static final String DEFAULT_CONFIG_FILE = "config.xml";
	public static final String DEFAULT_URL_REWRITE_FILE = "urlrewrite.xml";

	public static final String PROPERTIES = "properties";
	
	public static final String PREFIX_FILE = "file:";
	public static final String PREFIX_CLASSPATH = "classpath:";

	//serviceLocator
	public static final String SERVICE_LOCATOR_SUPPORT = "ServiceLocatorSupport";
	
	public static final String SERVICE_PARAM = "service";
	public static final String METHOD_PARAM = "method";
	
	//Symbol
	public static final String QUESTION_MARK = "?";
	public static final String AND = "&";
	public static final String FAVICON_ICO = "/favicon.ico";
	
	//Executor
	public static final String BOSS_GROUP_NUM = "BossGroupNumber";
	public static final String WORK_GROUP_NUM = "WorkGroupNumber";
	public static final String EVENT_EXECUTOR_GROUP_NUM = "EventExecutorGroupNumber";

	//HTTP
	public static final String HTTP_HEADER_X_FORWARD_FOR = "X-Forwarded-For";
	public static final String HTTP_HEADER_X_REAL_IP = "X-Real-IP";
	
	//Limit
	public static final String RATE_LIMITER_CONFIG = "Rate_limiter_config";

} 
