package com.myjava.algo;

public class MySort {
	public static void QuickSort(int src[]) {
		if (null == src) return;
		QuickSort(src, 0, src.length-1);
	}

	public static void QuickSort(int src[], int left, int right) {
		if (null == src || src.length < 1) return;
		
		int i = left;
		int j = right;
		int x = src[(i+j)/2];
		while (i <= j) {
			while (src[i] < x && i < right) ++i;
			while (src[j] > x && j > left) --j;
			
			if (i <= j) {
				int temp = src[i];
				src[i] = src[j];
				src[j] = temp;
				++i;
				--j;
			}
		}
		if (left < j) QuickSort(src, left, j);
		if (right > i) QuickSort(src, i, right);
	}
}
