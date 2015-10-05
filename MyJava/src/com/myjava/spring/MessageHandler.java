package com.myjava.spring;

public class MessageHandler {
	private String message1;
	private String message2;
	
	public MessageHandler()
	{
		System.out.println("MessageHandler() constructs.");
	}
	public void setMessage1(String msg)
	{
		this.message1 = msg;
	}
	public void setMessage2(String msg)
	{
		this.message2 = msg;
	}
	public String getMessage1()
	{
		System.out.println("MessageHandler.getMessage1(): " + this.message1);
		return this.message1;
	}
	public String getMessage2()
	{
		System.out.println("MessageHandler.getMessage2(): " + this.message2);
		return this.message2;
	}

}