package com.struts;

import java.util.*;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class HelloAction implements Action {

	private String name;
	
	public String execute() throws Exception
	{
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("key1", new String("this is key1"));
		context.put("key2", new String("this is key2"));
		stack.push(context);
		
		System.out.println("Size of valueStack: " + stack.size());
		
		return "success";
		//return "error";
	}
	public String getName() { return name; }
	public void setName(String nm) { this.name = nm; }
}
