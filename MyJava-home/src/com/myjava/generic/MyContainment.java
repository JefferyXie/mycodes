package com.myjava.generic;

public class MyContainment<T extends Number> implements Containment<T> {
	T[] arrayRef;
	public MyContainment(T[] o) {
		arrayRef = o;
	}
	@Override
	public boolean contains(T o) {
		for (T x : arrayRef)
			if (x.equals(o)) return true;
		return false;
	}
	
	static void runContainment() {
		System.out.println("runContainment()");
		
		Integer arr[] = { 3, 5, 1, 8 };
		MyContainment<Integer> obj = new MyContainment<>(arr);
		if (obj.contains(1))
			System.out.println("1 is in obj.");
		else
			System.out.println("1 is not in obj.");
		if (obj.contains(10))
			System.out.println("10 is in obj.");
		else
			System.out.println("10 is not in obj.");
	}
}
