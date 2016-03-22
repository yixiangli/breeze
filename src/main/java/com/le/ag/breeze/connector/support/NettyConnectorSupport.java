package com.le.ag.breeze.connector.support;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Map.Entry;

import com.le.ag.breeze.connector.AsynConnector;
import com.le.ag.breeze.connector.handler.DispatcherHandler;
import com.le.ag.breeze.exception.LifecycleException;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月9日
 * @use 连接服务中心－－－netty版
 */
public class NettyConnectorSupport extends AsynConnector{

	//	启动器
	private ServerBootstrap bootstrap;
    // 创建一个16个线程的线程组来处理耗时的业务逻辑                      
    private EventExecutorGroup  eventExecutor= new DefaultEventExecutorGroup(16);
		
	//初始化
	@Override
	public void init() throws LifecycleException{
		// TODO Auto-generated method stub	
		initInternal();
	}
	
	//启动
	@Override
	public void start() throws LifecycleException{
		// TODO Auto-generated method stub
		//super.start();
	}

	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		//处理客户端接收 绑定等工作的线程
        final EventLoopGroup bossGroup = new NioEventLoopGroup();
        //处理handler业务
        final EventLoopGroup workGroup = new NioEventLoopGroup();
        
        bootstrap = new ServerBootstrap();
        //初始化
        bootstrap.group(bossGroup, workGroup)
                   .channel(NioServerSocketChannel.class)
                   .childHandler(new ChannelInitializer<SocketChannel>(){

	                   @Override
	                   protected void initChannel(SocketChannel ch) throws Exception {
	                        // TODO Auto-generated method stub
	                        //目前是每次new出来 由于4.0不允许加入加入一个ChannelHandler超过一次，除非它由@Sharable注解，或者每次new出来
	                        //ch.pipeline().addLast("idleChannelTester", new IdleStateHandler(10, 5, 30));
	                        //ch.pipeline().addLast("keepaliveTimeoutHandler", keepaliveTimeoutHandler);
	                	   
	                        // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
	                        ch.pipeline().addLast("http-encoder", new HttpRequestDecoder());                 
	                        //将解析出来的http报文的数据组装成为封装好的httpRequest对象                              
	                        ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(1024 * 1024));
	                        // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
	                        ch.pipeline().addLast("http-decoder", new HttpResponseEncoder());
	                        //主要用于处理大数据流
	                        ch.pipeline().addLast("http-chunkedWriter", new ChunkedWriteHandler());         
	                        //加强业务逻辑处理效率
	                        ch.pipeline().addLast(eventExecutor,new DispatcherHandler());  
	                        
	                        /**
	                        // 这里只允许增加无状态允许共享的ChannelHandler，对于有状态的channelHandler需要重写
	                        // getPipelineFactory()方法
	                        for (Entry<String, ChannelHandler> channelHandler : channelHandlers.entrySet()) {
	                            ch.pipeline().addLast(channelHandler.getKey(), channelHandler.getValue());
	                        }
	                        
	                        **/
	                    }
                            
                    })                      
                    .option(ChannelOption.SO_BACKLOG, 1024000)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)   //tcp
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
	}

	@Override
	protected void startInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void destoryInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}
	
	//停止
	
	
	
	
	//销毁
}
