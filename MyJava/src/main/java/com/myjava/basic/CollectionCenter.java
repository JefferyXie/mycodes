package com.myjava.basic;

import java.util.*;

public class CollectionCenter {
	// https://javabeanz.wordpress.com/2007/06/29/iterator-vs-enumeration/
	// Iterator:
	// 1), allow the caller to remove element during iteration by using remove();
	// 2), thread-safe, meaning other thread cannot change collection when traversing, a 
	//     exception ConcurrentModificationException will be thrown
	public static void InteratorEnumeration() {
		Vector<String> vec = new Vector<>();
		vec.add("1st");
		vec.add("2nd");
		vec.add("3rd");
		Iterator<?> itr = vec.iterator();
		Enumeration<?> enu = vec.elements();
		try {
			int i = 0;
			while (itr.hasNext()) {
				if (i++ > 1) {
					//vec.remove(1); // throw ConcurrentModificationException
					itr.remove(); // safe
				}
				System.out.println(itr.next());
			}
			
			while (enu.hasMoreElements()) {
				vec.remove(1); // safe
				System.out.println(enu.nextElement());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
