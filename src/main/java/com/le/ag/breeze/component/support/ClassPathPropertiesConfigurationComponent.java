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
	
    public Integer getInt(String key){
    	String valueStr = this.properties.getProperty(key);
    	Integer value;
    	try{
    		//防止用户配置前后带空格
    		value= Integer.parseInt(valueStr.trim());
    	}catch (Exception e){
    		return null;
    	}
    	return value;
    }

	@Override
	public String getString(String key) {
		// TODO Auto-generated method stub
		String value = this.properties.getProperty(key);
		return value;
	}
    
}
