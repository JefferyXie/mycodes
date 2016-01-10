package com.myjava.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class NumericFns <T extends Number> {
	T num;
	NumericFns(T n) {
		num = n;
	}
	// use wildcard argument NumericFns<?> for target object
	boolean absEqual(NumericFns<?> obj) {
		return Math.abs(obj.num.doubleValue()) == Math.abs(num.doubleValue());
	}

	static <V extends Comparable<V>, W extends V> boolean Fun_GenericStaticEqual(V[] x, W[] y) {
		if (x.length != y.length) return false;
		for (int i = 0; i < x.length; ++i) {
			if (!x[i].equals(y[i])) return false;
		}
		return true;
	}
	
	<V extends Collection<?>> void Fun_GenericCollection(V x) {
		Iterator<?> it = x.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	static void runNumericFns() {
		System.out.println("runNumericFns()");
		
		NumericFns<Integer> iObj = new NumericFns<>(14);
		NumericFns<Double> dbObj = new NumericFns<>(11.01);
		NumericFns<Float> fObj = new NumericFns<>(-14f);
		
		if (iObj.absEqual(dbObj))
			System.out.println("iObj absEqual dbObj");
		if (iObj.absEqual(fObj))
			System.out.println("iObj absEqual fObj");
	}
	
	static void runGenericStaticEqual() {
		System.out.println("runGenericStaticEqual()");
		
		Double[] arr1 = { 1.1, 3.22 };
		Double[] arr2 = { 1.1, 3.22 };
		Double[] arr3 = { 1.0, 5.00 };
		if (Fun_GenericStaticEqual(arr1, arr2))
			System.out.println("arr1 equals arr2");
		else
			System.out.println("arr1 doesn't equal arr2");
		if (Fun_GenericStaticEqual(arr1, arr3))
			System.out.println("arr1 equals arr3");
		else
			System.out.println("arr1 doesn't equal arr3");
	}
	
	static void runGenericCollection() {
		System.out.println("runGenericCollection()");
		
		Integer[] arr = { 10, 4, 2, 5 };
		//ArrayList<?> arrList = new ArrayList<>(Arrays.asList(arr));
		List<Integer> list = Arrays.asList(arr);
		NumericFns<?> obj = new NumericFns<Number>(1);
		obj.Fun_GenericCollection(list);
	}
}
