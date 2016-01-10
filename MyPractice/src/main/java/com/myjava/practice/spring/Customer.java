package com.myjava.practice.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Customer {

	// setter/getter is not necessary if using @Autowired
	@Autowired(required=false)
	@Qualifier("student")
	private Student student;
	//@Autowired
	//@Qualifier("stu2")
	//private Student student2;
	private int type;
	private String action;
	
	public Customer() {}
	
	//@Autowired
	/*public Customer(Student stu)
	{
		this.student = stu;
	}*/
	public void setStudent(Student stu)
	{
		this.student = stu;
	}
	public Student getStudent()
	{
		return this.student;
	}
	public void setType(int t)
	{
		this.type = t;
	}
	public int getType()
	{
		return this.type;
	}
	public void setAction(String act)
	{
		this.action = act;
	}
	public String getAction()
	{
		return this.action;
	}
	public String toString()
	{
		return String.format("Customer [action=%s, type=%d, student=%s]", action, type, student);
		//return String.format("Customer [action=%s, type=%d, student=%s, student2=%s]", action, type, student, student2);
	}
}
