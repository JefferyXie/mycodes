package com.myjava.util;

public class IntegerUtil {
    public static int parse(Object value) {
        if (value == null) {
            return 0;
        } else if (value.getClass() == Integer.class) {
            return (Integer) value;
        } else if (value.getClass() == String.class) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                double val = DoubleUtil.parse(value);
                if (DoubleUtil.isValid(val)) {
                    return IntegerUtil.parse(val);
                }
            }
        } else if (value.getClass() == Double.class) {
            try {
                Double val = (Double) value + 0.5;
                return val.intValue();
            } catch (NumberFormatException e) {
                // e.printStackTrace();
            }
        }

        return 0;
    }

    public static String formatToBigNumber(int num) {
        String[] bigNumber = { "", "K", "M", "B", "T" };
        int level = 0;
        while (num >= 1000 || num <= -1000) {
            level++;
            num /= 1000;
        }

        return num + bigNumber[level];
    }
}
