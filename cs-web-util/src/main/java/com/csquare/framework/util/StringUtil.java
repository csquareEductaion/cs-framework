package com.csquare.framework.util;

/**
 * Custom class to hold error information
 *
 * @copyright Copyright (c) CodeX. All Right Reserved.
 * @author CodeX
 */
public class StringUtil {

    /**
     * This method will append zero's provided string and the total length of the string with Zero's.
     *
     * @param strval Actual string to which zero to be padded.
     * @param columnLen Actual length after the string with append Zero's.
     * @return returns the string with append Zero's.
     */
    public static String zeroPad(String strval, int columnLen) {

        String strtmp = "";
        int leng = strval.length();
        int diff = columnLen - leng;

        for (int j = 0; j < diff; j += 1) {
            strtmp += "0";
        }

        strval = strtmp + strval;

        return strval;
    }

    /**
     * This method will append zero's provided string and the total length of the string with Zero's.
     *
     * @param strval Actual string to which zero to be padded.
     * @param columnLen Actual length after the string with appended Zero's.
     * @return returns the string with appended Zero's.
     */
    public static String emptyPad(String strval, int columnLen) {

        String strtmp = "";
        int leng = strval.length();
        int diff = columnLen - leng;

        for (int j = 0; j < diff; j += 1) {
            strtmp += " ";
        }

        strval = strval + strtmp;

        return strval;
    }

    /**
     * This method checks if given string is null/blank or not.It returns true if the string is null/blank else it
     * return false.
     *
     * @param str string - input string
     * @return boolean - true/false.
     */
    public static boolean nullOrBlank(String str) {

        if (null == str) {
            return true;
        }

        if (0 == str.length()) {
            return true;
        }

        return false;
    }

    /**
     * Method to log message in debug mode
     *
     * @param source - The StringBuilder
     * @param messages - The Object var args
     * @return source - The StringBuilder with concanited object
     */
    public static void append(StringBuilder source, Object... messages) {

        if (null == source || null == messages) {
            return;
        }

        for (Object s : messages) {
            source.append(s);
        }
    }

    /**
     * Method to log message in debug mode
     *
     * @param messages - The String var args
     * @return source - The String with concatenated object
     */
    public static String append(Object... messages) {

        if (null == messages) {
            return null;
        }

        StringBuilder source = new StringBuilder();
        for (Object s : messages) {
            source.append(s);
        }
        return source.toString();
    }

    /**
     * Method to check whether given String is empty or not
     *
     * @param value - The String
     * @return true/false - The boolean
     */
    public static boolean isEmpty(String value) {

        if (null == value) {
            return true;
        }

        boolean isEmpty = value.isEmpty();
        if (isEmpty) {
            return isEmpty;
        }

        return value.trim().isEmpty();
    }

    /**
     * Method to check whether given String is empty or not
     *
     * @param value - The String
     * @return true/false - The boolean
     */
    public static boolean isNotEmpty(String value) {

        if (null == value) {
            return false;
        }

        return !value.isEmpty();
    }

    /**
     * Method to trim String value
     *
     * @param value - The List<Object>
     * @return trimmed value - The String
     */
    public static String trim(String value) {

        if (null == value) {
            return value;
        }
        return value.trim();
    }

    /**
     * Method to check if two string values are equal
     *
     * @param value1 - The String
     * @param value2 - The String
     * @return true/false - THe boolean
     */
    public static boolean equals(String value1, String value2) {

        boolean doesMatch = false;

        if (isEmpty(value1)) {
            doesMatch = isEmpty(value2);
            return doesMatch;
        }

        if (!isEmpty(value2)) {
            doesMatch = value1.equals(value2);
        }

        return doesMatch;
    }

    /**
     * Method to check if two string values are equal
     *
     * @param value1 - The String
     * @param value2 - The String
     * @return true/false - THe boolean
     */
    public static boolean equalsIgnoreCase(String value1, String value2) {

        boolean doesMatch = false;

        if (isEmpty(value1)) {
            doesMatch = isEmpty(value2);
            return doesMatch;
        }

        if (!isEmpty(value2)) {
            doesMatch = value1.equalsIgnoreCase(value2);
        }

        return doesMatch;
    }

    /**
     * Method to convert String value to boolean
     *
     * @param value - The String
     * @return true/false - THe boolean
     */
    public static boolean toBoolean(String value) {

        if (null == value) {
            return false;
        }

        if ("true".equalsIgnoreCase(value)) {
            return true;
        }
        return false;
    }

    /**
     * Method to convert String value to boolean
     *
     * @param value - The String
     * @return true/false - THe boolean
     */
    public static Double toDouble(String value) {

        double doubleValue = 0.0;
        if (null == value) {
            return doubleValue;
        }
        try {
            doubleValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            doubleValue = 0.0;
        }
        return doubleValue;
    }

    /**
     * Method to convert String value to float
     *
     * @param value - The String
     * @return floatValue - The float
     */
    public static Integer toInt(String value) {

        Integer intValue = 0;
        if (null == value) {
            return intValue;
        }

        try {
            intValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            intValue = 0;
        }
        return intValue;
    }

    /**
     * Method to convert String value to float
     *
     * @param value - The String
     * @return floatValue - The float
     */
    public static Float toFloat(String value) {

        Float floatValue = 0.0f;
        if (null == value) {
            return floatValue;
        }

        try {
            floatValue = Float.parseFloat(value);
        } catch (NumberFormatException e) {
            floatValue = 0.0f;
        }
        return floatValue;
    }

    /**
     * Method to convert String value to long
     *
     * @param value - The String
     * @return longValue - The long
     */
    public static Long toLong(String value) {

        Long longValue = 0l;
        if (null == value) {
            return longValue;
        }

        try {
            longValue = Long.parseLong(value);
        } catch (NumberFormatException e) {
            longValue = 0l;
        }
        return longValue;
    }

}