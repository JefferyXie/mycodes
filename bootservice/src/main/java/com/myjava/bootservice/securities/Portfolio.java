package com.myjava.bootservice.securities;

import java.sql.Date;
import java.util.List;

public class Portfolio {
	public String Id;
	public Date Date;
	public List<Holding> Holdings;
	public String toString() {
		return "@Id:" + Id + "; @Date:" + Date + "; @Holdings:" + Holdings;
	}
}
