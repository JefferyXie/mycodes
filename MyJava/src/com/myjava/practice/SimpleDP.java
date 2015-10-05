package com.myjava.practice;

public class SimpleDP {
	String id;
	String name;
	String type;
	String xmlContent;

	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof SimpleDP) {
			SimpleDP anotherDP = (SimpleDP) anObject;
			if (this.id.equals(anotherDP.id))
				return true;
		}
		return false;
	}
}
