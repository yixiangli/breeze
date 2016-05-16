breeze upgrade log
====

发布日期 |  版本 |  备注
--- | --- | ---

2016-4-15		|	  v1.0 Pre-alpha		|	   bug-1:请求路径按前缀匹配	         时间2016-4-15
                                                       bug-2:应用模块需要配置netty依赖  时间2016-4-16
													   bug-3:post请求异常                  时间2016-4-17
--- | --- | ---
2016-4-17        |     v1.0.1 Pre-alpha      |     解决:bug-3
--- | --- | ---
2016-4-17        |     v1.0.2 Pre-alpha     |      优化:引擎线程数可以通过用户properties配置设置
--- | --- | ---													   
2016-4-17       |      v1.0.2.1 Pre-alpha    |     优化:业务自定义线程池如果不配置可以不使用
--- | --- | ---													   
2016-4-17        |     v1.0.2.2 Pre-alpha     |    优化:logback使业务线程block
--- | --- | ---												   
2016-4-17        |     v1.0.3 Pre-alpha       |    优化:log日志清理
--- | --- | ---													   
2016-4-18        |     v1.0.3.1 Pre-alpha    |     优化:无用类清理/新增获取remoteAddr功能/拦截favicon.ico请求												   
--- | --- | ---													   
2016-4-27        |     v1.0.3.2 Pre-alpha     |    解决:bug-4 由于xpath.compile中路径表达式设置有误,rule规则只生效第一个配置
--- | --- | ---
2016-4-30		|	 v1.0.3.3 Pre-alpha     |    优化:获取remoteAddress,新增从context中获取hostAddress
--- | --- | ---
2016-5-16       |     v1.0.3.3 Pre-alpha     |   学习markdown语言,更新README.md文件
--- | --- | ---