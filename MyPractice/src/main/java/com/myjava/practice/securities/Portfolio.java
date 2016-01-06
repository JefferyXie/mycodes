package com.myjava.practice.securities;

import java.util.*;

public class Portfolio {
	public String Id;
	public Date Date;
	public List<String> Managers;
	public List<Holding> Holdings;
	public String toString() {
		return "@Id:" + Id + "; @Date:" + Date + "; @Managers:" + Managers + "; @Holdings:" + Holdings;
	}
}
