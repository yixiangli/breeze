package com.le.ag.breeze.event;

import java.util.EventObject;

import com.le.ag.breeze.Lifecycle;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月10日
 * @use 生命周期事件，基于java.util.EventObject实现
 * 封装了事件源对象及跟事件相关的信息。
 */
public class LifecycleEvent extends EventObject{

    /**
	 * @param 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * 事件发生时产生的对象
     */
    private Object data = null;


    /**
     * 事件类型
     */
    private String type = null;
	
	
	public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {

        super(lifecycle);
        this.type = type;
        this.data = data;
    }
	
	public Object getData() {
		return data;
	}

	public String getType() {
		return type;
	}

	/**
	 * 
	 * @use 获取当前生命周期
	 * @param
	 * @return
	 */
	public Lifecycle getLifecycle() {

        return (Lifecycle) getSource();

    }
}
