package com.myjava.practice.securities;

import java.util.List;

public class Fund extends Security {
	public double ManagementFee;
	public List<Portfolio> Portfolios;
	public String toString() {
		return super.toString() + "; @ManagementFee:" + ManagementFee + "; @Portfolios:" + Portfolios;
	}
}
