package org.rock.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


//开启注解 @Async
@EnableAsync
//开启注解 @Scheduled
@EnableScheduling
//MyBatis-Plus扫描所有mapper路径
@MapperScan("org.rock.base.mapper")
//springboot主启动程序
@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

}
