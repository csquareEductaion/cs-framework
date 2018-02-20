package com.csquare.framework.exception;

public class BaseException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = -1653648475576711854L;

    /** Variable represents the Error message */
    private String errorMessage = null;

    /**
     * Constructor of BaseException class
     *
     * @param message - The String
     */
    public BaseException(String message) {

        super(message);
        this.errorMessage = message;
    }

    /**
     * Constructor of BaseException class
     *
     * @param exception - The Exception
     */
    public BaseException(Exception exception) {

        super(exception);
        this.errorMessage = exception.getMessage();
    }

    /**
     * Constructor of BaseException class
     *
     * @param message - The String
     * @param exception - The Exception
     */
    public BaseException(String message, Exception exception) {

        super(message, exception);
        this.errorMessage = message;
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