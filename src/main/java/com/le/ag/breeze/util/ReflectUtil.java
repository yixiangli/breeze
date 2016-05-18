package com.le.ag.breeze.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.exception.ServerException;


/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月11日
 * @use 反射工具类
 */
public class ReflectUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    // 主要用来缓存Method对象，提升反射效率
    private static final Map<MethodDescriptor, Method> methodCachePool = new HashMap<ReflectUtil.MethodDescriptor, Method>();

	/**
	 * 
	 * @use 根据类 方法名 以及参数列表 返回Method
	 * @param
	 * @return
	 */
	public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
        MethodDescriptor md = new MethodDescriptor(clazz, methodName, paramTypes);
        //先取缓存
        Method method = getCachedMethod(md);
        if (method != null) {
            return method;
        }
        //缓存不为空
        try {
            method = clazz.getDeclaredMethod(methodName, paramTypes);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            //存入缓存
            cacheMethod(md, method);
            return method;
        } catch (Exception ex) {
        	ex.printStackTrace();
            logger.error("reflect getMethod exception", ex.getCause());
            throw new ServerException("reflect getMethod exception",ex.getCause());
        }
    }
	
    private static void cacheMethod(MethodDescriptor md, Method method) {
        if (method != null) {
            methodCachePool.put(md, method);
        }
    }
	
	private static Method getCachedMethod(MethodDescriptor md) {
        Method method = methodCachePool.get(md);
        if (method != null) {
            return method;
        }
        return null;
    }

	
	//方法描述符
	private static class MethodDescriptor {
        @SuppressWarnings("rawtypes")
        private Class clazz;
        private String methodName;
        @SuppressWarnings("rawtypes")
        private Class[] parameterTypes;

        private int hashCode;

        @SuppressWarnings("rawtypes")
        public MethodDescriptor(Class clazz, String methodName, Class[] parameterTypes) {
            this.clazz = clazz;
            this.methodName = methodName;
            this.parameterTypes = parameterTypes;
            this.hashCode = methodName.length();
        }

        public int hashCode() {
            return this.hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof MethodDescriptor)) {
                return false;
            }

            MethodDescriptor md = (MethodDescriptor) obj;
            return methodName.equals(md.methodName) && clazz.equals(md.clazz)
                    && Arrays.equals(parameterTypes, md.parameterTypes);
        }
    }
}
