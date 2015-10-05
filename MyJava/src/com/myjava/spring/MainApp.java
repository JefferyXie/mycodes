package com.myjava.spring;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args)
	{
/*
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaBeanConfig_Msg.class);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(JavaBeanConfig_Msg.class);
		ctx.register(JavaBeanConfig_Event.class);
		ctx.refresh();
		ctx.start();

		JavaBeanConfig_Event.CustomEventPublisher cep = 
				(JavaBeanConfig_Event.CustomEventPublisher)ctx.getBean("customEventPublisher");
		cep.publish();
		
		//SayHello sh = ctx.getBean(SayHello.class);
		//sh.handleMsg();
		SayHello sh1 = (SayHello)ctx.getBean("sayhello_initdestory_bean");
		sh1.handleMsg();
		JavaBeanConfig_Collection jc = (JavaBeanConfig_Collection)ctx.getBean("jcollection_bean");
		jc.toString();
		
		ctx.stop();
*/
		try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans-autoscan.xml"))
		{
			Customer cus = (Customer)context.getBean("customer");
			System.out.println(cus.toString());
		
			Student stu = (Student)context.getBean("student");
			System.out.println(stu.toString());

/*			SayHello obj = (SayHello)context.getBean("sayhello");
			obj.setMessage1("message set in obj 1");
			obj.getMessage1();
			SayHello obj2 = (SayHello)context.getBean("sayhello");
			obj2.getMessage1();

			// why the destroy() is not called twice even there're two instances created???
			SayHello obj_pro = (SayHello)context.getBean("sayhello-prototype");
			obj_pro.setMessage1("message for prototype obj 1");
			obj_pro.getMessage1();
			SayHello obj_pro2 = (SayHello)context.getBean("sayhello-prototype");
			obj_pro2.getMessage1();

			MessageHandler msgHandler = (MessageHandler)context.getBean("messagehandler");
			msgHandler.getMessage1();
			msgHandler.getMessage2();

			SayHello obj_inject = (SayHello)context.getBean("sayhello-injection");
			obj_inject.handleMsg();

			JavaCollection jc = (JavaCollection)context.getBean("javacollection");
			List lt = jc.getAddrList();
			SayHello o = (SayHello)lt.get(3);
			o.handleMsg();
			jc.getAddrSet();
			jc.getAddrMap();
			jc.getAddrProp();

			Map m = jc.getAddrMap();
			SayHello os = (SayHello)m.get("5");
			os.handleMsg();

			SayHello hello_annotation = (SayHello)context.getBean("sayhello-autowired");
			hello_annotation.handleMsg();

			// register a shutdown hook registerShutdownHook() method that is declared 
			// on the AbstractApplicationContext class. This will ensures a graceful shutdown 
			// and calls the relevant destroy methods.
*/			context.registerShutdownHook();
		};
	}
}
