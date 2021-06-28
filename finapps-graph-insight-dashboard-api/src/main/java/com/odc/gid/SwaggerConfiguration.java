package com.odc.gid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	private String confluenceLinkRevShare = "To be updated";

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.odc.gid"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		// TODO Auto-generated method stub
		ApiInfo metaInfo = new ApiInfo("Disney Graph Insight Tool", 
				"API description for Disney Graph Insight Tool", 
				"0.0.1-SNAPSHOT", 
				"N/A",
				new Contact("Finapps Team-ODC Blr", confluenceLinkRevShare, "dev.priya.v.varshney@oracle.com"), 
				"N/A",
				"N/A");
		return metaInfo;
	}

}

