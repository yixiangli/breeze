package com.le.ag.breeze.container.context.support;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.container.context.Context;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月23日
 * @use
 */
public class MessageContext implements Context {

    private static final Logger logger = LoggerFactory.getLogger(MessageContext.class);

    private final ConcurrentHashMap<String,String> parameters = new ConcurrentHashMap<String,String>();
}
