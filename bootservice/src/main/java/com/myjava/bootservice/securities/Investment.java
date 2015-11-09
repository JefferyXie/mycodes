package com.myjava.bootservice.securities;

import java.util.List;

public class Investment extends Security {
	public List<Portfolio> Portfolios;
	public List<String> Managers;
	public String toString() {
		return super.toString() + "; @Portfolios:" + Portfolios + 
				"; @Managers:" + Managers;
	}
}
