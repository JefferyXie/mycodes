package com.myjava.samples;

import java.io.*;
import java.util.*;

// java 按行读取文件
// http://blog.csdn.net/mafuli007/article/details/37535557
public class ReadFileByLine {

	public static List<String> first_list;
	public static List<String> second_list;

	public ReadFileByLine() {
		first_list = new LinkedList<>();
		second_list = new LinkedList<>();
	}

	public static void ReadFile() {
		final String filename = "d://aa.txt";
		String str = null;
		int i = 0;
		try {
			LineNumberReader reader = null;
			reader = new LineNumberReader(new FileReader(filename));
			while ((str = reader.readLine()) != null) {
				if (!str.isEmpty()) {
					String values[] = str.split("	");
					first_list.add(values[0]);
					second_list.add(values[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		ReadFileByLine reader = new ReadFileByLine();
		reader.ReadFile();

		for (int i = 0; i < first_list.size(); i++) {
			System.out.println(first_list.get(i) + ":" + second_list.get(i));
		}
	}
}
