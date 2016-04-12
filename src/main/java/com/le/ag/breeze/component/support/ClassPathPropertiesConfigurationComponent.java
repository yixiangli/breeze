package com.le.ag.breeze.component.support;

import com.le.ag.breeze.WebLoader;

public class ClassPathPropertiesConfigurationComponent extends PropertiesConfigurationComponent {

    public ClassPathPropertiesConfigurationComponent(String propertiesFile) {	
        this.properties = WebLoader.getProperties(propertiesFile);
    }

    @Override
    public boolean containsKey(String key) {
		return this.properties.containsKey(key);
    }
	

	@Override
	public String getString(String key) {
		// TODO Auto-generated method stub
		String value = this.properties.getProperty(key);
		return value;
	}
    
}
