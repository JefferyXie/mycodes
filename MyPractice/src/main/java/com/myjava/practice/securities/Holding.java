package com.myjava.practice.securities;

public class Holding extends Security {
	public double Weight;
	public String toString() {
		return super.toString() + "; @Weight:" + Weight;
	}
}
