# small
商城系统
用户模块：实现登录、注册、登出、未登录修改密码、登录修改密码、获取用户信息。
密码使用MD5加密，修改密码使用本地token验证，登录信息保存至redis分布式缓存中，重写cookie。

Tomcat集群部署:tomcat单机集群部署，配置环境变量：CATALINA_HOME、CATALINA_BASE、   修改Tomcat下bin目录中catalina.bat、startup.bat、shutdown.bat文件的环境变量，修改server中的端口，

redis配置：过滤器实现点击请求重置redis中session的有效时间，拦截器实现登录权限统一验证