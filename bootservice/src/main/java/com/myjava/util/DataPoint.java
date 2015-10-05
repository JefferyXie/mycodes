package com.myjava.util;

public class DataPoint {
	private final String id;
	private String value;
	private double[] seriesValue = {1.1, 2.25, 3.13};
	
	public DataPoint(String i, String v) {
		this.id = i;
		this.value = v;
	}
	public String getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String v) {
		this.value = v;
	}
	public double[] getSeriesValue() {
		return seriesValue;
	}
}
