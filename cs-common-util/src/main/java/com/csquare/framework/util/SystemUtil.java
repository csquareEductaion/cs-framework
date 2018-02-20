package com.csquare.framework.util;

public class SystemUtil {

    public static String getEnv(SystemKey key) {

        String keyString = key.name();
        String value = System.getenv(keyString);
        if (null == value || value.isEmpty()) {
            value = System.getProperty(keyString);
        }
        return value;
    }

    public static void setProperty(String key, String value) {

        System.setProperty(key, value);
    }

    public static String getProperty(String key) {

        return System.getProperty(key);
    }

    public enum SystemKey {
        CS_STORAGE_CONNECTION_STRING, CS_DB_CONNECTION_STRING, CS_APPCONFIG_PATH;

    }

}
