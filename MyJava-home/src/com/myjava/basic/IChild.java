package com.myjava.basic;

// interface extends another base interface
public interface IChild extends IBase {

	void review();
	
	// overwrite parent interface's default method
	default String getInitString() {
		System.out.println("IChild.getInitString().");
		return "IChild.getInitString()";
	}
}
