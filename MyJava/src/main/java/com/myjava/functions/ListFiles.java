package com.myjava.functions;

import java.io.File;
import java.util.ArrayList;

// �ݹ����Ŀ¼
// http://blog.csdn.net/mafuli007/article/details/40106297
public class ListFiles {
	public static ArrayList filelist = new ArrayList();

	public static void listfiles(String dir) {
		File file = new File(dir);
		File[] files = file.listFiles();
		if (null == files) {
			return;
		}
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				listfiles(files[i].getAbsolutePath());
			} else {
				System.out.println(files[i]);
				filelist.add(files[i]);
			}
		}
	}
}
