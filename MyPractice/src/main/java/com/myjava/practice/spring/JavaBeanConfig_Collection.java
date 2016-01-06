package com.myjava.practice.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaBeanConfig_Collection {
	@Bean
	public JavaBeanConfig_Collection jcollection_bean()
	{
		return new JavaBeanConfig_Collection();
	}
}
