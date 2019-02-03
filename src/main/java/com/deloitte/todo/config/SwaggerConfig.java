package com.deloitte.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger UI Configuration - Requires user to be logged in
 * Swagger URL: http://localhost:8008/DeloitteTodo/swagger-ui.html
 * 
 * @author Alan Kavanagh
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public static Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.deloitte.todo.web"))
				.paths(PathSelectors.any()).build().pathMapping("/").apiInfo(metadata());
	}

	private static ApiInfo metadata() {
		return new ApiInfoBuilder().title("Deloitte TODO API")
				.description("REST API for Deloitte TODO Application").version("1.0").build();
	}
}