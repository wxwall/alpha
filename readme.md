#alpha版本

####目录结构
 
 - busi: 业务功能
 - common:共公组件
 - compile:打包编译
 - config:配置中心客户端
 - model:实体类存放处
 - multi:本中心访问外部中心交互（非web）
 - outer：对外调用列表配置
 - parent:父工程
 - service:后端war包，API集成
 
####其他工程命名参考如下
  
  - ext:扩展
  - web:前端
  - schedule:任务
  
####需要配置host
> eureka返回是机器名，而不是IP地址

10.128.91.223 gzredis091223

####日志输出
 - 放在com.asiainfo的所有日志都会输出到日志文件中
 - 其他日志都以info的形式输出到控制台
  #####第三方组件集成日志说明
  > 此工程中用到的组件是logback，如果第三方组件是非logback，请excloud相应的组件。
 
####依赖关系
 - 父工程为parent
 - 所有模块依赖common
 - 所有模块可选依赖model，防止model与common同时依赖造成循环依赖
 - service-web为可执行集成tomcat的jar包
 - busi依赖multi，multi依赖outer，顺序不可变

####发布打包
  - 在parent上install打包即可
  - 发布jar包，在parent上deploye即可
  - 上传至测试环境，需要用到maven集成的wagon-maven-plugin插件，可直接上传重启等操作
  
####监控使用spring-boot-admin

   
   
####测试环境部署的一些配置

   - [springbootadmin测试环境地址](http://10.128.91.223:51001) ： 如果本机连测试环境，则状态是offerline，需要在测试环境配置对应的host
   - [eureka](http://10.128.91.223:50101/) :服务注册与发现
   - [service-web](http://10.128.91.223:50801/swagger-ui.html):swagger-ui
   - [apollo](http://10.128.91.223:50151/signin) 用户：apollo 密码：admin
   - [熔断器](http://10.128.91.223:50801/hystrix/monitor?stream=http://10.128.91.223:50801/hystrix.stream) 当GET /queryDemoForFeign方法时，可以看到数据，详情可查看**swagger-ui**界面
   
  

 
 
