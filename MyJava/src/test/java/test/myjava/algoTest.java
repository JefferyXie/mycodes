package test.myjava;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.function.Function;

import org.junit.*;

import com.myjava.algo.*;

public class algoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//System.out.println("[setUpBeforeClass]");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//System.out.println("[tearDownAfterClass]");
	}

	@Before
	public void setUp() throws Exception {
		//System.out.println("setUp");
	}

	@After
	public void tearDown() throws Exception {
		//System.out.println("tearDown");
	}

	@Test
	public void testQuickSortIntArray() {
		//fail("Not yet implemented"); 
		
		int[] src = {1, 2, 2, -3, 6, 3, 0, 1, -5};
		int[] target = {-5, -3, 0, 1, 1, 2, 2, 3, 6};
		MySort.QuickSort(src);
		assertArrayEquals(target, src);
	}
	
	@Test
	public void testFindContinuousSequence() {
		StringOperation.PossiblePermutation("ab".toCharArray());
		StringOperation.PossiblePermutation("abcd".toCharArray());
		StringOperation.PossiblePermutation("abcdef".toCharArray());
		NumOperation.FindContinuousSequence(25);
		NumOperation.FindContinuousSequence(30);
		NumOperation.FindContinuousSequence(100);
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
	
	@Test
	public void testInt2Binary() {
		BitUtil.int2Binary(10);
		BitUtil.int2Binary_v2(10);
		System.out.println("\t" + Integer.toBinaryString(10));
		BitUtil.int2Binary(100);
		BitUtil.int2Binary_v2(100);
		System.out.println("\t" + Integer.toBinaryString(100));
		BitUtil.int2Binary(213244);
		BitUtil.int2Binary_v2(213244);
		System.out.println("\t" + Integer.toBinaryString(213244));
		BitUtil.int2Binary(-1);
		BitUtil.int2Binary_v2(-1);
		System.out.println("\t" + Integer.toBinaryString(-1));
		BitUtil.int2Binary(-100);
		BitUtil.int2Binary_v2(-100);
		System.out.println("\t" + Integer.toBinaryString(-100));
		BitUtil.int2Binary(-232412);
		BitUtil.int2Binary_v2(-232412);
		System.out.println("\t" + Integer.toBinaryString(-232412));
	}
	
	@Test
	public void testFindMaxContinuousSum() {
		NumOperation.FindMaxContinuousSum(new int[] {0});
		NumOperation.FindMaxContinuousSum(new int[] {2});
		NumOperation.FindMaxContinuousSum(new int[] {1,-1});
		NumOperation.FindMaxContinuousSum(new int[] {-1,-2,0,2});
		NumOperation.FindMaxContinuousSum(new int[] {-1,-2,-3,-4});
		NumOperation.FindMaxContinuousSum(new int[] {-1,-2,-3,1});
		NumOperation.FindMaxContinuousSum(new int[] {1,1,-1,-2,-3,5});
		NumOperation.FindMaxContinuousSum(new int[] {-1,-2,-3,1,2,-5,-2,3});
		NumOperation.FindMaxContinuousSum(new int[] {0,1,2,3,4,5,6});
		NumOperation.FindMaxContinuousSum(new int[] {6,5,4,3,2,1,0});
		NumOperation.FindMaxContinuousSum(new int[] {-3,-1,2,3,1,-1,0,4,-10});
		NumOperation.FindMaxContinuousSum(new int[] {8,0,5,-2,-6,1,5,1,-2,5});
		NumOperation.FindMaxContinuousSum(new int[] {3,4,2,1,2,1,0,-1,-4,3,1});
		
		Function<Integer, Object> genIntArray = len -> {
			int[] arr = new int[len];
			Random rnd = new Random();
			for (int i = 0; i < len; ++i) {
				arr[i] = rnd.nextInt(100) * (rnd.nextBoolean() ? 1 : -1);
			}
			return arr;
		};
		NumOperation.FindMaxContinuousSum((int[])genIntArray.apply(5));
		NumOperation.FindMaxContinuousSum((int[])genIntArray.apply(5));
		NumOperation.FindMaxContinuousSum((int[])genIntArray.apply(10));
		NumOperation.FindMaxContinuousSum((int[])genIntArray.apply(10));
	}
	
	@Test
	public void testSumUp2Target() {
		NumOperation.SumUp2Target(new int[] {1, 2, 2, 3, 4, 5}, 5);
		NumOperation.SumUp2Target(new int[] {1, 3, 4, 5, 6, 15}, 15);
		NumOperation.SumUp2Target(new int[] {1, 3, 4, 5, 6, 2, 7, 8, 9, 10, 11, 13, 14, 15}, 15);
		NumOperation.SumUp2Target(new int[] {-4, -2, 1, 3, 2, 5, 6, 10}, 10);
	}
	
	@Test
	public void testSubSmallest() {
		NumOperation.SubSmallest(new int[] {1, 2, 2, 3, 4, 5});
		NumOperation.SubSmallest(new int[] {8, 3, -4, 5, 1, 15});
		NumOperation.SubSmallest(new int[] {-4, -2, 5, 3, 2, 5, 9, 7});
		NumOperation.SubSmallest(new int[] {-4, -2, -1, -3, 2, 5, 4, 10});
	}
	
	@Test
	public void testPossiblePermutation() {
		NumOperation.PossiblePermutation(1, 2, 3);
		NumOperation.PossiblePermutation(1, 2, 5);
		NumOperation.PossiblePermutation(2, 3, 5);
		NumOperation.PossiblePermutation(1, 2, 6);
		NumOperation.PossiblePermutation(3, 4, 7);
		NumOperation.PossiblePermutation(2, 4, 8);
		NumOperation.PossiblePermutation(3, 5, 9);
	}
	
	@Test
	public void testCountRepeatedTimes() {
		NumOperation.CountRepeatedTimes(new int[] {1, 2, 2, 3, 4, 5});
		NumOperation.CountRepeatedTimes(new int[] {1, 3, 4, 5, 6, 15});
		NumOperation.CountRepeatedTimes(new int[] {1, 3, 4, 5, 6, 2, 7, 8, 9, 10, 11, 13, 14, 15});
		NumOperation.CountRepeatedTimes(new int[] {8, 2, 2, 3, 4, 3});
		NumOperation.CountRepeatedTimes(new int[] {5, 2, 1, 3, 4, 5, 1, 3, 8, 6, 7, 8, 9});
		NumOperation.CountRepeatedTimes(new int[] {8, -2, -2, 3, 4, 3, -5, -1, -5});
		NumOperation.CountRepeatedTimes(new int[] {-6, 8, 2, 2, -3, 4, -3, 6});
	}

	@Test
	public void testFindDuplicateNumber() {
		NumOperation.FindDuplicateNumber(new int[] {1, 2, 3, 3, 4, 5, 6});
		NumOperation.FindDuplicateNumber(new int[] {7, 2, 3, 5, 4, 3, 6, 1});
	}

	@Test
	public void testFindKDiffPairs() {
		NumOperation.FindKDiffPairs(new int[] {1, 2, 2, 3, 4, 5}, 2);
		NumOperation.FindKDiffPairs(new int[] {-1, -3, 0, 5, 2, -2, 3, 6}, 5);
		NumOperation.FindKDiffPairs(new int[] {-1, -3, 0, 5, 2, -2, 3, 6}, -5);
		NumOperation.FindKDiffPairs(new int[] {1, 3, 4, 5, 6, 2, 7, 8, 9, 10, 11, 13, 14, 15}, 5);
		NumOperation.FindKDiffPairs(new int[] {8, 2, -2, 2, -3, 0, 4, 3}, 2);
		NumOperation.FindKDiffPairs(new int[] {8, 2, -2, 2, -3, 0, 4, 3}, -2);
		NumOperation.FindKDiffPairs(new int[] {5, 2, 1, 3, 4, 5, 1, 3, 8, 6, 7, 8, 9}, 10);
	}

	@Test
	public void testCountPerfectSquare() {
		NumOperation.CountPerfectSquare(1, 9);
		NumOperation.CountPerfectSquare(3, 13);
		NumOperation.CountPerfectSquare(0, 20);
		NumOperation.CountPerfectSquare(4, 16);
		NumOperation.CountPerfectSquare(14, 100);
	}

	@Test
	public void testPossiblePermutation_N() {
		NumOperation.PossiblePermutation(new int[] {1, 2, 3}, 2);
		NumOperation.PossiblePermutation(new int[] {1, 2, 3}, 3);
		NumOperation.PossiblePermutation(new int[] {1, 2, 3, 4, 5}, 3);
		NumOperation.PossiblePermutation(new int[] {1, 2, 2, 3, 4, 5}, 3);
		NumOperation.PossiblePermutation(new int[] {8, 3, -4, 5, 1, 15}, 4);
		NumOperation.PossiblePermutation(new int[] {-4, -2, 5, 3, 2, 5, 9, 7}, 5);
		NumOperation.PossiblePermutation(new int[] {-4, -2, -1, -3, 2, 5, 4, 10}, 6);
	}

	@Test
	public void testGreatestCommonDivisor() {
		NumOperation.GreatestCommonDivisor(new int[] {2,3,4});
		NumOperation.GreatestCommonDivisor(new int[] {3,6,12});
		NumOperation.GreatestCommonDivisor(new int[] {6,8,4,18,90});
		NumOperation.GreatestCommonDivisor(new int[] {12,18,36,180,90});
		NumOperation.GreatestCommonDivisor(new int[] {12,123,111,9,24});
	}

	@Test
	public void testLeastCommonMultiple() {
		NumOperation.LeastCommonMultiple(new int[] {2,3,4});
		NumOperation.LeastCommonMultiple(new int[] {3,4,5,6});
		NumOperation.LeastCommonMultiple(new int[] {6,8,4,3,7,9});
		NumOperation.LeastCommonMultiple(new int[] {5,7,11,8,9,13});
	}

	@Test
	public void testCaterpillarLeaves() {
		NumOperation.CaterpillarLeaves(new int[] {2,3,4}, 10);
		NumOperation.CaterpillarLeaves(new int[] {3,4,5,6}, 10);
		NumOperation.CaterpillarLeaves(new int[] {6,8,4,3,7,9}, 20);
		NumOperation.CaterpillarLeaves(new int[] {6,8,4,3,7,9}, 30);
		NumOperation.CaterpillarLeaves(new int[] {6,8,4,3,7,9}, 50);
		NumOperation.CaterpillarLeaves(new int[] {5,7,11,8,9,13}, 100);
	}

	@Test
	public void testFindUniqueChars() {
		StringOperation.FindUniqueChars("this is a fox".toCharArray());
		StringOperation.FindUniqueChars("FindUniqueChars".toCharArray());
		StringOperation.FindUniqueChars("Java testing program".toCharArray());
		StringOperation.FindUniqueChars("stringoperation".toCharArray());
	}
	
	@Test
	public void testPossiblePermutation_string() {
		StringOperation.PossiblePermutation("a".toCharArray());
		StringOperation.PossiblePermutation("abc".toCharArray());
		StringOperation.PossiblePermutation("abcde".toCharArray());
		StringOperation.PossiblePermutation("abc de".toCharArray());
		// this version doesn't yet support since multiple blank space " " are included
		StringOperation.PossiblePermutation("ab cd e".toCharArray()); 
	}
	
	@Test
	public void testCheckBraces() {
		StringOperation.CheckBraces("[{}]()[{{()}}()]".toCharArray());
		StringOperation.CheckBraces("[{}]({{()}}()]".toCharArray());
		StringOperation.CheckBraces("[{abc}] xx m ()123[{{( what?)}}()]".toCharArray());
	}
}
