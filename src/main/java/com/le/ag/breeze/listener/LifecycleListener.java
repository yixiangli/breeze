package com.le.ag.breeze.listener;

import com.le.ag.breeze.event.LifecycleEvent;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月10日
 * @since JDK 1.7
 * @Function 组件生命周期监听器
 */
public interface LifecycleListener {

	/**
	 * 
	 * @use 
	 *      当主题变化时要执行的方法
	 * @param event 具体的事件
	 * @return
	 */
	void lifecycleEvent(LifecycleEvent event);
}
