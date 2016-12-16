package com.le.ag.breeze;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月11日
 * @since JDK 1.7
 * @Function 加载器基础接口
 */
public interface Loader {

	/**
	 * 
	 * @author liyixiang
	 * @date 2016年9月1日 上午11:20:24
	 * @descriptor 获取类加载器
	 * @return
	 */
	public ClassLoader getClassLoader();
	
	
}
