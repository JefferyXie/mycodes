package com.myjava.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

// annotation @Configuration works as Beans.xml file, each @Bean declares a bean
// which is same as <bean/> node inside Beans.xml file.
// Typical use is creating a config class (AppConfig) with @Configuration and including
// a series of methods with @Bean for each to create the corresponding bean object.
// If no config class is necessary, you can use "auto scan component", saying, add @Component
// annotation to the bean class. And add <context:component-scan base-package="***" /> to the xml file.
@Configuration
@Import(JavaBeanConfig_Collection.class)
public class JavaBeanConfig_Msg {
	
	@Bean
	public SayHello sayhello_bean()
	{
		return new SayHello();
	}
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public SayHello sayhello_initdestory_bean()
	{
		return new SayHello();
	}
	@Bean
	@Scope("prototype")
	public SayHello sayhello_prototype_bean()
	{
		return new SayHello();
	}
	@Bean
	public MessageHandler msghandler_bean()
	{
		return new MessageHandler();
	}
}
