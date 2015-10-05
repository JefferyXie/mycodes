package com.struts;

import com.opensymphony.xwork2.ActionSupport;

import java.util.*;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({
	@Result(name="success", location="/WEB-INF/jsp/helloAjax.jsp"),
	@Result(name="failed", location="/WEB-INF/jsp/error.jsp")
})
public class AjaxAutocomplete extends ActionSupport {
	private List<String> countries;
	private String country;
	
	@Action(value="/ajax")
	public String execute()
	{
		String data = "China, America, Brazil, India, UK, Germany, France, Italy";
		countries = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(data, ",");
		while (st.hasMoreTokens())
		{
			countries.add(st.nextToken().trim());
		}
		country = countries.get(0);
		return SUCCESS;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
