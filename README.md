breeze
====
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/cyfonly/FLogger/blob/master/LICENSE)  [![Built with Maven](http://maven.apache.org/images/logos/maven-feather.png)](http://search.maven.org/#search%7Cga%7C1%7Ccyfonly)  

## Install
下载jar

## Credits
yixiangli - 李轶翔

## Features
### 基本功能
	1. 自定义请求路由规则，url重定向
	2. 支持get/post请求解析
	
### 特色功能
	1. 支持请求限流
	2. 性能优良
	3. 天生异步请求处理栈，线程池自定义配置

## Usage
	1. 继承BreezeServerFacade,编写main函数
	2. 配置urlrewrite.xml文件
	3. 实现ServiceLocator接口，实现服务定位功能
	4. 配置config.xml 引入properties,配置线程池&请求限流&服务定位功能等参数

## License
基于 Apache License 2.0 发布。有关详细信息，请参阅 [LICENSE](https://github.com/yixiangli/breeze/blob/master/LICENSE)。

## History
发布日期 | 版本 | 备注
--- | --- | --- 
2016-4-15 | v1.0 	  |  发现:bug-1:请求路径按前缀匹配 bug-2:应用模块需要配置netty依赖 bug-3:post请求异常                 
2016-4-17 | v1.0.1    |  解决:bug-3
2016-4-17 | v1.0.2    |  优化:引擎线程数可以通过用户properties配置设置												   
2016-4-17 | v1.0.2.1  |  优化:业务自定义线程池如果不配置可以不使用												   
2016-4-17 | v1.0.2.2  |  优化:logback使业务线程block											   
2016-4-17 | v1.0.3    |  优化:log日志清理												   
2016-4-18 | v1.0.3.1  |  优化:无用类清理/新增获取remoteAddr功能/拦截favicon.ico请求												   												   
2016-4-27 | v1.0.3.2  |  解决:bug-4 由于xpath.compile中路径表达式设置有误,rule规则只生效第一个配置
2016-4-30 | v1.0.3.3  |  优化:获取remoteAddress,新增从context中获取hostAddress
2016-5-16 | v1.0.3.3  |  整理:学习markdown语言,更新README.md文件
2016-5-18 | v1.0.3.4  |  整理:清理未使用但import的类,添加部分注解，消除框架中出现的警告
2016-8-31 | v1.0.3.5  |  整理:注释整理
2016-12-15 | v1.0.4   |  解决:bug-5 Component组件监听器未注册 新增功能:请求限流
2016-12-19 | v1.1     |  优化:添加注释,调整代码结构,编写接入指南文档与开发文档

## Manifest
	|- doc
	|  |- Develop.txt
	|  |- Guide.txt
	|- src
	|  |- main
	|     |- java
	|        |- com
	|          |- le
	|             |- ag
	|                |- breeze
	|- .gitignore
	|- LICENSE
	|- README.md
	|- pom.xml