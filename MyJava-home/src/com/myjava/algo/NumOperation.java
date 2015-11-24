package com.myjava.algo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NumOperation {

	private static void Print(int i, int j)
	{
		for(int m = i; m <= j; m++)
		{
			System.out.print(m + " ");
		}
		System.out.println();
	}

	// 鍜屼负sum鐨勮繛缁鏁存暟搴忓垪
	// http://blog.csdn.net/mafuli007/article/details/8558657
	public static boolean FindContinuousSequence(int sum)
	{
		if (sum < 3 ) return false;

		int i = sum / 2;
		int j = i + 1;

		int tmpsum = i + j;
		while(i > 0) {
			if( tmpsum  == sum) {
				Print(i, j);
				j--;
				i = j-1;

				tmpsum = i + j;
			} else if(tmpsum < sum) {
				i--;
				tmpsum += i;
			} else {
				tmpsum -= j;
				j--;
			}
		}
		return true;
	}

	// http://www.geeksforgeeks.org/maximum-difference-between-two-elements/
	// http://code.geeksforgeeks.org/ag4AqF
	public static int FindMaxProfit(int[] arr) {
		int maxProfit = Integer.MIN_VALUE;
		int minNumber = arr[0];
		for (int i = 1; i < arr.length; ++i) {
			maxProfit = Math.max(maxProfit, arr[i] - minNumber);
			minNumber = Math.min(minNumber, arr[i]);
		}
		return maxProfit;
	}

	// 姹備袱涓笁浣嶆暟涔樼Н鐨勬渶澶у洖鏂囨暟
	// 鍥炴枃鏁颁粠涓よ竟璇诲�兼槸涓�鏍风殑銆備袱涓袱浣嶆暟涔樼Н鐨勬渶澶у洖鏂囨暟鏄�9009锛�99*99锛屾眰涓や釜涓変綅鏁颁箻绉殑鏈�澶у洖鏂囨暟锛�	
	public static long FindPalindromicNumber() {
		Long max = 0L;
		for (long i = 999L; i >= 100; i--) {
			// 濡傛灉鏌愪釜鍥炴枃鏁�888888锛�924*962锛岄偅涔堜篃鏈夊彲鑳芥槸962*924锛屼袱娆¤幏寰楁煇涓洖鏂囨暟锛�
			// 濡傛灉鍒ゆ柇j>i, 搴旇鍙互鍥為伩姝ら棶棰橈紝鍑忓皯灏嗚繎涓�鍗婄殑鏁板�艰绠�
			for (long j = 999L; j >= i; j--) {
				if (IsPalindromicNumber(i * j)) {
					if (i * j > max) {
						max = i * j;
					}
				}
			}
		}
		return max;
	}
	public static boolean IsPalindromicNumber(long n) {
		StringBuffer s = new StringBuffer(n + "");
		s.reverse();
		return Long.parseLong(s.toString()) == n;
	}
	
	// find a number that appears once in array while others are duplicated
	// http://www.codinghelmet.com/?path=exercises/number-appearing-once-in-array
	public static int FindSingularFromDuplicates(int[] arr) {
		int result = 0;
		for (int i = 0; i < arr.length; ++i) {
			result ^= arr[i];
		}
		return result;
	}
	
	// find a number that appears once in array while others occur three times
	// http://www.geeksforgeeks.org/find-the-element-that-appears-once/
	public static int FindSingularFromTriple(int[] arr) {
		final int NUM_INT_BITS = 32;
		int result = 0;
		int x, sum;
		// iterate through every bit
		for (int i = 0; i < NUM_INT_BITS; ++i) {
			// find sum of set bits at i'th position in all array elements
			sum = 0;
			x = (1 << i);
			for (int j = 0; j < arr.length; ++j) {
				if ((arr[j] & x) != 0)
					++sum;
			}
			// the bits with sum not multiple of 3, are the bits
			// of element with single occurrence
			if (sum % 3 != 0)
				result |= x;
		}
		return result;
	}
	
	// Given a function GenerateUni5 which equally generates 0,1,2,3,4
	// Define a function that equally generates [0,8] by only using GenerateUni5()
	// 灏辨槸缁欎竴涓�25涓�肩殑绛夋鐜囩┖闂达紝鍓�24涓�肩殑璇濓紝姣�3涓�煎垎閰嶄竴涓繑鍥炲��
	// 濡傛灉涓嶅湪杩欎釜鑼冨洿锛坢agic绛変簬24浜嗭級锛屽垯閲嶅仛涓�閬嶃��
	// 杩欐槸涓敱澶氫釜Monte-Carlo绠楁硶鍙犲姞鐨凩as-Vegas绠楁硶銆傚崟涓狹onte-Carlo
	// 涓峟ail鐨勬鐜囦负24/25銆傛墍浠as-Vegas绠楁硶鎵ц鐨勬湡鏈涙鏁颁负25/24
	// 鎵�浠ヨ繖鏄竴涓狾(1)鏈熸湜鏃堕棿鐨勬鐜囩畻娉�
	public static int GenerateUni8() {
		int v = 5 * GenerateUni5() + GenerateUni5();
		if (v != 24) 
			return v % 8;
		return GenerateUni8();
	}
	// can be upgraded to lambda
	private static int GenerateUni5() {
		return new Random().nextInt(5);
	}
<<<<<<< HEAD
=======

	// http://stackoverflow.com/questions/10690843/finding-the-list-of-prime-numbers-in-shortest-time
	// Let n be a composite number (i.e. a number greter than 1 that has divisors other than 1 and n), and let d be a divisor of n.
	// Now, d being a divisor of n means that n/d is an integer, and also a divisor of n: n/(n/d) = d. So we can naturally group the divisors of n into pairs, each divisor d gives rise to the pair (d, n/d).
	// For such a pair, there are two possibilities:
	// d = n/d, which means n = d², or d = √n.
	// The two are different, then one of them is smaller than the other, say d < n/d. But that immediately translates to d² < n or d < √n.
	// So, either way, each pair of divisors contains (at least) one not exceeding √n, hence, if n is a composite number, its smallest divisor (other than 1) does not exceed √n.
	public static void FindAllPrimes(int end) {
		int num = 0;
		int[] primes = new int[end];
		for (int i = 2; i < end; ++i) {
			if (IsPrime(i, primes))
				primes[num++] = i;
		}
	}
	private static boolean IsPrime(int n, int[] primes) {
		for (int i = 0; i < primes.length; ++i) {
			if (primes[i] * primes[i] > n)
				return true;
			if (n % primes[i] == 0)
				return false;
		}
		return true;
	}

	static int main() throws IOException
	{
		FindContinuousSequence(15);

		System.in.read();
		return 0;
	}

>>>>>>> d5416360920294af336f554a6d7b5d3caba83632
}

