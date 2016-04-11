package com.le.ag.breeze.component.support;

import com.le.ag.breeze.WebLoader;

public class ClassPathPropertiesConfigurationComponent extends PropertiesConfigurationComponent {

    public ClassPathPropertiesConfigurationComponent(String propertiesFile) {	
        this.properties = WebLoader.getProperties(propertiesFile);
    }

}
