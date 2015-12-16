package com.myjava.bootservice.securities;

import java.util.*;

public class Portfolio {
	public String Id;
	public Date Date;
	public List<Holding> Holdings;
	public String toString() {
		return "@Id:" + Id + "; @Date:" + Date + "; @Holdings:" + Holdings;
	}
}
