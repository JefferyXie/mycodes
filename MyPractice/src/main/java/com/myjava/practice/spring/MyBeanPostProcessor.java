package com.myjava.practice.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		//System.out.println("MyBeanPostProcessor.postProcessAfterInit: " + beanName);
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		//System.out.println("MyBeanPostProcessor.postProcessBeforeInit: " + beanName);
		return bean;
	}
}
