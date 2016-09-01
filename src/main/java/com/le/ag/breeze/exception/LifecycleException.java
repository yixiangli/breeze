package com.le.ag.breeze.exception;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年9月1日
 * @since JDK 1.7
 * @Function 生命周期内异常
 */
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
