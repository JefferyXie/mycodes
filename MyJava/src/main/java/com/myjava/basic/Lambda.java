package com.myjava.basic;

import java.util.Random;
import java.util.function.*;

public class Lambda {
	static void run() {
		/*() -> 10.1;
		(n) -> Math.random() * n;
		n -> Math.random() * n;
		(double n) -> Math.random() * n;*/
		
		// functional interface should have only ONE non-default, non-static method
		IBase baseLambda = () -> 20;
		System.out.println("Functional interface of IBase.getValue(): " + baseLambda.getValue());
		
		ITestable bodyLamda = (s) -> {
			s += " -> after lambda call!";
			return true;
		};
		System.out.println("Try body lambda with ITestable.test(String): " + bodyLamda.test("hello"));
		
		LambdaGeneric<Double> genLambdaDb = (m, n) -> m/n == 1;
		LambdaGeneric<String> genLambdaStr = (s1, s2) -> {
			String ss = s1 + " " + s2;
			System.out.println(ss);
			return true;
		};
		genLambdaDb.goTest(10.0, 5.0);
		genLambdaStr.goTest("hello", "world");
		
		Lambda lamb = new Lambda();
		lamb.callLambdaArgument((n1, n2) -> {
			return n1 == n2;
		}, 10, "test lambda with argument");
		
		lamb.tryVarCapture();
	}

	static int getIntMethod() {
		return new Random().nextInt(100);
	}
	static boolean getStrMethod(String s1, String s2) {
		return s1.equals(s2);
	}
	static <T, V> boolean getGenericMethod(T t, V v) {
		return t.equals(v);
	}
	static int getLambdaAsPara(IBase para) {
		return para.getValue();
	}
	static void runMethodRef() {
		IBase lm1 = Lambda::getIntMethod;
		lm1.getValue();
		getLambdaAsPara(Lambda::getIntMethod);
		
		LambdaGeneric<String> lm2 = Lambda::getStrMethod;
		lm2.goTest("hello", "world");
		
		LambdaGeneric<PolymChild> lm3 = Lambda::getGenericMethod;
		lm3.goTest(new PolymChild(), new PolymChild());
		
		/*
		 * JDK8 adds a new package called java.util.function that provides several predefined functional interfaces
		 */
		// apply a unary operation to an object of type T and return the result, which is also type T
		// its method is called apply().
		UnaryOperator<Integer> uO = n -> ++n;
		uO.apply(10);
		
		// apply an operation to two objects of type T and return the result, which is also type T
		// its method is called apply().
		BinaryOperator<String> bO = (s1, s2) -> s1 + s2;
		bO.apply("hello", "world");
		
		// apply an operation on an object of type T
		// its method is called accept().
		Consumer<PolymChild> consumer = ob -> {
			ob.DoJob();
			ob.name = "consumer";
		};
		PolymChild obj1 = new PolymChild();
		consumer.accept(obj1);
		
		// return an object of type T
		// its method is called get().
		Supplier<PolymChild> supplier = () -> {
			PolymChild temp = new PolymChild();
			temp.name = "supplier";
			return temp;
		};
		PolymChild obj2 = supplier.get();
		
		// apply an operation to an object of type T and return the result as an object of type R.
		// its method is called apply().
		Function<String, Integer> func = s -> {
			try {
				return Integer.parseInt(s);
			} catch (Exception e) { }
			return null;
		};
		func.apply("100");
		
		// determine if an object of type T fulfills some constraint. returns a boolean that 
		// indicates the outcommes
		// its method is called test().
		Predicate<Double> pred = d -> d > 10;
		pred.test(new Random().nextDouble());
	}

	int value = 1;
	
	<T> boolean callLambdaArgument(LambdaGeneric<T> lam, T m, String str) {
		System.out.println("lambdaArgument(LambdaGeneric<T>, T, String): " + str);
		return lam.goTest(m, m);
	}
	void tryVarCapture() {
		IBase lam1 = () -> {
			value += 1; // this.value
			return 1;
		};
		int lvalue = 1;
		IBase lam2 = () -> {
			int t = lvalue + 1; // can read local variable
			//lvalue += 1; // cannot change local variable
			return 1;
		};
		//lvalue = 12; // cannot change local variable since above lambda accesses it already
		LambdaGeneric<Lambda> lam3 = (t1, t2) -> {
			t1.value = t1.value + t2.value; // you can change object's content without problem
			t2 = new Lambda();
			t2.value = 10;
			return true;
		};
		Lambda[] temp = { new Lambda(), new Lambda() };
		lam3.goTest(temp[0], temp[1]);
	}
}

interface LambdaGeneric<T> {
	boolean goTest(T m, T n);
}
