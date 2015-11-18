package com.myjava.algo;

import java.io.IOException;

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

	static int main() throws IOException
	{
		FindContinuousSequence(15);

		System.in.read();
		return 0;
	}

}

