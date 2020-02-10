package com.itdan.springdemo03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //访问路径为localhost:8080/swagger-ui.html

    @Bean
    Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itdan.springdemo03.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .description("接口文档的前述信息")
                        .title("测试文档")
                        .contact(new Contact("dan", "localhost:8080", "2207161187@qq.com"))
                        .version("v1.0")
                        .license("api1.0")
                        .build());

    }
}
