package com.le.ag.breeze.util;

import java.io.InputStream;
import java.util.LinkedHashMap;
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

import com.le.ag.breeze.Constants;
import com.le.ag.breeze.WebLoader;
import com.le.ag.breeze.exception.ServerException;


/**
 * 
 * @author liyixiang <liyixiang@letv.com>
 * @date 2016年3月11日
 * @use xml工具类
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

    static {
    	try{
    		INCLUDE_XPATH_EXPRESSION = xpath.compile("//include");
            RULE_XPATH_EXPRESSION = xpath.compile("//rule");
            FROM_XPATH_EXPRESSION = xpath.compile("from/text()");
            TO_XPATH_EXPRESSION = xpath.compile("to/text()");
    	} catch (Exception e){
    		logger.error("xml xpath compile error",e);
    		throw new ServerException("xml xpath compile error",e.getCause());
    	}
    }
    
    /**
     * 
     * @use xml文件匹配
     * @param
     * @return
     */
	@SuppressWarnings("unchecked")
    public static Map<Pattern, String> parse(String configFile) throws Exception {
        logger.info("初始化xml文件配置, {}", configFile);

        //获取文件流
        InputStream is = WebLoader.getStreamByResourceName(configFile);
        if (is == null) {
            logger.error("urlrewrite文件不存在");
            throw new ServerException("urlrewrite文件不存在");
        }
        //解析xml
        return analyzXml(is);
    }
	
	/**
	 * 
	 * @use 根据输入流解析成xml，并将规则存入map中
	 * @param
	 * @return
	 */
	private static Map<Pattern, String> analyzXml(InputStream inputStream) throws Exception{
        Map<Pattern, String> rules = new LinkedHashMap<Pattern, String>();
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setFeature(Constants.DTD_RULE_FILE_PATH, false);
        DocumentBuilder builder = factory.newDocumentBuilder();

        //InputStream convert Document
        Document xmlDoc = builder.parse(inputStream);
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
	
}
