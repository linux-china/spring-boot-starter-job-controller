spring-boot-start-job-controller
================================
Spring boot starter Job Controller。

### 如何使用

* 在Spring Boot项目的pom.xml中添加以下依赖:

          <dependency>
                     <groupId>com.mvnsearch.spring.boot</groupId>
                     <artifactId>spring-boot-starter-job-controller</artifactId>
                     <version>1.0.0-SNAPSHOT</version>
          </dependency>

* 在Spring Boot的application.properties添加是否启动启动的配置，如下:
                    
          spring.job.controller.auto-start=true=false

* 在你的项目中添加一下代码,我们是建议一个class只执行一个job,这样方便监控数据: 

```java

import org.mvnsearch.spring.boot.job.controller.JobController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * running
 *
 * @author linux_china
 */
@Component
public class CronDemo extends JobController {

    public String getName() {
        return "demo";
    }

    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void demo() {
        String userName = "Jacky" + System.currentTimeMillis();
        run(() -> {
            System.out.println(userName);
        });
    }
}
```

### Job的状态

* paused: 暂停
* running: 运行中
* finished: 运行结束
* ready: 准备运行

### spring-boot-start-mybatis提供的服务

* /schedule/ready:准备运行
* /schedule/paused: 暂停运行
* /schedule/start: 启动某一job
* /schedule/statics: job的统计信息


### 参考文档

* Task Execution and Scheduling: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/scheduling.html
