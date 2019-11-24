package com.scaffold.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerConfig(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .paths(PathSelectors.ant("/**"))
                .apis(RequestHandlerSelectors.basePackage("com.scaffold"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo("Customer API",
                "Sample Api for Microservice Scaffold",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Ankit Pandoh","https://dummyurl","dumm@gmail.com"),
                "API License",
                "Dummy Url",
                Collections.emptyList());
    }
}
