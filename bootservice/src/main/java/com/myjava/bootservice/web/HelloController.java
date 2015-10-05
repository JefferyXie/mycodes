package com.myjava.bootservice.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.wordnik.swagger.annotations.ApiOperation;
import com.myjava.util.*;

//@RestController("hellocontroller")
//@RequestMapping("/v1/")
//@Api(value="hello", description="hello API")
@RestController
public class HelloController {
	
	@RequestMapping(method=RequestMethod.GET, value="/hello")
	@ApiOperation(httpMethod = "GET", value = "Say Hello To World using Swagger")
	public String sayHello() {
		return "Greetings from Xie's sayHello()! ";
	}
	
	//@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping(value="/data", method=RequestMethod.GET)
	public @ResponseBody List<DataPoint> getAllData(
			@RequestParam(value="id", required=false, defaultValue="001") String id) {
		final String defalutId = "001";
		List<DataPoint> lst = new ArrayList<DataPoint>();
		String inputId = id;
		if (defalutId.equals(inputId)) {
			inputId = new Integer(new Random().nextInt()).toString();
			lst.add(new DataPoint(inputId, "from getAllData()"));
		} else {
			DataPoint[] testDPs = {new DataPoint("01", "return"), new DataPoint("02", "P/E")};
			lst.addAll(Arrays.asList(testDPs));
		}
		return lst;
	}

	@RequestMapping(value="/data/{id}", method=RequestMethod.GET)
	public DataPoint getDataById(
			@PathVariable String id) {
		if (id.isEmpty()) return null;
		return new DataPoint(id, "from getDataById()");
	}
	
	@RequestMapping(value="/data/{id}", method=RequestMethod.PUT)
	public DataPoint putDataById(@PathVariable String id, @RequestBody DataPoint dp) {
		dp.setValue("$FROM putDataById()$" + dp.getValue());
		return dp;
	}
}
