package com.csquare.framework.exception;

public class ValidationException extends BaseException {

    /** serialVersionUID */
	private static final long serialVersionUID = 5655928714220503019L;

	/**
     * Constructor of BaseException class
     *
     * @param message - The String
     */
    public ValidationException(String message) {

        super(message);
    }

    /**
     * Constructor of BaseException class
     *
     * @param exception - The Exception
     */
    public ValidationException(Exception exception) {

        super(exception);
    }

    /**
     * Constructor of BaseException class
     *
     * @param message - The String
     * @param exception - The Exception
     */
    public ValidationException(String message, Exception exception) {

        super(message, exception);
    }

}