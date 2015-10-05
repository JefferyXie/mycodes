package com.myjava.spring;

import org.springframework.beans.factory.annotation.Autowired;

public class SayHello {
	private String message1;
	@Autowired
	private MessageHandler msgHandler;
	
	public SayHello()
	{
		System.out.println("SayHello() construts.");
	}
	public void setMessage1(String msg)
	{
		this.message1 = msg;
	}
	public String getMessage1()
	{
		System.out.println("SayHello.getMessage1(): " + message1);
		return this.message1;
	}
	public void setMsgHandler(MessageHandler handler)
	{
		System.out.println("SayHello.setMsgHandler(...): " + handler.getMessage1());
		this.msgHandler = handler;
	}
	public void handleMsg()
	{
		System.out.println("SayHello.handleMsg().");
		if (null != msgHandler)
		{
			msgHandler.getMessage1();
			msgHandler.getMessage2();
		}
	}
	public void init()
	{
		System.out.println("SayHello bean is init(), message: " + this.message1);
	}
	public void destroy()
	{
		System.out.println("SayHello bean is destroy(), message: " + this.message1);
	}
}
