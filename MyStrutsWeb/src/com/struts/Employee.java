package com.struts;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Results({
	@Result(name="success", location="/WEB-INF/jsp/success.jsp"),
	@Result(name="input", location="/index.jsp")
})
@SuppressWarnings("serial")
public class Employee extends ActionSupport {
	private String name;
	private int age;

	@Action(value="/empinfo")
	public String execute()
	{
		return SUCCESS;
	}
	
	@RequiredStringValidator(message="The name is required")
	public String getName() { return name; }
	public void setName(String nm) { this.name = nm; }

	@IntRangeFieldValidator(message="Age must be within [21, 60]", min="21", max="60")
	public int getAge() { return age; }
	public void setAge(int a) { this.age = a; }
}

