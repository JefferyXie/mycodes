package com.myjava.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StringUtil {

    public static boolean isValid(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isEqual(String str1, String str2) {
        if (!isValid(str1) && !isValid(str2)) {
            return true;
        } else if (isValid(str1) && isValid(str2)) {
            return str1.equals(str2);
        }
        return false;
    }

    public static String specialEncode(String symbol) {
        if (!StringUtil.isValid(symbol)) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (char ch : symbol.toCharArray()) {
            try {
                String tmp = URLEncoder.encode(String.valueOf(ch), "UTF-8");
                if (tmp.indexOf("%") != -1) {
                    tmp = URLDecoder.decode(tmp, "UTF-8");
                }
                builder.append(tmp);
            } catch (UnsupportedEncodingException e) {
                //log.warn(e);
            }
        }
        return builder.toString();
    }

    public static String[] split(String text, String separator) {
        List<String> list = new ArrayList<String>();
        if (!isValid(text) || !isValid(separator)) {
            return list.toArray(new String[0]);
        }

        int pos = 0;
        int len = text.length();
        int sepLen = separator.length();
        while (pos < len) {
            int end = text.indexOf(separator, pos);
            String temp = null;
            if (end == -1) {
                temp = text.substring(pos);
                pos = len;
            } else {
                temp = text.substring(pos, end);
                pos = end + sepLen;
            }

            if (isValid(temp)) {
                list.add(temp);
            }
        }

        return list.toArray(new String[0]);
    }

    public static String removeSpecialSymbolForFileName(String name) {
        return name.replaceAll("(/|\\*|:|\\?|<|>|\"|\\||\\\\)", "");
    }

    public static void main(String[] args) {
        String name = "4/5\\6*:?<>\"|";
        System.out.println(removeSpecialSymbolForFileName(name));
    }

    public static void remove(String[] res, String s) {
        int index = -1;
        for (int i = 0; i < res.length; i++) {
            if (res[i].equals(s)) {
                index = i;
            }
        }
        if (index != -1) {
            for (int i = index + 1; i < res.length; i++) {
                res[i - 1] = res[i];
            }
        }
    }

    public static int search(String[] res, String s) {
        for (int i = 0; i < res.length; i++) {
            if (s.equals(res[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String getDecimalPlace(boolean decimalPlaceFormat, String decimalPlaceStr) {
        String decimalPlace = "0.";
        if (decimalPlaceFormat) {
            int count = Integer.parseInt(decimalPlaceStr.substring(4, 5));
            while (count > 0) {
                decimalPlace = decimalPlace + "0";
                count--;
            }
        } else {
            decimalPlace = "0.00000";
        }
        return decimalPlace;
    }

    /**
     * @param str
     * @return a string wrap with double quotes. for example, hello would be return "hello"
     */
    public static String wrapWithDoubleQuotes(String str) {
        return "\"" + str + "\"";
    }

    public static boolean isNumeric(String str) {
        if (!isValid(str)) {
            return false;
        }

        for (int i = 0, len = str.length(); i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String setToString(Set<String> stringList) {
        return setToString(stringList, ",");
    }

    public static String setToString(Set<String> stringList, String separator) {
        if (stringList == null) {
            return null;
        }
        if (separator == null) {
            separator = ",";
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(separator);
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    public static String[] splitWithRegex(String str, String regex) {
        if (str == null) {
            return new String[0];
        }
        return str.split(regex);
    }

}
