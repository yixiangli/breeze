package com.le.ag.breeze;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月10日
 * @use 配置基类
 */
public interface Configuration {

	/**
	 * 
	 * @use 根据key 获取value
	 * @param
	 * @return
	 */
	public String getString(String key);
	
	/**
	 * 
	 * @use 判断该key是否存在文件中
	 * @param
	 * @return
	 */
	public boolean containsKey(String key);
}
