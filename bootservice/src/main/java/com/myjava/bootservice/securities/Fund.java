package com.myjava.bootservice.securities;

public class Fund extends Investment {
	public double MangementFee;
	public String toString() {
		return super.toString() + "; @ManagementFee:" + MangementFee;
	}
}
