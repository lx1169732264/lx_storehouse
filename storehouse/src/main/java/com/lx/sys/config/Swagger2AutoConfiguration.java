package com.lx.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: lx
 * EnableSwagger2   启用swagger的注解
 * 有可能出现扫描不到的情况 ,需要在启动类加上ComponentScan确保它被扫描到
 **/
@Configuration
@EnableSwagger2
public class Swagger2AutoConfiguration {

    /**
     * 在IOC容器里面创建可以对象可以扫描Controller里面的是否有Swagger相关的注解 如果，swagger会生成相关的文档
     *
     * @return
     */
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("api汇总").description("Tech Otakus save the world")
                .contact(new Contact("lx", "www.persona6.cn", "lx1169732264@foxmail.com"))
                .version("1.0")
                .license("lx")
                .build();
    }
}
