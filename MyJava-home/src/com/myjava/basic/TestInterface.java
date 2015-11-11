package com.myjava.basic;

class TestInterface implements IChild, ITestable {

	public void outputVars() {
		System.out.println("MAX: " + IChild.MAX + ", MIN: " + IBase.MIN);
	}
	
	public int getValue() {
		System.out.println("TestInterface.getValue().");
		return 10;
	}

	public void review() {
		System.out.println("TestInterface.review().");
		getInitString();
		IChild.super.getInitString(); // call IChild.getInitString(), no way to call IBase.getInitString which violates encapsulation
	}

	public boolean test(String s) {
		return true;
	}
	
	public String getInitString() {
		System.out.println("TestInterface.getInitString().");
		return "TestInterface.getInitString()";
	}
	
	static void runInterface() {
		TestInterface test = new TestInterface();
		test.outputVars();
		test.getValue();
		test.review();
		test.getInitString();
		IBase.runIndependently(); // call static method from interface
	}
}
