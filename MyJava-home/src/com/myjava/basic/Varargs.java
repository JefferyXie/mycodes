package com.myjava.basic;

public class Varargs {
	public static void vaTest(int... args)
	{
		System.out.println("vaTest(int... args):");
		System.out.println("\t# of args: " + args.length);
		for (int arg : args)
		{
			System.out.println("\t\t" + arg);
		}
	}
	public static void vaTest(String msg, String... args)
	{
		System.out.println("vaTest(String, String...):");
		System.out.println("\t" + msg);
		System.out.println("\t# of args: " + args.length);
		for (String arg : args)
		{
			System.out.println("\t\t" + arg);
		}
	}
}
