package com.aerors.oms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

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
@PropertySource(value={"classpath:config.properties"}) 
public class SwaggerConfig {

	@Value(value="${swagger.enable}") 
	private boolean enable; 

	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		 .enable(enable)
        		 .select()
                 .apis(RequestHandlerSelectors.any())
                 .paths(PathSelectors.any())
                 .build()
                 .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {       
		Contact contact = new Contact("tianw", "http://www.aerors.com", "tianw@spacestar.com.cn");
		return new ApiInfoBuilder()
				.title("接口列表 v1.1.0")
				.description("接口测试")
				.termsOfServiceUrl("http://localhost:8080/swagger-ui.html")
				.contact(contact)
				.version("1.1.0")
				.build();
	}

	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations(
				"classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/");
	}
}
