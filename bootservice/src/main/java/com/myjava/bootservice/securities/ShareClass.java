package com.myjava.bootservice.securities;

import java.util.List;

public class ShareClass {
	public String Id;
	public String Name;
	public List<Fund> Funds;
	public List<Portfolio> Portfolios;
	public String toString() {
		return "@Id:" + Id + "; @Name:" + Name +
				"; @Funds:" + Funds + 
				"; @Portfolios:" + Portfolios;
	}
}
