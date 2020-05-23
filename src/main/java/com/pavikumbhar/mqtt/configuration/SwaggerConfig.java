package com.pavikumbhar.mqtt.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pavikumbhar.mqtt.MqttApplication;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * @author pavikumbhar
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${application.formatted-version:development}")
    private String version;

    @Value("${application.title:MqttApplication}")
    private String title;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(MqttApplication.class.getPackage().getName())
                .select()
                .apis(RequestHandlerSelectors.basePackage(MqttApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .build();

    }
}
