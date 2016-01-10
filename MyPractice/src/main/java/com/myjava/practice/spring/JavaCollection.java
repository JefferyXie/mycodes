package com.myjava.practice.spring;

import java.util.*;

public class JavaCollection {
	List addrList;
	Set addrSet;
	Map addrMap;
	Properties addrProp;
	
	public JavaCollection()
	{
		System.out.println("JavaCollection() constructs.");
	}
	public void setAddrList(List lst)
	{
		addrList = lst;
	}
	public List getAddrList()
	{
		System.out.println("addrList: " + addrList);
		return addrList;
	}
	public void setAddrSet(Set s)
	{
		addrSet = s;
	}
	public Set getAddrSet()
	{
		System.out.println("addrSet: " + addrSet);
		return addrSet;
	}
	public void setAddrMap(Map m)
	{
		addrMap = m;
	}
	public Map getAddrMap()
	{
		System.out.println("addrMap: " + addrMap);
		return addrMap;
	}
	public void setAddrProp(Properties prop)
	{
		addrProp = prop;
	}
	public Properties getAddrProp()
	{
		System.out.println("addrProp: " + addrProp);
		return addrProp;
	}
}
