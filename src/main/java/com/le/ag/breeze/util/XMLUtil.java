package com.le.ag.breeze.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.le.ag.breeze.Configuration;
import com.le.ag.breeze.Constants;
import com.le.ag.breeze.WebLoader;
import com.le.ag.breeze.component.support.PropertiesConfigurationComponent;
import com.le.ag.breeze.exception.ServerException;

/**
 * 
 * @author liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年3月11日
 * @since JDK 1.7
 * @Function xml工具类
 */
public final class XMLUtil {

    private static final Logger logger = LoggerFactory.getLogger(XMLUtil.class);
	
    //待优化
    private static final XPathFactory xpathFactory = XPathFactory.newInstance();
    private static final XPath xpath = xpathFactory.newXPath();
    private static XPathExpression INCLUDE_XPATH_EXPRESSION;
    private static XPathExpression RULE_XPATH_EXPRESSION;
    private static XPathExpression FROM_XPATH_EXPRESSION;
    private static XPathExpression TO_XPATH_EXPRESSION;
    private static XPathExpression RESOURCE_XPATH_EXPRESSION;

    static {
    	try{
    		INCLUDE_XPATH_EXPRESSION = xpath.compile(Constants.INCLUDE);
            RULE_XPATH_EXPRESSION = xpath.compile(Constants.RULE);
            FROM_XPATH_EXPRESSION = xpath.compile(Constants.FROM);
            TO_XPATH_EXPRESSION = xpath.compile(Constants.TO);
            RESOURCE_XPATH_EXPRESSION = xpath.compile("//properties/@resource");
    	} catch (Exception e){
    		logger.error("xml xpath compile error",e);
    		throw new ServerException("xml xpath compile error",e.getCause());
    	}
    }
    
    /**
     * 
     * @use 加载文件流
     * @param
     * @return
     */
    public static InputStream loading(String configFile) throws Exception {
    	logger.info("初始化xml文件配置, {}", configFile);

        //获取文件流
    	InputStream is = WebLoader.getStreamByResourceName(configFile);    	
   
        if (is == null) {
            logger.error("config文件不存在");
            throw new ServerException("config文件不存在");
        }
        return is;
    }
    
    /**
     * 
     * @use xml文件匹配
     * @param
     * @return
     */
    public static Map<Pattern, String> parse(String configFile) throws Exception {
		//加载文件
		InputStream is = loading(configFile);
        //解析xml
        return analyzXml(is);
    }

	/**
	 * 
	 * @use 构建xml Document对象
	 * @param
	 * @return
	 */
	public static Document buildXMLDocument(InputStream inputStream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setFeature(Constants.DTD_RULE_FILE_PATH, false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        //InputStream convert Document
        return builder.parse(inputStream);
	}
	
	
	
	/**
	 * 
	 * @use 根据输入流解析成xml，并将规则存入map中
	 * @param
	 * @return
	 */
	private static Map<Pattern, String> analyzXml(InputStream inputStream) throws Exception{
        Map<Pattern, String> rules = new LinkedHashMap<Pattern, String>();
		
        Document xmlDoc = buildXMLDocument(inputStream);
        // process include node
        NodeList includeNodes = (NodeList) INCLUDE_XPATH_EXPRESSION.evaluate(xmlDoc, XPathConstants.NODESET);

        for (int i = 0; i < includeNodes.getLength(); i++) {
            Element includeElement = (Element) includeNodes.item(i);
            rules.putAll(parse(includeElement.getAttribute(Constants.INCLUDE_SOURCE)));
        }

        // process rule node
        NodeList ruleNodes = (NodeList) RULE_XPATH_EXPRESSION.evaluate(xmlDoc, XPathConstants.NODESET);

        for (int i = 0; i < ruleNodes.getLength(); i++) {
            Element ruleElement = (Element) ruleNodes.item(i);
            //对外url
            String from = (String) FROM_XPATH_EXPRESSION.evaluate(ruleElement, XPathConstants.STRING);
            //方法匹配规则mapping
            String to = (String) TO_XPATH_EXPRESSION.evaluate(ruleElement, XPathConstants.STRING);
            Pattern pattern4From = Pattern.compile(from, Pattern.CASE_INSENSITIVE);
            rules.put(pattern4From, to);
        }
        return rules;
	}
	
	/**
	 * 
	 * @use
	 * @param
	 * @return
	 */
	public static Configuration parse(String configFile,String configType) throws Exception{	
		//加载文件
		InputStream is = loading(configFile);
		//解析xml
		return getConfiguration(is,configType);
	}
	
	/**
	 * 
	 * @use
	 * @param
	 * @return
	 */
	public static Configuration getConfiguration(InputStream inputStream,String configType) throws Exception{
        Document xmlDoc = buildXMLDocument(inputStream);
		NodeList resourceNodes = (NodeList) RESOURCE_XPATH_EXPRESSION.evaluate(xmlDoc, XPathConstants.NODESET);

         List<String> resourceNames = new ArrayList<String>();
         for (int i = 0, size = resourceNodes.getLength(); i < size; i++) {
             resourceNames.add(StringUtils.trim(resourceNodes.item(i).getTextContent()));
         }
         if(Constants.PROPERTIES.equals(configType)){
        	 return new PropertiesConfigurationComponent(resourceNames.toArray(new String[]{}));
         }
         //默认
         return new PropertiesConfigurationComponent();
	}
	
	
	public static String convertStreamToString(InputStream is) {   
		   BufferedReader reader = new BufferedReader(new InputStreamReader(is));   
		        StringBuilder sb = new StringBuilder();   
		        String line = null;   
		        try {   
		            while ((line = reader.readLine()) != null) {   
		                sb.append(line + "/n");   
		            }   
		        } catch (IOException e) {   
		            e.printStackTrace();   
		        } finally {   
		            try {   
		                is.close();   
		            } catch (IOException e) {   
		                e.printStackTrace();   
		            }   
		        }   
		        return sb.toString();   
		    }   
	
	
}
