package com.myjava.algo;

import java.io.*;
import java.util.*;

public class NumOperation {

	private static void Print(int i, int j)
	{
		for(int m = i; m <= j; m++)
		{
			System.out.print(m + " ");
		}
		System.out.println();
	}
	private static void Print(int[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			System.out.print((i == 0 ? "[" : "") + arr[i] + ((i == arr.length-1) ? "]" : ", "));
		}
	}
	public static int Exponential_Mod(int base, int exp, int mod) {
		if (mod <= 0) mod = 1000000007;
		long ans = 1;
		int a = base;
		while (exp > 0) {
			if ((exp & 0x1) != 0) ans = ans * a % mod;
			a = a * a % mod;
			exp >>= 1;
		}
		return (int)ans;
	}

	// 和为sum的连续正整数序列
	// http://blog.csdn.net/mafuli007/article/details/8558657
	public static boolean FindContinuousSequence(int sum) {
		if (sum < 3 ) return false;

		int i = sum / 2;
		int j = i + 1;

		int tmpsum = i + j;
		while(i > 0) {
			if (tmpsum  == sum) {
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
	
	// 最大连续元素的和
	public static int[] FindMaxContinuousSum(int[] arr) {
		if (null == arr || arr.length <= 0) return null;
		
		int index_start = 0;
		int index_end = 0;
		int index_start_temp = 0;
		int sumAll = arr[0];
		int maxSum = arr[0];
		int minSum = Math.min(arr[0], 0);
		for (int i = 1; i < arr.length; ++i) {
			sumAll += arr[i];
			if (sumAll - minSum > maxSum) {
				maxSum = sumAll - minSum;
				index_end = i;
				index_start = index_start_temp < index_end ? index_start_temp : index_end;
			}
			if (minSum >= sumAll) {
				minSum = sumAll;
				index_start_temp = i + 1;
			}
		}

		System.out.println("Max sum: " + maxSum + "; index ranges: [" + index_start + ", " + index_end + "]");
		StringBuffer str = new StringBuffer();
		for (int e : arr) {
			str.append(e + ",");
		}
		str.setLength(str.length()-1);
		System.out.println("\t" + str.toString());

		return new int[] { index_start, index_end, maxSum };
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

	// given an array that have continuous integers 1 ~ n, where there's only
	// one number repeated, find the repeated number
	public static int FindDuplicateNumber(int[] arr) {
		int N = arr[0];
		for (int i : arr) {
			N = N > i ? N : i;
		}
		int sum = N*(N+1)/2;
		for (int i : arr) {
			sum -= i;
		}
		return -sum;
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
	// can be upgraded to lambda
	private static int GenerateUni5() {
		return new Random().nextInt(5);
	}

	// http://stackoverflow.com/questions/10690843/finding-the-list-of-prime-numbers-in-shortest-time
	// Let n be a composite number (i.e. a number greter than 1 that has divisors other than 1 and n), 
	// and let d be a divisor of n.
	// Now, d being a divisor of n means that n/d is an integer, and also a divisor of n: n/(n/d) = d. 
	// So we can naturally group the divisors of n into pairs, each divisor d gives rise to the pair (d, n/d).
	// For such a pair, there are two possibilities:
	// d = n/d, which means n = d², or d = √n.
	// The two are different, then one of them is smaller than the other, say d < n/d. But that immediately 
	// translates to d² < n or d < √n.
	// So, either way, each pair of divisors contains (at least) one not exceeding √n, hence, if n is a 
	// composite number, its smallest divisor (other than 1) does not exceed √n.
	public static void FindAllPrimes(int end) {
		int num = 0;
		int[] primes = new int[end];
		for (int i = 2; i < end; ++i) {
			if (IsPrime(i, primes, num))
				primes[num++] = i;
		}
	}
	private static boolean IsPrime(int n, int[] primes, int length) {
		for (int i = 0; i < length; ++i) {
			if (primes[i] * primes[i] > n)
				return true;
			if (n % primes[i] == 0)
				return false;
		}
		return true;
	}
	
	// http://stackoverflow.com/questions/4632322/finding-all-possible-combinations-of-numbers-to-reach-a-given-sum
	// http://articles.leetcode.com/2010/09/print-all-combinations-of-number-as-sum.html
	// Finding all possible combinations of numbers to reach a given sum
	public static void SumUp2Target(int[] numbers, int target) {
         SumUp2Target(numbers, target, new int[0]);
     }
	private static void SumUp2Target(int[] numbers, int target, int[] partial) {
        int s = 0;
        for (int x : partial) s += x;
        if (s == target)
             System.out.println("sum("+Arrays.toString(partial)+") = "+target);
        if (s >= target)
             return;

        // recursively find all possible permutation from array numbers[]
        for (int i = 0; i < numbers.length; i++) {
              int[] remaining = Arrays.copyOfRange(numbers, i + 1, numbers.length);
              int[] partial_rec = new int[partial.length + 1];
              System.arraycopy(partial, 0, partial_rec, 0, partial.length);
              partial_rec[partial_rec.length-1] = numbers[i];
              SumUp2Target(remaining, target, partial_rec);
        }
     }
	
	// http://www.snippetexample.com/tag/hacker-rank-coding-challenge/
	// given an array, recursively subtract the smallest value from other elements until the array is empty
	public static void SubSmallest(int[] arr) {
		if (null == arr || arr.length <= 0) return;
		int index_smallest = 0;
		// find the smallest value
		for (int i = 1; i < arr.length; ++i) {
			if (arr[index_smallest] > arr[i])
				index_smallest = i;
		}
		
		for (int i = 0; i < arr.length; ++i) {
			int temp = arr[i];
			arr[i] = arr[index_smallest];
			arr[index_smallest] = temp;

			index_smallest = i + 1;

			System.out.print(i+"'th operation: smallest value - [" + arr[i] + "], rest array - [");
			for (int j = i + 1; j < arr.length; ++j) {
				arr[j] -= arr[i];
				// find the smallest value from the rest of array
				if (j != i + 1 && arr[index_smallest] > arr[j]) {
					index_smallest = j;
				}
				System.out.print(arr[j] + ((j == arr.length-1) ? "" : ", "));
			}
			System.out.print("]\n");
		}
	}
	
	// http://www.snippetexample.com/tag/hacker-rank-coding-challenge/
	// given x, y and N, print all possible permutations starting with 0 and size as N, 
	// each element incremented by x, y, or both
	public static int PossiblePermutation(int x, int y, int N) {
		if (x <= 0 || y <= x || N <= 0) return -1;
		
		int[] arr = new int[] {0};
		int count = PossiblePermutation(arr, x, y, N);
		System.out.println("Totally " + count + " possible permutations!");
		return count;
	}
	private static int PossiblePermutation(int[] arr, int x, int y, int N) {
		int count = 0;
		if (arr.length == N) {
			System.out.print("Possible permutation: [");
			for (int i = 0; i < arr.length; ++i) {
				System.out.print(arr[i] + ((i == arr.length-1) ? "" : ", "));
			}
			System.out.print("]\n");
			return 1;
		}
		int[] arr_new = new int[arr.length+1];
		System.arraycopy(arr, 0, arr_new, 0, arr.length);

		arr_new[arr_new.length-1] = arr_new[arr_new.length-2] + x;
		count += PossiblePermutation(arr_new, x, y, N);

		arr_new[arr_new.length-1] = arr_new[arr_new.length-2] + y;
		count += PossiblePermutation(arr_new, x, y, N);

		return count;
	}
	
	// given an integer array, show all possible permutation which size is N
	// this version assumes no duplicate element from input array
	public static int PossiblePermutation(int[] arr, int N) {
		return PossiblePermutation(arr, N, null);
	}
	public static int PossiblePermutation(int[] arr, int N, ArrayList<int[]> permutations) {
		System.out.print("\nPossible permutations:\n");
		System.out.print("\tarray = ");
		Print(arr);
		System.out.print("\n\tsize = " + N + "\n");

		int[] target = new int[0];
		int count = PossiblePermutation(arr, 0, target, N, permutations);

		System.out.print("Total: "+count+"\n");
		return count;
	}
	private static int PossiblePermutation(int[] original, int pos, int[] target, int N, ArrayList<int[]> permutations) {
		// get the permutation
		if (target.length == N) {
			if (null != permutations) {
				permutations.add(target);
			}

			Print(target);
			System.out.println();
			return 1;
		}
		int count = 0;
		for (int i = pos; i < original.length; ++i) {
			int[] target_new = new int[target.length+1];
			System.arraycopy(target, 0, target_new, 0, target.length);
			target_new[target_new.length-1] = original[i];
			count += PossiblePermutation(original, i+1, target_new, N, permutations);
		}
		return count;
	}
	
	// count the number of repeated times in an array
	// this version support both negative and positive arrays
	public static boolean CountRepeatedTimes(int[] arr) {
		if (null == arr || arr.length <= 0) return false;
		
		// to support negative arrays, find min value and get the max possible size 
		int maxValue = arr[0];
		int minValue = arr[0];
		for (int i : arr) {
			maxValue = i > maxValue ? i : maxValue;
			minValue = i < minValue ? i : minValue;
		}
		int maxPossibleSize = maxValue - minValue + 1;
		int[] arr_frequency = new int[maxPossibleSize];
		for (int i : arr) {
			// index is element offset by minValue
			int index = i - minValue;
			arr_frequency[index]++;
		}
		System.out.println();
		for (int i = 0; i < arr_frequency.length; ++i) {
			if (arr_frequency[i] > 0) {
				// element should be restored with offset by minValue
				int element = i + minValue;
				System.out.println("[" + element + "] is repeated " + arr_frequency[i] + " times");
			}
		}
		return true;
	}
	
	// http://www.snippetexample.com/tag/hacker-rank-coding-challenge/
	// given an array and integer k, find all pairs from array that have distance as k 
	public static boolean FindKDiffPairs(int[] arr, int k) {
		if (null == arr || arr.length <= 0 || k == 0) return false;
		
		// to support negative arrays, find min value and get the max possible size 
		int maxValue = arr[0];
		int minValue = arr[0];
		for (int i : arr) {
			maxValue = i > maxValue ? i : maxValue;
			minValue = i < minValue ? i : minValue;
		}
		System.out.print("\nPairs with k difference from array: \n\tk = " + k + "\n\tarray = [");
		int maxPossibleSize = maxValue - minValue + 1;
		int[] arr_frequency = new int[maxPossibleSize];
		for (int i = 0; i < arr.length; ++i) {
			// index is element offset by minValue
			int index = arr[i] - minValue;
			arr_frequency[index]++;

			System.out.print(arr[i] + (i == (arr.length-1) ? "]\n" : ", "));
		}
		
		for (int i = 0; i < arr.length; ++i) {
			int index = arr[i] - minValue;
			int index_pair_left = index - k;
			// it's symmetric, doesn't need to check index on the right side
			//int index_pair_right = index + k;
			if (index_pair_left >= 0 && index_pair_left < arr_frequency.length && arr_frequency[index_pair_left] > 0) {
				System.out.print("[" + (index_pair_left+minValue) + ", " + arr[i] + "] ");
			}
		}
		System.out.println();
		return true;
	}
	
	// given integer start and end, count the integers that are perfect squares within [start, end]
	public static int CountPerfectSquare(int start, int end) {
		if (start < 0 || end < start) return -1;
		
		int start_floor = (int)Math.floor(Math.sqrt(start));
		int end_ceil = (int)Math.ceil(Math.sqrt(end));
		int count = end_ceil-start_floor;
		if ((start_floor^2) == start && (end_ceil^2) == end)
			count++;
		else if ((start_floor^2) != start && (end_ceil^2) != end)
			count--;
		
		System.out.print(count + " perfect squares between [" + start + ", " + end + "]: ");
		int first = (int)Math.ceil(Math.sqrt(start));
		for (int i = first; i <= first+count; ++i) {
			System.out.print(i + ((i == first+count) ? "\n" : ", "));
		}
		return count;
	}
	
	// http://www.snippetexample.com/tag/hacker-rank-coding-challenge/
	// http://stackoverflow.com/questions/27248327/caterpillars-and-leaves-can-we-do-better-than-onc
	// given an array of caterpillars and N leaves, leaf will be eaten if index satisfies index % num == 0
	// calculate how many leaves will be left?
	// Example: 
	// arr = [2,3,4]
	// N = 10 ---> [1,2,3,4,5,6,7,8,9,10]
	// Left leaves = [1,5,7]
	public static int CaterpillarLeaves(int[] arr, int N) {
		if (null == arr || arr.length <= 0 || N <=0) return -1;
		
		int totaleaten = 0;
		for (int i = 1; i <= arr.length; ++i) {
			ArrayList<int[]> permutations = new ArrayList<>();
			PossiblePermutation(arr, i, permutations);
			for (int[] permutation : permutations) {

				int lcm = LeastCommonMultiple(permutation);

				// inclusion-exclusion pattern, should use (-1)^(i+1);
				int num_eaten = (int)Math.pow(-1, i+1)*N/lcm;
				totaleaten += num_eaten;
				
				System.out.print("\tPermutation: " );
				Print(permutation);
				System.out.print("\n\tEatean leaves: " + num_eaten);
				System.out.print("\n\tTotal eaten leaves: " + totaleaten + "\n");
			}
		}
		int totalleft = N - totaleaten;

		System.out.print("\nCaterpillarLeaves:\n\tarray = ");
		Print(arr);
		System.out.print("\n\tN = " + N);
		System.out.print("\n\tTotal left: " + totalleft + "\n");

		return totalleft;
	}

	public static int LeastCommonMultiple(int a, int b) {
		return a*b/GreatestCommonDivisor(a, b);
	}
	public static int LeastCommonMultiple(int[] arr) {
		if (null == arr || arr.length < 1) return 0;
		if (arr.length == 1) return arr[0];
		
		int lcm = arr[0];
		for (int i = 1; i < arr.length; ++i) {
			lcm = LeastCommonMultiple(lcm, arr[i]);
		}
		System.out.print("LeastCommonMultiple is " + lcm + "\n\tarray = ");
		Print(arr);
		System.out.println();
		return lcm;
	}
	public static int GreatestCommonDivisor(int a, int b) {
		return b == 0 ? a : GreatestCommonDivisor(b, a % b);
	}
	public static int GreatestCommonDivisor(int[] arr) {
		if (null == arr || arr.length < 1) return 0;
		if (arr.length == 1) return arr[0];
		
		int gcd = arr[0];
		for (int i = 1; i < arr.length; ++i) {
			gcd = GreatestCommonDivisor(gcd, arr[i]);
		}
		System.out.print("GreatestCommonDivisor is " + gcd + "\n\tarray = ");
		Print(arr);
		System.out.println();
		return gcd;
	}
	
	// http://codereview.stackexchange.com/questions/77975/time-limit-exceeded-for-sherlock-and-queries-hackerrank-challenge
	// https://github.com/havelessbemore/hackerrank/blob/master/algorithms/warmup/sherlock-and-queries.java
    public static void Sherlock() {
		final int MOD = 1000000007;
		try {
	        //INPUT
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String[] strs = br.readLine().split(" ");
	        int N = Integer.parseInt(strs[0]);
	        int M = Integer.parseInt(strs[1]);
	        int[] A = getArr(br.readLine().split(" "));
	        int[] B = getArr(br.readLine().split(" "));
	        int[] C = getArr(br.readLine().split(" "));
	        
	        //SOLVE
	        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	        for(int i = 0; i < M; ++i){
	            int key = B[i];
	            Integer j = map.get(key);
	            map.put(key, (j == null) ? C[i] : (int)((((long)j) * C[i]) % MOD));
	        }
	        
	        for(int divisor : map.keySet()){
	            long multiplier = map.get(divisor);
	            for(int j = divisor - 1; j < N; j += divisor){
	                A[j] = (int)((A[j] * multiplier) % MOD);
	            }
	        }
	        
	        //OUTPUT
	        StringBuffer sb = new StringBuffer();
	        for(int i = 0; i < N; ++i){
	            sb.append(A[i] + " ");
	        }
	        System.out.print(sb);
		} catch (Exception ex) {}
    }
    private static int[] getArr(String[] strs){
        final int N = strs.length;
        int[] arr = new int[N];
        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(strs[i]);
        }
        return arr;
    }
}

