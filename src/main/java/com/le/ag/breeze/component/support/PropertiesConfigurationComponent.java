package com.le.ag.breeze.component.support;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.breeze.Configuration;
import com.le.ag.breeze.Constants;
import com.le.ag.breeze.util.StringUtils;

/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年4月10日
 * @use 支持properties形式的配置文件
 */
public class PropertiesConfigurationComponent implements Configuration {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesConfigurationComponent.class);
	
    protected Properties properties;
    private static Configuration[] configurations;
	
    public PropertiesConfigurationComponent() {
		// TODO Auto-generated constructor stub
	}
    
	public PropertiesConfigurationComponent(String[] configPropertiesFiles) {
		// TODO Auto-generated constructor stub
		initConfigurations(configPropertiesFiles);
	}
	
	private void initConfigurations(String[] configPropertiesFiles) {
        if (configPropertiesFiles == null || configPropertiesFiles.length == 0) {
            return;
        }
        configurations = new Configuration[configPropertiesFiles.length];
        for (int i = 0; i < configurations.length; i++) {
            logger.info("loading properties file: {}", configPropertiesFiles[i]);
            //file:形式配置
            if (configPropertiesFiles[i].startsWith(Constants.PREFIX_FILE)) {
                configurations[i] = new FilePropertiesConfigurationComponent(StringUtils.substringAfter(configPropertiesFiles[i], Constants.PREFIX_FILE));
            //properties:形式配置
            } else if (configPropertiesFiles[i].startsWith(Constants.PREFIX_CLASSPATH)) {
                configurations[i] = new ClassPathPropertiesConfigurationComponent(StringUtils.substringAfter(configPropertiesFiles[i], Constants.PREFIX_CLASSPATH));
            }
        }
    }

	@Override
	public String getString(String key) {
		// TODO Auto-generated method stub
		for (Configuration configuration : configurations) {
            if (configuration.containsKey(key)) {
                return configuration.getString(key);
            }
        }
        return StringUtils.EMPTY;
	}

	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		for (Configuration cfg : configurations) {
            if (cfg.containsKey(key)) {
                return true;
            }
        }
        return false;
	}

}
