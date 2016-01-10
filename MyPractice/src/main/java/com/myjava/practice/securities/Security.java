package com.myjava.practice.securities;

public class Security {
	public String Id;
	public String Name;
	public SecurityType Type;
	public String toString() {
		return "@Id:" + Id + "; @Name:" + Name + "; @Type:" + Type;
	}
}
