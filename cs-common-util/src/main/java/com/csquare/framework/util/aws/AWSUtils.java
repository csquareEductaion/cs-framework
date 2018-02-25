package com.csquare.framework.util.aws;

import java.io.IOException;
import java.util.ResourceBundle;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.csquare.framework.util.StringUtil;


/**
 * Custom class for AWS infrastructure
 *
 * @copyright Copyright (c) Akoni. All Right Reserved.
 * @author Akoni
 */
public abstract class AWSUtils {

    private ResourceBundle bundle;
    private String envName;
    private String appStoreBucketName;
    private String appRegion;
    private BasicAWSCredentials credentials;

    /**
     * Constructor of AWSUtils class
     *
     * @throws IOException - The IOException
     */
    public AWSUtils() throws IOException {
        if (null == bundle) {
            bundle = ResourceBundle.getBundle("application");
        }
    }

    /**
     * Method to provide environment name. Method may return dev/test/prod based on the environment on which code is deployed.
     * Every system must set env_name as parameter with value dev/test/prod
     *
     * @return envName - The String
     */
    public String getEnvironment() {

        envName = System.getProperty("env_name");
        if (null == envName || envName.isEmpty()) {
            envName = System.getenv("env_name");
        }

        if (null == envName || envName.isEmpty()) {
            envName = "dev";
        }

        envName = StringUtil.append("-", envName);
        return envName;
    }

    /**
     * Method to provide AWS credentials
     *
     * @return credentials - The AWSCredentials
     * @throws IOException - The IOException
     */
    protected AWSCredentials credentials() throws IOException {

        if (null != credentials) {
            return credentials;
        }
        String accessKey = bundle.getString("aws_access_key_id");
        String secretKey = bundle.getString("aws_secret_access_key");

        credentials = new BasicAWSCredentials(accessKey, secretKey);
        return credentials;
    }

    /**
     * Method to provide S3 bucket name based on system environment variable.
     * Every system must set AkoniAppstoreBucket as parameter with value akoni-appstore-dev, akoni-appstore-test, akoni-appstore-prod
     *
     * @return appStoreBucketName - The String
     */
    public String getS3Bucket() {

        appStoreBucketName = System.getProperty("AkoniAppstoreBucket");
        if (null == appStoreBucketName || appStoreBucketName.isEmpty()) {
            appStoreBucketName = System.getenv("AkoniAppstoreBucket");
        }

        if (null == appStoreBucketName || appStoreBucketName.isEmpty()) {
            appStoreBucketName = "akoni-appstore-dev";
        }
        return appStoreBucketName;
    }

    /**
     * Method to provide region on which application is hosted.
     * Every system must set AkoniRegion as parameter with value like eu-west-1.amazonaws.com
     *
     * @return appRegion - The String
     */
    protected String getRegion() {

        appRegion = System.getProperty("AkoniRegion");
        if (null == appRegion || appRegion.isEmpty()) {
            appRegion = System.getenv("AkoniRegion");
        }

        if (null == appRegion || appRegion.isEmpty()) {
            appRegion = "eu-west-1.amazonaws.com";
        }
        return appRegion;
    }

    /**
     * Method to provide region on which application is hosted.
     * Every system must set AkoniRegion as parameter with value like eu-west-1.amazonaws.com
     *
     * @param key - The String
     * @return value - The String
     */
    public String getProperty(String key) {

        String value = bundle.getString(key);
        return value;
    }

}