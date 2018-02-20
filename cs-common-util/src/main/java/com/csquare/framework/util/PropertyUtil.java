package com.csquare.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.csquare.framework.util.SystemUtil.SystemKey;


public enum PropertyUtil {
    INSTANCE;

    private Properties prop = new Properties();

    private PropertyUtil() throws RuntimeException {

    }

    private InputStream getPropertyIS(String fileName) throws FileNotFoundException {

        InputStream input = null;
        try {
            String appConfigPath = SystemUtil.getEnv(SystemKey.CS_APPCONFIG_PATH);
            input = new FileInputStream(appConfigPath + "/" + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    /**
     * Method to provide region on which application is hosted. Every system
     * must set CodeXRegion as parameter with value like eu-west-1.amazonaws.com
     *
     * @param key
     *            - The String
     * @return value - The String
     */
    public void init(String fileName) {

        InputStream input = null;
        try {
            input = getPropertyIS(fileName);
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            CommonUtil.close(input);
        }

    }

    /**
     * Method to provide region on which application is hosted. Every system
     * must set CodeXRegion as parameter with value like eu-west-1.amazonaws.com
     *
     * @param key
     *            - The String
     * @return value - The String
     */
    public String getProperty(String key) {

        String value = prop.getProperty(key);
        return value;
    }
}
