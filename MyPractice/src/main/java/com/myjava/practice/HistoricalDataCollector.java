package com.myjava.practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import org.dom4j.*;
import com.myjava.util.*;

public class HistoricalDataCollector {

	static final List<String> EXCLUDEGROUPINDEX = Arrays.asList(
			"2", // Historical Price and Return Data
			"4", // Historical Morningstar Analysis & Ratings
			"8", // Historical Attribution
			"9" // Historical Calculation
			);
	
	static void output2Xml() {
		StringBuilder strXml = new StringBuilder("<flds>");
		StringBuilder strTxt = new StringBuilder();

		Document document = XmlUtil.parseXMLFile(PathUtil.getConfPath(),
				"HistoricalDataPointGroup.xml");
		List<SimpleDP> dpList = new ArrayList<SimpleDP>();
		List<?> groupElements = document.selectNodes("/groups/universe/group");
		Iterator<?> it = groupElements.iterator();
		while (it.hasNext()) {
			Element gElement = (Element) it.next();
			String groupName = gElement.attributeValue("name");
			String groupIndex = gElement.attributeValue("i");
			if (EXCLUDEGROUPINDEX.contains(groupIndex))
				continue;

			List<?> dps = gElement.selectNodes("i");
			Iterator<?> dpsIterator = dps.iterator();
			while (dpsIterator.hasNext()) {
				Element dpElement = (Element) dpsIterator.next();
				String tempId = dpElement.attributeValue("id");
				if (tempId.indexOf(",") > 0) {
					tempId = tempId.split(",")[0];
				}
				SimpleDP dp = DataIdMapping.getSimpleDataPoint(tempId);
				if (null == dp || !("int".equals(dp.type) || "float".equals(dp.type)) || dpList.contains(dp))
					continue;
				dpList.add(dp);
				strXml.append(dp.xmlContent);
				strTxt.append(String.format("%s\t%s\n", dp.id, dp.name));
			}
		}
		strXml.append("</flds>");

		File f = new File("c:\\PA_histdatapoints.xml");
		try (FileOutputStream fos = new FileOutputStream(f)) {
			if (!f.exists())
				f.createNewFile();
			fos.write(strXml.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		File fTxt = new File("c:\\PA_hist.txt");
		try (FileOutputStream fos = new FileOutputStream(fTxt)) {
			if (!fTxt.exists())
				fTxt.createNewFile();
			fos.write(strTxt.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
