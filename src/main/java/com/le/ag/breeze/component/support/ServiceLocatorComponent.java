package com.le.ag.breeze.component.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.Constants;
import com.le.ag.breeze.WebLoader;
import com.le.ag.breeze.component.StandardComponent;
import com.le.ag.breeze.connector.processor.ServiceLocator;
import com.le.ag.breeze.exception.LifecycleException;
import com.le.ag.breeze.util.StringUtils;

public class ServiceLocatorComponent extends StandardComponent {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLocatorComponent.class);
	
	private static ServiceLocator serviceLocator;

	@Override
	public void init() throws LifecycleException {
		// TODO Auto-generated method stub
		initInternal();
	}
	
	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		String serviceLocatorClazz = ConfigurationComponent.getString(Constants.SERVICE_LOCATOR_SUPPORT);
		if(StringUtils.isBlank(serviceLocatorClazz)){
			
		}else {
			serviceLocator = (ServiceLocator)WebLoader.getInstance(serviceLocatorClazz);
			if (serviceLocator == null) {
				logger.info("实例化类{}失败", serviceLocatorClazz);
			}
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
	 * @use 获取serviceLocator实例
	 * @param
	 * @return
	 */
	public static ServiceLocator getServiceLocator(){
		return serviceLocator;
	}

	@Override
	protected void destoryInternal() throws LifecycleException {
		// TODO Auto-generated method stub
		serviceLocator = null;
	}
	
	
}
