package com.csquare.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.csquare.framework.exception.BaseException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Custom class to hold error information
 *
 * @copyright Copyright (c) CodeX. All Right Reserved.
 * @author CodeX
 */
public class CommonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Method to check whether given List is empty or not
     *
     * @param value - The List<Object>
     * @return true/false - The boolean
     */
    public static boolean isEmpty(List<?> value) {

        if (null == value) {
            return true;
        }

        return value.isEmpty();
    }

    /**
     * Method to convert String value to boolean
     *
     * @param value - The String
     * @return true/false - THe boolean
     */
    public static String formatDouble(Double value) {

        DecimalFormat df = new DecimalFormat("###,###,###,###,##0.00");
        return df.format(value);
    }

    /**
     * Method to get Current Date
     *
     * @return currentDate - The String
     */
    public static String getCurrentDate1() {

        String datePattern = "dd-MM-yyyy";
        String utc = "UTC";
        Date date = new Date();

        DateFormat sdf = new SimpleDateFormat(datePattern);
        sdf.setTimeZone(TimeZone.getTimeZone(utc));
        sdf.setLenient(false);
        return sdf.format(date);
    }

    /**
     * Method to get Current Date
     *
     * @return currentDate - The String
     */
    public static String getCurrentDate() {

        String datePattern = "dd-MM-yyyy";
        String utc = "UTC";
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        DateFormat sdf = new SimpleDateFormat(datePattern);
        sdf.setTimeZone(TimeZone.getTimeZone(utc));
        sdf.setLenient(false);
        return sdf.format(date);
    }

    /**
     * Method to get Current Date
     *
     * @return currentDate - The String
     */
    public static String getCurrentMonth() {

        String date = getCurrentDate();
        String[] splitDate = date.split("-");
        return splitDate[1];
    }

    /**
     * Method to get Current Date
     *
     * @return currentDate - The String
     */
    public static String getCurrentYear() {

        String date = getCurrentDate();
        String[] splitDate = date.split("-");
        return splitDate[2];
    }

    /**
     * Method to get TimeStamp based on data provided
     *
     * @param timestamp - The long
     * @return timestamp - The String
     */
    public static String getTimeStamp(long timestamp) {

        String datePattern = "dd-MM-yyyy HH:mm:ss";
        String utc = "UTC";
        Date date = new Date(timestamp);

        DateFormat sdf = new SimpleDateFormat(datePattern);
        sdf.setTimeZone(TimeZone.getTimeZone(utc));
        sdf.setLenient(false);
        return sdf.format(date);
    }

    /**
     * This method is used to get the date as string format for the given datePattern and date.
     *
     * @param dateStr - The String
     * @param fromDateFormat - The String
     * @param toDateFormat - The String
     * @return formatedDate - The String
     * @throws ParseException - The ParseException
     */
    public static String formatDate(String dateStr, String fromDateFormat, String toDateFormat) throws ParseException {

        SimpleDateFormat dt = new SimpleDateFormat(fromDateFormat);
        Date date1 = dt.parse(dateStr);

        SimpleDateFormat dt1 = new SimpleDateFormat(toDateFormat);
        String formatedDate = dt1.format(date1);
        return formatedDate;
    }

    /**
     * Method to close any Object which can be closed
     *
     * @param closeable - The Closeable
     */
    public static void close(Closeable closeable) {

        if (null == closeable) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException ioe) {
            // Do Nothing
        }
    }

    /**
     * Method to flush any Object which can be flushed
     *
     * @param flushable - The Flushable
     */
    public static void flush(Flushable flushable) {

        if (null == flushable) {
            return;
        }

        try {
            flushable.flush();
        } catch (IOException ioe) {
            // Do Nothing
        }
    }

    /**
     * @param exc Exception
     * @return String
     */
    public static String getStackTrace(Exception exc) {

        StringBuilder sb = new StringBuilder();
        if (null == exc) {
            return sb.toString();
        }

        StackTraceElement[] stackTraceElements = exc.getStackTrace();

        for (StackTraceElement element : stackTraceElements) {
            sb.append(element.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public static String toJson(Object value) {

        if (null == value) {
            return null;
        }

        String json = null;
        if (value instanceof String) {
            json = (String) value;
            return json;
        }

        try {
            json = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * @param valueType
     * @param json
     * @return
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    public static <T> T toPojo(Class<T> valueType, String json) {

        T value = null;
        if (null == json || json.isEmpty()) {
            return value;
        }

        if (String.class.isAssignableFrom(valueType)) {
            value = (T) json;
            return value;
        }

        try {
            value = mapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * @param valueType
     * @param json
     * @return
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    public static <T> T toPojo(Class<?> mainClass, Class<?> elementClass, String json) {

        T value = null;
        if (null == json || json.isEmpty()) {
            return value;
        }

        try {
            JavaType type = mapper.getTypeFactory().constructParametricType(mainClass, elementClass);
            value = mapper.readValue(json, type);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * Copy an object
     *
     * @param obj Object
     * @return newObj
     * @throws BaseException
     */
    public static Object deepCopy(Object obj) throws BaseException {

        if (null == obj) {
            return null;
        }

        Object newObj = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            // Write the object out to a byte array
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.flush();

            in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            newObj = in.readObject();
            in.close();
        } catch (IOException e) {
            throw new BaseException(e);
        } catch (ClassNotFoundException e) {
            throw new BaseException(e);
        } finally {
            close(out);
            close(bos);
            close(in);
        }

        return newObj;
    }

}