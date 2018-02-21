package com.csquare.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.csquare.framework.util.SystemUtil.SystemKey;


public enum PropertyUtil {

    /* API_GATEWAY */
    API_GATEWAY("cs-api-gateway-app.properties"),
    /* LEAD_MGT */
    LEAD_MGT("cs-lead-mgt-app.properties"),
    /* STUDENT_MGT */
    STUDENT_MGT("cs-student-mgt-app.properties"),
    /* TUTOR_MGT */
    TUTOR_MGT("cs-tutor-mgt-app.properties"),
    /* REF_MGT */
    REF_MGT("cs-ref-mgt-app.properties"),
    /* USER_MGT */
    USER_MGT("cs-user-mgt-app.properties"),
    /* COMMUNICATION_MGT */
    COMMUNICATION_MGT("cs-communication-mgt-app.properties");

    private Properties prop = new Properties();
    /** value **/
    private final String value;

    private PropertyUtil(String value) throws RuntimeException {
        this.value = value;
    }

    /**
     * Method to provide region on which application is hosted. Every system
     * must set CodeXRegion as parameter with value like eu-west-1.amazonaws.com
     *
     * @param key
     *            - The String
     * @return value - The String
     */
    public void init() {

        String fileName = this.value;
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
     * @param key - The String
     * @return value - The String
     */
    public String getString(String key) {

        String value = prop.getProperty(key);
        return value;
    }

    /**
     * Method to provide region on which application is hosted. Every system
     * must set CodeXRegion as parameter with value like eu-west-1.amazonaws.com
     *
     * @param key - The String
     * @return value - The String
     */
    public Integer getInteger(String key) {

        String value = prop.getProperty(key);
        return StringUtil.toInt(value);
    }

    /**
     * Method to provide region on which application is hosted. Every system
     * must set CodeXRegion as parameter with value like eu-west-1.amazonaws.com
     *
     * @param key - The String
     * @return value - The String
     */
    public Boolean getBoolean(String key) {

        String value = prop.getProperty(key);
        return StringUtil.toBoolean(value);
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
}