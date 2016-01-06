package com.myjava.practice.securities;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJson {
	public boolean run(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (mapper.canSerialize(ShareClass.class)) {
				String strJson = mapper.writeValueAsString(obj);
				Object objJson = mapper.readValue(strJson, obj.getClass());
				System.out.println(strJson);
				System.out.println(objJson.toString());
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}
}
