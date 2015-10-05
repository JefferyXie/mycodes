package com.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
	
	@RequestMapping(value="/student", method=RequestMethod.GET)
	public ModelAndView student()
	{
		Student stu = new Student();
		return new ModelAndView("student", "springweb", stu); 
	}
	
	@RequestMapping(value="/addStudent", method=RequestMethod.POST)
	@ExceptionHandler({SpringException.class})
	public String addStudent(@ModelAttribute("abcd")Student stu, ModelMap model)
	{
		if (stu.getName().length() <= 3)
			throw new SpringException("Given name should be greater than 3");
		else
			model.addAttribute("name", stu.getName());
		if (stu.getAge() <= 10)
			throw new SpringException("Given age should be greater than 10");
		else
			model.addAttribute("age", stu.getAge());

		model.addAttribute("id", stu.getId());
		model.addAttribute("stuobj", stu);
		
		return "result";
	}
	
	@RequestMapping(value="/doadd", method=RequestMethod.GET)
	public String doAddStudent(@RequestParam("name") String nm, @RequestParam int age, @RequestParam String id)
	{
		System.out.println(nm);
		System.out.println(age);
		System.out.println(id);
		return "result";
	}

	@RequestMapping(value="/domv", method=RequestMethod.GET)
	public ModelAndView doModelAndView(@RequestParam String name, ModelAndView mv)
	{
		System.out.println(name);
		Student stu = new Student();
		stu.setName(name);
		mv.setViewName("result");
		mv.addObject("stuobj", stu);
		return mv;
	}

	@RequestMapping(value="doreq")
	public ModelAndView handleReqAndResp(HttpServletRequest request, HttpServletResponse response)
	{
		// access request...
		// access response...
		return new ModelAndView("final");
	}
}
