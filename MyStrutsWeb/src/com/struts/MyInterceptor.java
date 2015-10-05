package com.struts;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class MyInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/* let us do some pre-processing */
		String output = "Pre-Processing";
		System.out.println(output);

		/* let us call action or next interceptor */
		String result = invocation.invoke();
		System.out.println(result);

		/* let us do some post-processing */
		output = "Post-Processing";
		System.out.println(output);

		return null;
	}

}
