package com.myjava.bootservice.app;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// http://kamwo.me/java-spring-boot-mvc-ontroller-not-called/
// @SpringBootApplication by default includes @ComponentScan but it only scans controller
// in current package, so we replace it with more specified annotations
//@SpringBootApplication

@Configuration
@ComponentScan(basePackages="com.myjava")
@EnableAutoConfiguration
public class BootApplication {

	public static void main(String[] args) {
        //System.setProperty("server.port", "8080");
        System.setProperty("server.port", "38080");
        ApplicationContext ctx = SpringApplication.run(BootApplication.class, args);
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
        	System.out.println(beanName);
        }
    }
}
