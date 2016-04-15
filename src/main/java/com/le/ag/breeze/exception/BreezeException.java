package com.le.ag.breeze.exception;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月12日
 * @use 服务异常 封装运行时异常
 */
public class BreezeException extends RuntimeException {

	/**
	 * @param 
	 */
	private static final long serialVersionUID = 1L;

	public BreezeException() {
        super();
    }
	
	public BreezeException(String message) {
        super(message);
    }
	
	public BreezeException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public BreezeException(Throwable cause) {
        super(cause);
    }
	
}
