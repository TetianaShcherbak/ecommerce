package com.example.ecommerce.config.documentation;


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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.ecommerce"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("ecommerccontact", "http://ecommerce.com", "ecommerccontact@ecommerce.com");
        return new ApiInfoBuilder()
                .title("E-commerce API")
                .description("Documentation E-commerce api")
                .version("1.0.0")
                .license("MIT")
                .licenseUrl("https://github.com/TetianaShcherbak/ecommerce/blob/main/LICENSE")
                .contact(contact)
                .build();
    }
}
