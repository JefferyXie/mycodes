package com.myjava.test;

import static org.junit.Assert.*;
import org.junit.*;

import com.myjava.algo.NumOperation;
import com.myjava.algo.Sort;

public class algoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("[setUpBeforeClass]");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("[tearDownAfterClass]");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}

	@Test
	public void testQuickSortIntArray() {
		//fail("Not yet implemented"); 
		
		int[] src = {1, 2, 2, -3, 6, 3, 0, 1, -5};
		int[] target = {-5, -3, 0, 1, 1, 2, 2, 3, 6};
		Sort.QuickSort(src);
		assertArrayEquals(target, src);
	}
	
	@Test
	public void testNumOperation() {
		assertTrue(NumOperation.FindContinuousSequence(25));
	}
}
