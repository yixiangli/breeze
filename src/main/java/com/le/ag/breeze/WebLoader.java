package com.le.ag.breeze;

import java.io.InputStream;
import java.util.Properties;

import com.le.ag.breeze.support.WebClassLoader;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月12日
 * @use 加载类等基础信息的门面
 */
public class WebLoader implements Loader {
	
	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return WebClassLoader.getClassLoader();
	}

	/**
	 * 
	 * @use 根据resource返回InputStream
	 * @param
	 * @return
	 */
	public static InputStream getStreamByResourceName(String resource){
		return WebClassLoader.getStream(resource);
	}
	
	/**
	 * 
	 * @use 根据resources返回Properties
	 * @param
	 * @return
	 */
	public static Properties getProperties(String resource) {	
		return WebClassLoader.getProperties(resource);
	}
	
	
	/**
     * 创建指定类的实例
     *
     * @param clazzName 类名
     * @return
     */
    public static Object getInstance(String clazzName) {
    	return WebClassLoader.getInstance(clazzName);
    }
}
