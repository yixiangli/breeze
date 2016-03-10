package com.le.ag.breeze.component;

import com.le.ag.breeze.listener.LifecycleListener;

public abstract class StandardComponent implements Component {

	@Override
	public abstract void start();

	@Override
	public abstract void stop();

	@Override
	public void addLifecycleListener(LifecycleListener lister) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeLifecycleListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO Auto-generated method stub
		return null;
	}

}
