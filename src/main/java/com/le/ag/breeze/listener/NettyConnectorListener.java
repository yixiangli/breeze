package com.le.ag.breeze.listener;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.connector.Connector;
import com.le.ag.breeze.connector.support.NettyConnectorSupport;
import com.le.ag.breeze.event.LifecycleEvent;
import com.le.ag.breeze.exception.LifecycleException;

public class NettyConnectorListener implements LifecycleListener {

    private static final Logger logger = LoggerFactory.getLogger(NettyConnectorListener.class);
	
    protected Connector connector;
    
	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		// TODO Auto-generated method stub
		try{
			connector = (NettyConnectorSupport)event.getLifecycle();
		}catch (ClassCastException e){
			logger.error("",e);
		}
		
		//初始化之前
		if(event.getType().equals(Lifecycle.BEFORE_INIT_EVENT)){
			//线程设置
			if(connector instanceof NettyConnectorSupport){
				((NettyConnectorSupport) connector).setEventExecutor(new DefaultEventExecutorGroup(16));
				//处理客户端接收 绑定等工作的线程
				((NettyConnectorSupport) connector).setBossGroup(new NioEventLoopGroup());
		        //处理handler业务
				((NettyConnectorSupport) connector).setWorkGroup(new NioEventLoopGroup());
			}
		}
	}

}
