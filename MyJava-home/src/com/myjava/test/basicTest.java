package com.myjava.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myjava.basic.CollectionCenter;
import com.myjava.basic.InnerClass;

public class basicTest {

	@Test
	public void testAnalyze() {
		InnerClass inner = new InnerClass();
		inner.analyze();
		InnerClass.Inner_static_public inner_static_pub = new InnerClass.Inner_static_public();
		// cannot access the non-public fields
		System.out.print(inner_static_pub.VALUE);
	}
	
	@Test
	public void testCollections() {
		CollectionCenter.InteratorEnumeration();
	}
}
