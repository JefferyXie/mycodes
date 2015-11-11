package com.myjava.basic;

public class PolymChild extends PolymBase {
	private int type;
	String name = "PolymChild";
	
	public PolymChild() {
		System.out.println("PolymChild()");
	}

	public PolymChild(String nm, int v, int t) {
		super(nm, v);
		this.type = t;
		System.out.println("PolymChild(String, int)");
	}
	@Override
	public void foo()
	{
		super.foo();
		System.out.println("PolymChild.foo()");
	}

	@Override
	public void DoJob() {
		System.out.println("PolymChild.DoJob(): impletation of abstract method");
	}
}
