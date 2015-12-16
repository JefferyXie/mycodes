package com.myjava.bootservice.securities;

public class Fund extends Investment {
	public double ManagementFee;
	public String toString() {
		return super.toString() + "; @ManagementFee:" + ManagementFee;
	}
}
