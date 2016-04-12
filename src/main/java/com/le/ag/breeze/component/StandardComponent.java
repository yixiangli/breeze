package com.le.ag.breeze.component;

import com.le.ag.breeze.LifecycleBase;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月11日
 * @use 标准组件
 */
public abstract class StandardComponent extends LifecycleBase implements Component {

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		destoryInternal();
	}
	
	@Override
	protected void destoryInternal() {
		// TODO Auto-generated method stub
		System.out.println("destory component");
	}
}
