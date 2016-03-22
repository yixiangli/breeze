package com.le.ag.breeze.support;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.exception.ServerException;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月12日
 * @use 封装jdk classloader工具
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
	public static Class<?> loadClassByName(String className){
		try {
            return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException ex) {
        	logger.error("类加载异常",ex);
        	Throwable cause = ex.getCause();
            throw new ServerException("类加载异常",cause);
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
	
}
