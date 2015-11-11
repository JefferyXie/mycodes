package com.myjava.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.myjava.basic.*;

public class LangClass {
	static void getInstanceofClass() {
		try {
			// method 1): Class.forName()
			Class<?> cl = Class.forName("com.myjava.basic.PolymChild");
			System.out.println(cl.getName());
			
			// method 2): Object.getClass()
			Object obj = new PolymChild();
			Class<?> cl1 = obj.getClass();
			System.out.println(cl1.getName());
			
			// method 3): .class syntax
			Class<?> c1 = boolean.class;
			System.out.println(c1.getName());
			Class<?> c2 = PolymBase.class;
			System.out.println(c2.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void determineClassObj() {
		try {
			// Class.isInterface()
			Class<?> c1 = Class.forName("com.myjava.basic.PolymChild");
			System.out.println(c1.isInterface());
			Class<?> c2 = Class.forName("com.myjava.basic.IChild");
			System.out.println(c2.isInterface());

			// Class.isArray()
			int[] arrInt = {1, 2, 3,};
			Class<?> c3 = arrInt.getClass();
			System.out.println(c3.isArray());
			List<?> lst = new ArrayList<Integer>();
			Class<?> c4 = lst.getClass();
			System.out.println(c4.isArray());

			// Class.isPrimitive()
			Class<?> c5 = double.class;
			System.out.println(c5.isPrimitive());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void getConstructor() {
		Constructor<?>[] ctors = PolymChild.class.getConstructors();
		for (Constructor<?> ctor : ctors) {
			System.out.print(ctor + "(");
			Class<?>[] paraTypes = ctor.getParameterTypes();
			for  (Class<?> para : paraTypes) {
				System.out.print(para+", ");
			}
			System.out.println(")");
		}
	}
	
	static void getField() {
		PolymChild obj = new PolymChild("PolymChild obj", 10, 20);
		Consumer<Field[]> fun_consumer = flds -> {
				for (Field fld : flds) {
				String name = fld.getName();
				try {
					Field f = PolymChild.class.getField(name);
					Class<?> t = f.getType();
					
					Object v = f.get(obj);
					f.set(obj, v + "$");

					System.out.println(name + ", " + t + ", " + f.get(obj));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		// getFields() return public fields
		Field[] flds = PolymChild.class.getFields();
		fun_consumer.accept(flds);

		// getDeclaredFields() return both public and private fields
		Field[] dflds = PolymChild.class.getDeclaredFields();
		fun_consumer.accept(dflds);
	}
	
	static void newInstance() {
		try {
			// Class.newInstance()
			Class<?> c1 = Class.forName(PolymChild.class.getName());
			PolymChild o1 = (PolymChild)c1.newInstance();

			// Constructor.newInstance()
			// http://stackoverflow.com/questions/195321/why-is-class-newinstance-evil
			Constructor<?> ct1 = PolymChild.class.getDeclaredConstructor();
			Constructor<?> ct2 = PolymChild.class.getDeclaredConstructor(new Class[] {String.class, int.class, int.class});
			PolymChild o2 = (PolymChild)ct1.newInstance();
			PolymChild o3 = (PolymChild)ct2.newInstance("o3", 1, 2);
			
			o1.foo();
			o2.foo();
			o3.foo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void mimicJavap(String className) {
		if (null == className || className.isEmpty()) {
			className = "com.myjava.PolymChild";
		}
		try {
			Class<?> c = Class.forName(className);
			System.out.println("[Fields]:");
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {
				System.out.println(f);
			}
			
			System.out.println("[Constructors]:");
			Constructor<?>[] cons = c.getDeclaredConstructors();
			for (Constructor<?> con : cons) {
				System.out.println(con);
			}
			
			System.out.println("[Methods]:");
			Method[] ms = c.getDeclaredMethods();
			for (Method m : ms) {
				System.out.println(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
