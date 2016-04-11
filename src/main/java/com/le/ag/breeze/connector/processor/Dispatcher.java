package com.le.ag.breeze.connector.processor;

import io.netty.handler.codec.http.HttpMethod;


/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月31日
 * @use 分发器
 */
public class Dispatcher {
	
	public void dispatch(HttpMethod method){
		if(method.equals(HttpMethod.GET)){
			doGet();
		}else if(method.equals(HttpMethod.POST)){
			doPost();
		}else {
			
		}
	}
	
	public void doGet(){
		
	}
	
	public void doPost(){
		
	}
	
	public void doHead(){
		
	}
	
	public void doPut(){
		
	}
	
	public void doDelete(){
		
	}
	
	public void doOptions(){
		
	}
	
	public void doTrace(){
		
	}

}
