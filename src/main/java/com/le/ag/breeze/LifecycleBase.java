package com.le.ag.breeze;

import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.listener.LifecycleListener;
import com.le.ag.breeze.support.LifecycleSupport;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月10日
 * @use 生命周期控制基类
 */
public abstract class LifecycleBase implements Lifecycle {

	private LifecycleSupport lifecycle = new LifecycleSupport(this);
	
	@Override
	public void init() throws LifecycleException{
		
	}
	
	protected abstract void initInternal() throws LifecycleException;

	@Override
	public void start() throws LifecycleException{
		
	}
		
	protected abstract void startInternal() throws LifecycleException;

	@Override
	public void stop() throws LifecycleException{
		
	}
	
	protected abstract void stopInternal() throws LifecycleException;

	@Override
	public void destory() throws LifecycleException{
		
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
