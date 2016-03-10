package com.le.ag.breeze.connector;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月8日
 * @use 接入层基类
 */
public interface Connector {

	/**
	 * 
	 * @use 监听端口
	 * @param port 端口
	 * @return
	 */
	public void listen(int port);
}
