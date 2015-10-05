package com.myjava.bootservice.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class SwaggerConfig {
	private SpringSwaggerConfig springSwaggerCfg;
	
	@Autowired
	public void setSpringSwaggerCfg(SpringSwaggerConfig cfg) {
		this.springSwaggerCfg = cfg;
	}
	
	private ApiInfo getApiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"My Boot API", 
				"API for Test Boot", 
				"Xie API tems of service", 
				"jeffery.xie@morningstar.com",
				"Xie API License Type", 
				"Xie API License URL");
		return apiInfo;
	}
	
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(springSwaggerCfg).apiInfo(getApiInfo())
					.includePatterns("/.*");
	}

	@Bean
	public SwaggerSpringMvcPlugin APIConfiguration() {
		SwaggerSpringMvcPlugin swaggerPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerCfg);
		swaggerPlugin.apiInfo(getApiInfo()).apiVersion("1.0").includePatterns("/v1/*.*").swaggerGroup("v1");
		swaggerPlugin.useDefaultResponseMessages(false);
		return swaggerPlugin;
	}
}

