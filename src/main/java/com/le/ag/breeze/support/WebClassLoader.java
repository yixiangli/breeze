package com.le.ag.breeze.support;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.exception.ServerException;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月12日
 * @since JDK 1.7
 * @Function 封装jdk classloader工具
 */
public class WebClassLoader extends ClassLoader{

	//(1):出于资源利用的考虑，LOGGER的构造方法参数是Class，决定了LOGGER是根据
	//类的结构来进行区分日志，所以一个类只要一个LOGGER就可以了，故static
	//(2):final表示一种编程习惯，表示该类的LOGGER只是记录该类的信息，否则日志
	//会无法提供可以令人信服的记录
    private static final Logger logger = LoggerFactory.getLogger(WebClassLoader.class);
	
    //无实例类
	private WebClassLoader() {
		super();
	}

	/**
	 * 根据className加载class
	 */
	protected static Class<?> loadClassByName(String className){
		try {
            return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException ex) {
        	logger.error("load class exception",ex);
            throw new ServerException("load class error,please check your className",ex.getCause());
        }	
	}
	
	
	/**
     * 创建指定类的实例
     *
     * @param clazzName 类名
     * @return
     */
    public static Object getInstance(String clazzName) {
        try {
            return loadClassByName(clazzName).newInstance();
        } catch (Exception ex) {
        	logger.error("类实例化异常",ex);
            throw new ServerException("类实例化异常",ex.getCause());
        } 
    }
	
	
	/**
	 * 
	 * @use 根据resouce获取输入流
	 * @param
	 * @return
	 */
	public static InputStream getStream(String resource) {
        return getClassLoader().getResourceAsStream(resource);
    }
	
	
	/**
	 * 
	 * @use 获取当前线程的classloader
	 * @param
	 * @return
	 */
	public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
	
	/**
	 * 
	 * @use
	 * @param
	 * @return
	 */
	public static Properties getProperties(String resource) {	
		 Properties properties = new Properties();
	        try {
	            InputStream is = getStream(resource);
	            if (is == null) {
	            	logger.error("properties file is not found");
	                throw new ServerException("properties file is not found");
	            }  
	            properties.load(is);
	            return properties;
	        } catch (Exception ex) {
	        	logger.error("load properties error",ex);
                throw new ServerException("load properties error",ex.getCause());
	        }
	}
	
}
