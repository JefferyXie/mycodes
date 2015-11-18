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

	// ��Ϊsum����������������
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

	// ��������λ���˻�����������
	// �����������߶�ֵ��һ���ġ�������λ���˻�������������9009��99*99����������λ���˻�������������
	public static long FindPalindromicNumber() {
		Long max = 0L;
		for (long i = 999L; i >= 100; i--) {
			// ���ĳ��������888888��924*962����ôҲ�п�����962*924�����λ��ĳ����������
			// ����ж�j>i, Ӧ�ÿ��Իرܴ����⣬���ٽ���һ�����ֵ����
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

