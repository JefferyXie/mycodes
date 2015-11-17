package com.myjava.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.*;

import com.myjava.algo.*;

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
	
	@Test
	public void testBitUtil() {
		String s = "12" ;
		short n = 100;
		byte[] buf;
		try {
			buf = s.getBytes("UTF-8");
			byte[] buf2 = BitUtil.shortToByteArray(n);
			System.out.println(BitUtil.calculateCheckSum(buf));
			System.out.println(BitUtil.calculateCheckSum(buf2));
			System.out.println(BitUtil.bytes2HexString(buf2));
			byte i = -112;
			System.out.println(i & 0xff);	
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
