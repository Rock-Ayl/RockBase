package org.rock.base.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Knife4j 3 配置(Swagger配置)
 *
 * @Author ayl
 * @Date 2022-03-26
 */
@Configuration
@EnableKnife4j
public class Knife4jConfig {

    @Bean()
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        //标题
                        .title("org.rock.api.document")
                        //描述
                        .description("这是base基底的接口文档.")
                        //联系人
                        .contact(new Contact("rock", "org.rock", "Rock-Ayl@foxmail.com"))
                        //版本
                        .version("1.0.0.2")
                        .build())
                //分组名称(也可以理解为版本)
                .groupName("1.0")
                //选择哪些接口作为swagger的doc发布
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("org.rock.base.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}