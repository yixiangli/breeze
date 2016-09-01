package com.le.ag.breeze.exception;


/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月12日
 * @since JDK 1.7
 * @Function 服务异常 封装运行时异常
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
