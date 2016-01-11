package test.myjava;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myjava.basic.CollectionCenter;
import com.myjava.basic.InnerClass;

public class basicTest {

	@Test
	public void testAnalyze() {
		
		String book = "I like this book";
		
		String[] words = book.split(" ");
		StringBuffer sb = new StringBuffer();
		for (int i = words.length-1; i >= 0; i--) {
			sb.append(words[i]).append(" ");
		}
		int index = book.length();
		while (index > 0) {
			int pos = book.lastIndexOf(" ", index-1);
			sb.append(book.substring(pos+1, index)).append(" ");
			index = pos;
		}
		
		InnerClass inner = new InnerClass();
		inner.analyze();
		InnerClass.Inner_static_public inner_static_pub = new InnerClass.Inner_static_public();
		// cannot access the non-public fields
		System.out.print(inner_static_pub.VALUE);
	}
	
	@Test
	public void testCollections() {
		CollectionCenter.InteratorEnumeration();
	}
}
