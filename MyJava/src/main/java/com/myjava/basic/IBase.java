package com.myjava.basic;

// without "public" the interface is invisible outside of package
public interface IBase {

	// interface can contain variables which are implicitly public, final and static
	int MIN = 0;
	int MAX = 10000;
	int[] DICE = { 1, 2, 3, 4, 5, 6, };
	
	// method is implicitly public
	int getValue();
	
	// static method can be called by IBase.xxx without any instance created
	static void runIndependently() {
		System.out.println("IBase.runIndependently is static method");
	}
	
	// default method that doesn't have to be implemented by subclass
	default String getInitString() {
		System.out.println("IBase.getInitString().");
		return "IBase.getInitString()";
	}
}
