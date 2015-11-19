package com.myjava.algo;

import java.io.IOException;
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

	// 和为sum的连续正整数序列
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

	// 求两个三位数乘积的最大回文数
	// 回文数从两边读值是一样的。两个两位数乘积的最大回文数是9009＝99*99，求两个三位数乘积的最大回文数？	
	public static long FindPalindromicNumber() {
		Long max = 0L;
		for (long i = 999L; i >= 100; i--) {
			// 如果某个回文数888888＝924*962，那么也有可能是962*924，两次获得某个回文数，
			// 如果判断j>i, 应该可以回避此问题，减少将近一半的数值计算
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
	// 就是给一个25个值的等概率空间，前24个值的话，每3个值分配一个返回值
	// 如果不在这个范围（magic等于24了），则重做一遍。
	// 这是个由多个Monte-Carlo算法叠加的Las-Vegas算法。单个Monte-Carlo
	// 不fail的概率为24/25。所以Las-Vegas算法执行的期望次数为25/24
	// 所以这是一个O(1)期望时间的概率算法
	public static int GenerateUni8() {
		int v = 5 * GenerateUni5() + GenerateUni5();
		if (v != 24) 
			return v % 8;
		return GenerateUni8();
	}
	private static int GenerateUni5() {
		return new Random().nextInt(5);
	}

	static int main() throws IOException
	{
		FindContinuousSequence(15);

		System.in.read();
		return 0;
	}

}

