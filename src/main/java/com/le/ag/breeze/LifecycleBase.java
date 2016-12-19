package com.le.ag.breeze;

import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.listener.LifecycleListener;
import com.le.ag.breeze.support.LifecycleSupport;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月10日
 * @since JDK 1.7
 * @Function 生命周期基础类
 */
public abstract class LifecycleBase implements Lifecycle {

	//组合形式
	private LifecycleSupport lifecycle = new LifecycleSupport(this);
	
	/**
	 * 维护初始化流程
	 */
	@Override
	public void init() throws LifecycleException{
		/**
		 * demo
		 */
		//fireLifecycleEvent()   启动监听器 监听部分事件
		//initInternal()    与本身相关的初始化操作
	}
	
	/**
	 * 
	 * @author liyixiang
	 * @date 2016年12月19日 上午11:12:21
	 * @descriptor 初始化操作
	 * @throws LifecycleException
	 */
	protected abstract void initInternal() throws LifecycleException;

	@Override
	public void start() throws LifecycleException{
		
	}
		
	/**
	 * 
	 * @author liyixiang
	 * @date 2016年12月19日 上午11:12:28
	 * @descriptor
	 * @throws LifecycleException
	 */
	protected abstract void startInternal() throws LifecycleException;

	@Override
	public void stop() throws LifecycleException{
		
	}
	
	protected abstract void stopInternal() throws LifecycleException;

	@Override
	public void destory() {
		
	}
	
	protected abstract void destoryInternal() throws LifecycleException;


	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		// TODO Auto-generated method stub
		lifecycle.addLifecycleListener(listener);
	}

	@Override
	public void removeLifecycleListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO Auto-generated method stub
		return lifecycle.findLifecycleListeners();
	}
	
	/**
	 * 
	 * @use 根据事件类型唤醒事件源
	 * @param
	 * @return
	 */
	protected void fireLifecycleEvent(String type, Object data) {
        lifecycle.fireLifecycleEvent(type, data);
    }

}
