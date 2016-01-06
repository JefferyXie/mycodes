package com.myjava.basic;

public class InnerClass {
	private int arr[];
	
	public InnerClass() { System.out.println("InnerClass constructor."); }
	public void analyze()
	{
		arr = new int[] { 1, 3, 7, 2 };
		Inner obj = new Inner();
		System.out.println(obj.first() + ", " + obj.end());
		
		Inner_static o_inner_static = new Inner_static();
		Inner_static_public o_inner_static_pub = new Inner_static_public();
	}
	
	// 1), inner class can access outer class's member
	// 2), non static inner class won't be created unless outer instance is created
	class Inner
	{
		public Inner() { System.out.println("Inner constructor."); }
		public int first() { return arr[0]; }
		public int end() { return arr[arr.length-1]; }
	}
	// static class can be accessed without outer instance created
	static class Inner_static {
		final String NAME = "This is Inner_static class";
		public Inner_static() {
			System.out.println("Inner_static()");
		}
	}
	// public inner class can be accesses outside of package
	public static class Inner_static_public {
		final String NAME = "This is Inner_static_public class";
		public final String VALUE = "No value";
		public Inner_static_public() {
			System.out.println("Inner_static_public()");
		}
	}
}
