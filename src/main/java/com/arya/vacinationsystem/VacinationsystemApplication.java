package com.arya.vacinationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableSwagger2
public class VacinationsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacinationsystemApplication.class, args);
	}
//	@Bean
//	public Docket productApi() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.arya.vacinationsystem.controller")).build();
//	}
}
