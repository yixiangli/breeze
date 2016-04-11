package com.le.ag.breeze.connector.support;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.connector.AsynConnector;
import com.le.ag.breeze.connector.handler.HttpHandler;
import com.le.ag.breeze.exception.LifecycleException;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月9日
 * @use 连接服务中心－－－netty版
 */
public class NettyConnectorSupport extends AsynConnector{

    private static final Logger logger = LoggerFactory.getLogger(NettyConnectorSupport.class);
	
    private int port = -1;
	//	启动器
	private ServerBootstrap bootstrap;
	// 线程组
	private EventLoopGroup bossGroup;
	private EventLoopGroup workGroup;
    // 创建一个16个线程的线程组来处理耗时的业务逻辑                      
    private EventExecutorGroup  eventExecutor;
		
	//初始化
	@Override
	public void init() throws LifecycleException{
		// TODO Auto-generated method stub	
		fireLifecycleEvent(BEFORE_INIT_EVENT, null);
		initInternal();
	}
	
	//启动
	@Override
	public void start() throws LifecycleException{
		// TODO Auto-generated method stub
		logger.info("server start in {}", getPort());
        try {
            ChannelFuture cf = bootstrap.bind(new InetSocketAddress(getPort())).sync();
            cf.channel().closeFuture().sync();
        } catch (Exception ex){
        	logger.error("server start failure!",ex);
        	ex.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
	}

	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
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
	                        ch.pipeline().addLast(eventExecutor,"http-processor",new HttpHandler());  
	                        
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


	public void setBossGroup(EventLoopGroup bossGroup) {
		this.bossGroup = bossGroup;
	}

	public void setWorkGroup(EventLoopGroup workGroup) {
		this.workGroup = workGroup;
	}

	public void setEventExecutor(EventExecutorGroup eventExecutor) {
		this.eventExecutor = eventExecutor;
	}

	public void setPort(int port) {
		// TODO Auto-generated method stub
		this.port = port;
	}

	public int getPort() {
		// TODO Auto-generated method stub
		return port;
	}
	
	//停止
	
	
	
	
	//销毁
	
	
}
