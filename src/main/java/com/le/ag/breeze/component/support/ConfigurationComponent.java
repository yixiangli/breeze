package com.le.ag.breeze.component.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.Configuration;
import com.le.ag.breeze.Constants;
import com.le.ag.breeze.component.StandardComponent;
import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.util.XMLUtil;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月10日
 * @use Config组件
 */
public class ConfigurationComponent extends StandardComponent {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationComponent.class);
	
    private static Configuration cfg;
    
	@Override
	public void init() throws LifecycleException {
		// TODO Auto-generated method stub
		initInternal();
	}

	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		try {
			cfg = getConfiguration(Constants.PROPERTIES);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("configuration build error",e);
			throw new LifecycleException("configuration build error",e.getCause());
		}
	}

	@Override
	protected void startInternal() throws LifecycleException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void stopInternal() throws LifecycleException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @use 获取配置文件实例
	 * @param configType 配置类型
	 * @return
	 */
	public static Configuration getConfiguration(String configType) throws Exception{
		return XMLUtil.parse(Constants.DEFAULT_CONFIG_FILE,configType);
	}

	public static String getString(String key) {
		// TODO Auto-generated method stub
		return cfg.getString(key);
	}

	public static boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return cfg.containsKey(key);
	}

	@Override
	protected void destoryInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		cfg = null;
	}


}
