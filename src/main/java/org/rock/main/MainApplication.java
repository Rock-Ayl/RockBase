package org.rock.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @Author ayl
 * @Date 2022-03-09
 */
//开启定时任务
@EnableScheduling
//MyBatis-Plus扫描所有mapper路径
@MapperScan("org.rock.main.mapper")
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
