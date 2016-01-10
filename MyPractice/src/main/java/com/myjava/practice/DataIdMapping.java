package com.myjava.practice;

import org.dom4j.*;
import com.myjava.util.*;

public class DataIdMapping {
	private static Document docDataIdMapping;
	private static Document docDataIdMappingDirect;

	static {
		docDataIdMapping = XmlUtil.parseXMLFile(PathUtil.getConfPath(), "DataIdMapping.xml");
		docDataIdMappingDirect = XmlUtil.parseXMLFile(PathUtil.getConfPath(), "DataIdMappingDirect.xml");
	}

	static String getDataPointXmlContent(String id) {
		SimpleDP dp = getSimpleDataPoint(id);
		if (null != dp)
			return dp.xmlContent;
		return "";
	}
	static SimpleDP getSimpleDataPoint(String id) {
		Node f = docDataIdMappingDirect.selectSingleNode("flds/f[@i='" + id + "']");
		if (null == f) {
			f = docDataIdMapping.selectSingleNode("flds/f[@i='" + id + "']");
		}
		if (null != f) {
			Element ele = (Element)f;
			String name = ele.attributeValue("udlbl");
			name = name.replace("\\n", "");
			String type = ele.attributeValue("type");
			SimpleDP dp = new SimpleDP(); 
			dp.id = id;
			dp.name = name;
			dp.type = type;
			dp.xmlContent = ele.asXML();

			Object clone = f.clone();
			String strValue = f.getStringValue();
			String text = f.getText();
			return dp;
		}
		return null;
	}
}
