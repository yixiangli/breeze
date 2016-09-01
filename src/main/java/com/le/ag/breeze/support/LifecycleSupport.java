package com.le.ag.breeze.support;

import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.event.LifecycleEvent;
import com.le.ag.breeze.listener.LifecycleListener;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月10日
 * @since JDK 1.7
 * @Function 生命周期基本实现
 */
public class LifecycleSupport {

	/**
     * The source component for lifecycle events that we will fire.
     */
    private Lifecycle lifecycle = null;
	
    /**
     * 生命周期监听器数组
     */
    private LifecycleListener listeners[] = new LifecycleListener[0];
    
    //监听器锁
    private byte[] listenersLock = new byte[0];
    
	public LifecycleSupport(Lifecycle lifecycle) {
        super();
        this.lifecycle = lifecycle;
    }
	
	/**
	 * 
	 * @use 添加监听器
	 * @param
	 * @return
	 */
	public void addLifecycleListener(LifecycleListener listener) {
	      synchronized (listenersLock) {
	          LifecycleListener results[] = new LifecycleListener[listeners.length + 1];
	          for (int i = 0; i < listeners.length; i++) {
	              results[i] = listeners[i];
	          }
	          results[listeners.length] = listener;
	          listeners = results;
	      }
	}
	
	
	public LifecycleListener[] findLifecycleListeners() {
        return listeners;
    }
	
	/**
	 * 
	 * @use  唤醒监听事件
	 * @param
	 * @return
	 */
	public void fireLifecycleEvent(String type, Object data) {
        LifecycleEvent event = new LifecycleEvent(lifecycle, type, data);
        LifecycleListener interested[] = listeners;
        for (int i = 0; i < interested.length; i++) {
            interested[i].lifecycleEvent(event);
        }
    }
}
