package com.le.ag.breeze.listener;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.Constants;
import com.le.ag.breeze.Lifecycle;
import com.le.ag.breeze.component.support.ConfigurationComponent;
import com.le.ag.breeze.connector.Connector;
import com.le.ag.breeze.connector.support.NettyConnectorSupport;
import com.le.ag.breeze.event.LifecycleEvent;
import com.le.ag.breeze.exception.ServerException;

public class NettyConnectorListener implements LifecycleListener {

    private static final Logger logger = LoggerFactory.getLogger(NettyConnectorListener.class);
	
    protected Connector connector;
    
	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		// TODO Auto-generated method stub
		try{
			connector = (NettyConnectorSupport)event.getLifecycle();
		}catch (ClassCastException e){
			logger.error("class cast exception",e);
			throw new ServerException("class cast exception");
		}
		
		//初始化之前
		if(event.getType().equals(Lifecycle.BEFORE_INIT_EVENT)){
			//线程设置
			if(connector instanceof NettyConnectorSupport){
				
				Integer bossGroupNumber = ConfigurationComponent.getInt(Constants.BOSS_GROUP_NUM);
				Integer workerGroupNumber = ConfigurationComponent.getInt(Constants.WORK_GROUP_NUM);
				Integer eventExecutorGroupNum = ConfigurationComponent.getInt(Constants.EVENT_EXECUTOR_GROUP_NUM);
				
				//boss线程数
				if(null == bossGroupNumber){
					//处理客户端接收 绑定等工作的线程
					((NettyConnectorSupport) connector).setBossGroup(new NioEventLoopGroup());
				}else {
					((NettyConnectorSupport) connector).setBossGroup(new NioEventLoopGroup(bossGroupNumber));
				}
				
				//worker线程数
				if(null == workerGroupNumber){
					((NettyConnectorSupport) connector).setWorkGroup(new NioEventLoopGroup());
				}else {
					((NettyConnectorSupport) connector).setWorkGroup(new NioEventLoopGroup(workerGroupNumber));
				}
				
		        //处理handler业务
				if(null != eventExecutorGroupNum){
					((NettyConnectorSupport) connector).setEventExecutor(new DefaultEventExecutorGroup(eventExecutorGroupNum));
				}
			}
		}
	}

}
