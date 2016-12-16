package com.le.ag.breeze.component.support;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年9月1日
 * @since JDK 1.7
 * @Function properties文件配置组件
 */
public class FilePropertiesConfigurationComponent extends PropertiesConfigurationComponent {

	private static final Logger logger = LoggerFactory.getLogger(FilePropertiesConfigurationComponent.class);

    public FilePropertiesConfigurationComponent(String propertiesFile) {
        Properties props = new Properties();
        try {
            InputStream is = new FileInputStream(propertiesFile);
            props.load(is);
            this.properties = props;
        } catch (Exception ex) {
            logger.error("file properties load error", ex);
            this.properties = null;
        }
    }

}
