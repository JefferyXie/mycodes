package com.myjava.basic;

public class StaticBlock {
	public static double rootOf2;
	public static double rootOf3;
	
	static {
		System.out.println("Inside static block");
		rootOf2 = Math.sqrt(2);
		rootOf3 = Math.sqrt(3);
	}

	public StaticBlock(String msg)
	{
		System.out.println(msg);
	}
}
