package com.myjava.basic;

public abstract class PolymBase {
	// final variable is constant and can be static
	static final int SUCCESS_CODE = 1;
	static final int FAILED_CODE = 0;
	static final String SUCCESS = "success";
	static final String FAILED = "failed";
	
	static void checkStatus(final int statusCode)
	{
		// final parameter cannot be modified
		//statusCode = 0;
		switch (statusCode) {
		case SUCCESS_CODE:
			System.out.println(SUCCESS);
			break;
		case FAILED_CODE:
			System.out.println(FAILED);
			break;
		default:
			break;
		}
	}
	
	private int value;
	String name = "PolymBase";
	
	public PolymBase()
	{
		System.out.println("PolymBase()");
	}
	public PolymBase(String nm, int v)
	{
		System.out.println("PolymBase(String, int)");
		this.name = nm;
		this.value = v;
	}
	protected void foo()
	{
		System.out.println("PolymBase.foo()");
	}
	
	// child class must implement abstract method(s)
	public abstract void DoJob();
}
