package com.le.ag.breeze;

import com.le.ag.breeze.listener.LifecycleListener;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月8日
 * @use 生命周期基类
 */
public interface Lifecycle {

    // ----------------------------------------------------- Manifest Constants
    /**
     * The LifecycleEvent type for the "component after init" event.
     */
    public static final String BEFORE_INIT_EVENT = "before_init";


    /**
     * The LifecycleEvent type for the "component after init" event.
     */
    public static final String AFTER_INIT_EVENT = "after_init";


    /**
     * The LifecycleEvent type for the "component start" event.
     */
    public static final String START_EVENT = "start";


    /**
     * The LifecycleEvent type for the "component before start" event.
     */
    public static final String BEFORE_START_EVENT = "before_start";


    /**
     * The LifecycleEvent type for the "component after start" event.
     */
    public static final String AFTER_START_EVENT = "after_start";


    /**
     * The LifecycleEvent type for the "component stop" event.
     */
    public static final String STOP_EVENT = "stop";


    /**
     * The LifecycleEvent type for the "component before stop" event.
     */
    public static final String BEFORE_STOP_EVENT = "before_stop";


    /**
     * The LifecycleEvent type for the "component after stop" event.
     */
    public static final String AFTER_STOP_EVENT = "after_stop";


    /**
     * The LifecycleEvent type for the "component after destroy" event.
     */
    public static final String AFTER_DESTROY_EVENT = "after_destroy";


    /**
     * The LifecycleEvent type for the "component before destroy" event.
     */
    public static final String BEFORE_DESTROY_EVENT = "before_destroy";

	/**
	 * 
	 * @use 初始化
	 * @param
	 * @return
	 */
	void init();

	/**
	 * 
	 * @use
	 * 		统一管理component的生命周期的启动
	 * @param
	 * @return
	 */
	void start();
	
	/**
	 * 
	 * @use
	 * 		统一管理component的生命周期的终止
	 * @param
	 * @return
	 */
	void stop();
	
	
	/**
	 * 
	 * @use 销毁
	 * @param
	 * @return
	 */
	void destory();
	
	
	void addLifecycleListener(LifecycleListener lister);
	
	void removeLifecycleListener();
	
	LifecycleListener[] findLifecycleListeners();
	
	
	
}
