package com.le.ag.breeze.component;

import com.le.ag.breeze.LifecycleBase;
import com.le.ag.breeze.exception.LifecycleException;

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
	
	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void startInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

}
