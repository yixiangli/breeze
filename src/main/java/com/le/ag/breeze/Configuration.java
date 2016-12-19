package com.le.ag.breeze;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年4月10日
 * @since JDK 1.7
 * @Function 配置文件获取接口
 */
public interface Configuration {

	/**
	 * 
	 * @use 根据key 获取String类型的value 
	 * @param
	 * @return
	 */
	public String getString(String key);
	
	/**
	 * 
	 * @use 根据key 获取int类型的value 
	 * @param
	 * @return
	 */
	public Integer getInt(String key);
		
	/**
	 * 
	 * @use 判断该key是否存在文件中
	 * @param
	 * @return
	 */
	public boolean containsKey(String key);
}
