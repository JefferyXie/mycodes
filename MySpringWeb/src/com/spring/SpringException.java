package com.spring;

public class SpringException extends RuntimeException {
	private static final long serialVersionUID = 0;
	
	private String exceptionMsg;
	
	public SpringException(String msg) 
	{
		this.exceptionMsg = msg;
	}
	public String getExceptionMsg()
	{
		return this.exceptionMsg;
	}
	public void setExceptionMsg(String msg)
	{
		this.exceptionMsg = msg;
	}
}
