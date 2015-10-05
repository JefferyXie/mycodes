package com.myjava.util;

import java.io.*;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.*;
import org.dom4j.io.*;

public class XmlUtil {

    static public Document parseXMLFile(String path, String fileName) {
        Document document = null;
        String filePath = path + fileName;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            SAXReader reader = new SAXReader();
            document = reader.read(fis);
        } catch (Exception e) {

        }

        return document;
    }

    /**
     * get the text of e , if e is null, then return the defaultValue
     * 
     * @param e
     * @param xpath
     * @param defaultValue
     * @return
     */
    public static String getText(Node e, String defaultValue) {
        if (e == null) {
            return defaultValue;
        }
        return e.getText();
    }

    public static String getAttr(Element e, String xpath, String attrName, String defaultValue) {
        Node node = e.selectSingleNode(xpath);
        if (node == null) {
            return defaultValue;
        }
        Element el = (Element) node;
        return el.attributeValue(attrName);
    }

    /**
     * if attrValue not null, add to e as its attr
     * 
     * @param e
     * @param attrName
     * @param attrValue
     */
    public static void addAttrIfNotNull(Element e, String attrName, Object attrValue) {
        if (e != null && attrName != null && attrValue != null) {
            e.addAttribute(attrName, attrValue.toString());
        }
    }

    /**
     * if attrValue not null and is not empty, add to e as its attr
     * 
     * @param e
     * @param attrName
     * @param attrValue
     */
    public static void addAttrIfValid(Element e, String attrName, String attrValue) {
        if (e != null && attrName != null && StringUtil.isValid(attrValue)) {
            e.addAttribute(attrName, attrValue);
        }
    }

    public static String getInnerXmlSafe(Element e) {
        if (e == null) {
            return "";
        }
        return getInnerXml(e);
    }

    /**
     * create an element with attributes then add to parent
     * 
     * @param parent
     * @param eName
     *            element name
     * @param attrNameAndValues
     *            string array with attrName and value, such as name1, value1, name2, value2, name3, value3 ...
     */
    public static void createElement(Element parent, String eName, String... attrNameAndValues) {
        Element e = parent.addElement(eName);
        if (attrNameAndValues.length < 2) {
            return;
        }
        int i = 1;
        while (i < attrNameAndValues.length) {
            e.addAttribute(attrNameAndValues[i - 1], attrNameAndValues[i]);
            i = i + 2;
        }
    }

    /**
     * translate element to json, just attribute, not include child elements
     * 
     * @param e
     * @return
     */
    /*public static JSONObject toJson(Element e, boolean withElementName) {
        if (e == null) {
            return null;
        }
        JSONObject json = new JSONObject();
        int attrNum = e.attributeCount();
        for (int i = 0; i < attrNum; i++) {
            Attribute attr = e.attribute(i);
            json.put(attr.getName(), attr.getValue());

        }
        if (withElementName) {
            return new JSONObject().put(e.getName(), json);
        } else {
            return json;
        }
    }*/

    /**
     * get and trim(including all blank space) the innerXml content of the element
     * 
     * @param e
     * @return
     */
    public static String getInnerXml(Element e) {
        String xml = e.asXML();
        String nodeName = e.getName();
        String startTag = "<" + nodeName + "\\b\\s*[^>]*" + ">";
        String endTag = "</" + nodeName + ">";
        return xml.replaceAll("^" + startTag + "|" + endTag + "$", "").replaceAll("^\\s+|\\s+$", "");
    }

    static public String text(Element element) {
        if (element == null) {
            return null;
        }

        return element.getText();
    }

    static public String text(Element element, String path) {
        if (element == null || path == null) {
            return null;
        }

        Element tmp = (Element) element.selectSingleNode(path);
        if (tmp != null) {
            return tmp.getText();
        } else {
            return null;
        }
    }

    static public String attributeValue(Element element, String attriName) {
        return attributeValue(element, attriName, null);
    }

    public static String attributeValueOfNode(Element element, String nodeName, String attriName) {
        Element node = (Element) element.selectSingleNode(nodeName);
        if (node != null) {
            return XmlUtil.attributeValue(node, attriName);
        } else {
            return null;
        }
    }

    static public String attributeValue(Element element, String attriName, String defaultString) {
        if (element == null) {
            return defaultString;
        }
        String val = element.attributeValue(attriName);
        if (val == null) {
            return defaultString;
        } else {
            return val;
        }
    }

    static public char attributeChar(Element element, String attriName) {
        if (element == null) {
            return '\0';
        }
        String value = element.attributeValue(attriName);
        if (value != null && !value.isEmpty()) {
            return value.charAt(0);
        } else {
            return '\0';
        }
    }

    static public int attributeInt(Element element, String attriName) {
        if (element == null) {
            return 0;
        }

        String str = element.attributeValue(attriName);
        if (str == null) {
            return 0;
        }

        try {
            return IntegerUtil.parse(str);
        } catch (NumberFormatException e) {
            //log.warn(e);
        }
        return 0;
    }

    static public boolean attributeBoolean(Element element, String attriName) {
        if (element == null) {
            return false;
        }

        String str = element.attributeValue(attriName);
        if (str == null) {
            return false;
        }

        try {
            return str.equals("true") || str.equals("1");
        } catch (NumberFormatException e) {
            //log.warn(e);
        }
        return false;
    }

    static public double attributeDouble(Element element, String attriName) {
        return attributeDouble(element, attriName, Double.NaN);
    }

    static public double attributeDouble(Element element, String attriName, double defaultValue) {
        if (element == null) {
            return defaultValue;
        }

        String str = element.attributeValue(attriName);
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            String temp = "Cannot parse float number " + str;
            System.out.println(temp);
        }
        return defaultValue;
    }

    static public Date attributeDate(Element element, String attriName) {
        if (element == null) {
            return null;
        }

        String str = element.attributeValue(attriName);
        //return DateUtil.parse(str);
        return new Date();
    }

    static public Date attributeDate(Element element, String attriName, String format) {
        if (element == null) {
            return null;
        }

        String str = element.attributeValue(attriName);
        //return DateUtil.parse(str, format);
        return new Date();
    }

    /**
     * @param element
     * @param map
     * @return Map
     * @description Getting attributes from dom4j element according to the keys in map
     */
    static public Map<String, String> attribute(Element element, Map<String, String> map) {
        if (element == null) {
            return null;
        }
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            map.put(key, XmlUtil.attributeValue(element, key));
        }
        return map;
    }

    /**
     * @param element
     * @param map
     * @return Map
     * @description Getting attributes from dom4j element according to the keys in map
     */
    static public Map<String, String> attributesToMap(Element element) {
        Map<String, String> map = new HashMap<String, String>();
        if (element == null) {
            return map;
        }
        List<?> attrs = element.attributes();
        Attribute attr;
        for (int i = 0; i < attrs.size(); i++) {
            attr = (Attribute) attrs.get(i);
            map.put(attr.getName(), attr.getValue());
        }
        return map;
    }

    static public void removeAttribute(Element node, String attriName) {
        Attribute attri = node.attribute(attriName);
        if (attri != null) {
            node.remove(attri);
        }
    }

    static public Element selectSingleNode(Element element, String path, String attributeName, String attributeValue) {
        List<Element> list = element.selectNodes(path);
        for (Element ele : list) {
            String value = attributeValue(ele, attributeName);
            if (value != null && value.equals(attributeValue)) {
                return ele;
            }
        }

        return null;
    }

    static public Element selectSingleNode(Element element, String path) {
        List<Element> list = element.selectNodes(path);
        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    static public List<Element> selectNodes(Element element, String path) {
        List<Element> list = element.selectNodes(path);
        return list;
    }

    static public void removeElement(Element parentElement, Element element) {
        parentElement.remove(element);
    }

    static public void filterElement(Element parentElement, List nodes, String attr, String attrValue) {
        if (parentElement == null || nodes == null) {
            return;
        }

        for (Object node : nodes) {
            Element e = (Element) node;
            String accessedBy = XmlUtil.attributeValue(e, attr);
            List<String> list = null;
            if (accessedBy != null) {
                list = ListUtil.toList(accessedBy.split(","));
            }

            if (list == null || !list.contains(attrValue)) {
                XmlUtil.removeElement(parentElement, e);
            } else {
                List subNodes = e.selectNodes("I");
                filterElement(e, subNodes, attr, attrValue);
            }
        }
    }

    public static Document toDocument(String fileContent) {
        try {
            return DocumentHelper.parseText(fileContent);
        } catch (DocumentException e) {
            //log.warn(e);
        }
        return null;
    }

    /**
     * make a xml element string
     * 
     * @param elementName
     * @param attrList
     *            store attrName, attrValue {"id", "123"}
     * @return
     */
    public static String makeElementString(String elementName, String[][] attrList) {
        StringBuffer xml = new StringBuffer();
        xml.append("<").append(elementName);
        for (String[] attr : attrList) {
            String name = attr[0];
            String value = attr[1];
            String attrString = name + "=" + StringUtil.wrapWithDoubleQuotes(value);
            xml.append(" ").append(attrString);
        }
        xml.append(">");
        xml.append("</").append(elementName).append(">");
        return xml.toString();
    }

    private static Document parse(String xml) throws UnsupportedEncodingException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        return document;
    }

    public static Element getElement(String xml, String xpath) throws Exception {
        Document document = parse(xml);
        return (Element) document.selectSingleNode(xpath);
    }

    public static String replaceString(String strData, String regex, String replacement) {
        if (strData == null) {
            return null;
        }
        int index;
        index = strData.indexOf(regex);
        String strNew = "";
        if (index >= 0) {
            while (index >= 0) {
                strNew += strData.substring(0, index) + replacement;
                strData = strData.substring(index + regex.length());
                index = strData.indexOf(regex);
            }
            strNew += strData;
            return strNew;
        }
        return strData;
    }

    public static String encodeString(String strData) {
        if (strData == null) {
            return "";
        }
        strData = replaceString(strData, "&", "&amp;");
        strData = replaceString(strData, "<", "&lt;");
        strData = replaceString(strData, ">", "&gt;");
        strData = replaceString(strData, "'", "&apos;");
        strData = replaceString(strData, "\"", "&quot;");
        return strData;
    }

    public static String decodeString(String strData) {
        strData = replaceString(strData, "&lt;", "<");
        strData = replaceString(strData, "&gt;", ">");
        strData = replaceString(strData, "&apos;", "'");
        strData = replaceString(strData, "&quot;", "\"");
        strData = replaceString(strData, "&amp;", "&");
        return strData;
    }

    /**
     * parse xml file to bean
     * 
     * @param xmlStream
     * @return
     */
    public static <T> T fromXml(InputStream xmlStream, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(xmlStream);
        } catch (JAXBException e) {
            //log.error(e);
        }

        return null;
    }

    /**
     * parse xml string to bean
     * 
     * @param xml
     * @return
     */
    public static <T> T fromXml(String xml, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            //log.error(e);
        }

        return null;
    }

    /**
     * parse bean to xml string
     * 
     * @return
     */
    public static String toXml(Object bean) {
        return toXml(bean, false);
    }
    /**
     * parse bean to xml string
     * @param noDeclaration : if true, xml string without <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     * @return
     */
    public static String toXml(Object bean, boolean noDeclaration) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, noDeclaration);
            marshaller.marshal(bean, writer);
            return writer.toString();
        } catch (JAXBException e) {
            //log.error(e);
            e.printStackTrace();
        }
        return null;
    }
}
