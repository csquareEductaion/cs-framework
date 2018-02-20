package com.csquare.framework.exception.handler;

public class ErrorInfo {

    /** errorURL */
    private String errorURL;

    /** errorMessage */
    private String errorMessage;

    /**
     * Constructor of ErrorInfo class
     *
     * @param errorURL - The String
     * @param errorMessage - THe String
     */
    public ErrorInfo(String errorURL, String errorMessage) {
        this.errorURL = errorURL;
        this.errorMessage = errorMessage;
    }

    /**
     * Method to get error URL
     *
     * @return errorURL - The String
     */
    public String getErrorURL() {

        return errorURL;
    }

    /**
     * Method to set errorURL
     *
     * @param errorURL - The String
     */
    public void setErrorURL(String errorURL) {

        this.errorURL = errorURL;
    }

    /**
     * Method to get error message
     *
     * @return errorMessage - The String
     */
    public String getErrorMessage() {

        return errorMessage;
    }

    /**
     * Method to set error message
     *
     * @param errorMessage - The String
     */
    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }
}