package com.le.ag.breeze.exception;

public final class LifecycleException extends Exception{

	/**
	 * @param 
	 */
	private static final long serialVersionUID = 1L;

	public LifecycleException(){
		super();
	}
	
	public LifecycleException(String message){
		super(message);
	}
	
	public LifecycleException(Throwable throwable){
		super(throwable);
	}
	
	public LifecycleException(String message,Throwable throwable){
		super(message,throwable);
	}
}
