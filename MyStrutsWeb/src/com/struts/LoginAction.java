package com.struts;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.User;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	// With implementation of ModelDriven, no need to explicitly call 
	// @user.name but just @name inside JSP.
	public User getModel() 
	{
		return this.user;
	}
	

	public void validate()
	{
		if (StringUtils.isEmpty(user.getName()))
			addFieldError("name", "Name cannot be empty");
		if (StringUtils.isEmpty(user.getPassword()))
			addFieldError("password", "Password cannot be empty");
	}

	public String execute()
	{
		if (getUser().getName().equals("tester") && getUser().getPassword().equals("tester"))
			return "success";
		return "failed";
	}
}
