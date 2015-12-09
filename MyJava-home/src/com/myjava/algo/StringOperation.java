package com.myjava.algo;

import java.util.Arrays;

public class StringOperation {

	private static void Print(char[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			System.out.print((i == 0 ? "[" : "") + arr[i] + ((i == arr.length-1) ? "]" : ", "));
		}
	}

	// http://blog.sina.com.cn/s/blog_60d6fadc01013n7a.html
	// find unique characters from a string
	public static int FindUniqueChars(char[] arr) {
		// characters have only 256 outcomes
		int[] chAll = new int[256];
		for (int i = 0; i < arr.length; ++i) {
			chAll[arr[i]]++;
		}
		int count = 0;
		StringBuffer sb = new StringBuffer("[");
		for (int j = 0; j < chAll.length; ++j) {
			if (chAll[j] == 1) {
				sb.append((char)j + ", ");
				count++;
			}
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append("]");
		System.out.println("\"" + String.valueOf(arr) + "\" has unique characters: " + sb);
		return count;
	}
	
	// list all possible combination with a given string
	// this version assumes no duplicate character in the array
	public static int PossiblePermutation(char[] arr) {
		System.out.println("PossiblePermutation of string: " + new String(arr));

		int count = PossiblePermutation(arr, 0, arr.length-1);

		System.out.println("Total: " + count);
		return count;
	}
	public static int PossiblePermutation(char[] arr, int start, int end) {
		int count = 0;
		if (start == end) {
			System.out.println(arr);
			return 1;
		} else {
			for (int i = start; i <= end; ++i) {
				Swap(arr, start, i);
				count += PossiblePermutation(arr, start+1, end);
				Swap(arr, start, i);
			}
		}
		return count;
	}
	private static boolean Swap(char[] arr, int p1, int p2) {
		if (null == arr || p1 < 0 || p2 < 0 || p1 == p2 || arr.length <= p1 || arr.length <= p2) return false;
		char ch = arr[p1];
		arr[p1] = arr[p2];
		arr[p2] = ch;
		return true;
	}
	
	// http://www.snippetexample.com/tag/hacker-rank-coding-challenge/
	// Match the braces and verify weather all the opening braces has the closing braces in right order. 
	// Input: String of braces only: “[{}]()[{{()}}()]”
	// Output: Yes
	// Input: String of braces only: “[{}]({{()}}()]”
	// Output: No
	public static boolean CheckBraces(char[] arr) {
		if (null == arr || arr.length <= 1) return false;
		
		char[] stack_char = new char[arr.length];
		int pos = 0;
		boolean flag = true;
		for (int i = 0; i < arr.length; ++i) {
			switch (arr[i]) {
			case '{':
			case '[':
			case '(':
				stack_char[pos++] = arr[i];
				break;
			case '}':
				flag = (stack_char[--pos] == '{');
				break;
			case ']':
				flag = (stack_char[--pos] == '[');
				break;
			case ')':
				flag = (stack_char[--pos] == '(');
				break;
			default: break;
			}
			if (!flag) break;
		}
		System.out.println("CheckBraces: array = " + new String(arr));
		System.out.println(flag ? "Matched." : "Mismatched!");

		return flag;
	}
}
