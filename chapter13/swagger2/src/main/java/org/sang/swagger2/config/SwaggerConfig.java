package org.sang.swagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Mr.Du
 * @date 2020/10/20 18:54
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.sang.swagger2.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .description("微人事接口测试文档")
                        .contact(new Contact("baidu","https://www.baidu.com","hello2020@gmail.com"))
                        .version("v1.0")
                        .title("API文测试")
                        .license("Apache2.0")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                        .build());
    }
}
