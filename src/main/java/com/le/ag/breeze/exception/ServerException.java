package com.le.ag.breeze.exception;


/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月12日
 * @use 服务异常 封装运行时异常
 */
public class ServerException extends RuntimeException {

	/**
	 * @param 
	 */
	private static final long serialVersionUID = 1L;

	public ServerException() {
        super();
    }
	
	public ServerException(String message) {
        super(message);
    }
	
	public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public ServerException(Throwable cause) {
        super(cause);
    }
	
}
