package com.myjava.basic;

import com.myjava.algo.*;

public class RunApp {

	public static void main(String[] args) {
		//runClass();
		//TestInterface.runInterface();
		//Transport.runEnum();
		Lambda.run();
	}
	
	static void runFunc()
	{
		System.out.println(StaticBlock.rootOf2 + "," + StaticBlock.rootOf3);
		StaticBlock ob = new StaticBlock("Inside constructor");
		System.out.println(ob);
		
		InnerClass ic = new InnerClass();
		ic.analyze();

		Varargs.vaTest(1, 2, 4);
		Varargs.vaTest(new int[] {1, 2, 5});
		Varargs.vaTest();
		Varargs.vaTest("test string");
		Varargs.vaTest("test string2", "first", "tom", "John");
		
		int arr[][] = {
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9, },
				{ 2, 1, 5, 3, 4, 4, 4, 9, 10 },
				{ 5, 5, 4, 2, 7, 11, 23, 20, 7, 6 },
				{ 5, 5, 4, 2, 7, 11, 23, 20, 7, 6, 1 },
				{ 5, 5, 4, 2, 7, 11, 23, 7, 6 },
		};
		for (int[] ar : arr)
		{
			Sort.QuickSort(ar);
			for (int v : ar)
			{
				System.out.print(v + ",");
			}
			System.out.println();
		}
	}
	static void runClass()
	{
		PolymBase obj1 = new PolymChild();
		PolymBase obj2 = new PolymChild("obj2", 10, 2);
		PolymChild obj3 = new PolymChild();
		System.out.println("obj1.name: " + obj1.name + ";((PolymChild)obj1).name: " + ((PolymChild)obj1).name);
		System.out.println("obj2.name: " + obj2.name);
		System.out.println("obj3.name: " + obj3.name);
		
		System.out.println("obj1.getClass(): " + obj1.getClass());
		System.out.println("obj3.getClass(): " + obj3.getClass());
		
		System.out.println("package: " + obj1.getClass().getPackage() + ";name: " + obj1.getClass().getName());
		try {
			PolymChild oo = (PolymChild)obj1.getClass().newInstance();
			oo.foo();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
