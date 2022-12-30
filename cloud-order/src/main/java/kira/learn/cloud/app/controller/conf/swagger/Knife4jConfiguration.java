package kira.learn.cloud.app.controller.conf.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.function.Predicate;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/9 11:52
 */

@Configuration
@EnableConfigurationProperties(Swagger2Properties.class)
@ConditionalOnProperty(prefix = "swagger", name = {"enable"}, havingValue = "true", matchIfMissing = true)
@EnableSwagger2WebMvc
public class Knife4jConfiguration {


    private final Swagger2Properties swagger2Properties;

    public Knife4jConfiguration(Swagger2Properties swagger2Properties) {
        this.swagger2Properties = swagger2Properties;
    }

    private final Predicate<RequestHandler> DEFAULT_REQUEST_HANDLER = RequestHandlerSelectors.withClassAnnotation(RestController.class)
            .and(RequestHandlerSelectors.withClassAnnotation(IgnoreApi.class).negate())
            .and(RequestHandlerSelectors.withMethodAnnotation(IgnoreApi.class).negate());


    @Bean(value = "dockerBean")
    public Docket dockerBean(@Value("${spring.application.name:default_group}") String group) {
        //指定使用Swagger2规范
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(group);
        return docket.select()
                //swagger scan path
                .apis(DEFAULT_REQUEST_HANDLER)
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swagger2Properties.getTitle())
                .description(swagger2Properties.getDescription())
                .termsOfServiceUrl(swagger2Properties.getTermsOfServiceUrl())
                .contact(new Contact(swagger2Properties.getName(), swagger2Properties.getUrl(), swagger2Properties.getEmail()))
                .version(swagger2Properties.getVersion())
                .build();
    }
}