package com.myjava.practice.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

@Configuration
public class JavaBeanConfig_Event {

	@Bean
	public MyStartEventHandler startEventHandler()
	{
		return new MyStartEventHandler();
	}
	@Bean
	public MyStopEventHandler stopEventHandler()
	{
		return new MyStopEventHandler();
	}
	
	@Bean
	public CustomEventHandler customEventHandler()
	{
		return new CustomEventHandler();
	}
	@Bean
	public CustomEventPublisher customEventPublisher()
	{
		return new CustomEventPublisher();
	}


	// implement the event listener for Spring default event
	public class MyStartEventHandler implements ApplicationListener<ContextStartedEvent>
	{
		@Override
		public void onApplicationEvent(ContextStartedEvent arg0) {
			System.out.println("MyStartEventHandler.onApplicationEvent.");
		}
	}
	// implement the event listener for Spring default event
	public class MyStopEventHandler implements ApplicationListener<ContextStoppedEvent>
	{
		@Override
		public void onApplicationEvent(ContextStoppedEvent arg0) {
			System.out.println("MyStopEventHandler.onApplicationEvent.");
		}
	}
	// declare a custom event class: event object should be data structure for storage
	public class CustomEvent extends ApplicationEvent
	{
		public CustomEvent(Object source) {
			super(source);
		}
		public String toString()
		{
			return "MY CustomEvent message.";
		}
	}
	// implement the event listener: handler is to handle biz logic when event is triggered
	public class CustomEventHandler implements ApplicationListener<CustomEvent>
	{
		@Override
		public void onApplicationEvent(CustomEvent event) {
			System.out.println(event.toString());
		}
	}
	// implement event publisher to publish event(s)
	public class CustomEventPublisher implements ApplicationEventPublisherAware  
	{
		private ApplicationEventPublisher publisher;
        public void setApplicationEventPublisher(ApplicationEventPublisher pub)
        {
        	this.publisher = pub;
        }
        public void publish()
        {
        	this.publisher.publishEvent(new CustomEvent(this));
        }
	}
}
