package com.csquare.framework.exception.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.csquare.framework.exception.BaseException;
import com.csquare.framework.exception.ValidationException;
import com.csquare.framework.util.StringUtil;


public class RestExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(RestExceptionHandler.class);

    /**
     * Method to handle IOException
     *
     * @param req
     *            - The HttpServletRequest
     * @param ex
     *            - The IOException
     * @return errorInfo - The ErrorInfo
     */
    @ExceptionHandler(IOException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleIOException(HttpServletRequest req, IOException ex) {

        logError(req, ex);

        String errorMessage = ex.getMessage();
        String errorURL = req.getRequestURL().toString();
        return new ErrorInfo(errorURL, errorMessage);
    }
    

    /**
     * Method to handle Exception
     *
     * @param req
     *            - The HttpServletRequest
     * @param ex
     *            - The Exception
     * @return errorInfo - The ErrorInfo
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorInfo handleValidationException(HttpServletRequest req, ValidationException ex) {

        logError(req, ex);

        String errorMessage = ex.getMessage();
        String errorURL = req.getRequestURL().toString();
        return new ErrorInfo(errorURL, errorMessage);
    }

    /**
     * Method to handle BaseException
     *
     * @param req
     *            - The HttpServletRequest
     * @param ex
     *            - The BaseException
     * @return errorInfo - The ErrorInfo
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleBaseException(HttpServletRequest req, BaseException ex) {

        logError(req, ex);

        String errorMessage = ex.getMessage();
        String errorURL = req.getRequestURL().toString();
        return new ErrorInfo(errorURL, errorMessage);
    }

    /**
     * Method to handle Exception
     *
     * @param req
     *            - The HttpServletRequest
     * @param ex
     *            - The Exception
     * @return errorInfo - The ErrorInfo
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleException(HttpServletRequest req, Exception ex) {

        logError(req, ex);

        String errorMessage = ex.getMessage();
        String errorURL = req.getRequestURL().toString();
        return new ErrorInfo(errorURL, errorMessage);
    }

    /**
     * Method to handle Throwable
     *
     * @param req
     *            - The HttpServletRequest
     * @param ex
     *            - The Throwable
     * @return errorInfo - The ErrorInfo
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleThrowable(HttpServletRequest req, Throwable ex) {

        logError(req, ex);

        String errorMessage = ex.getMessage();
        String errorURL = req.getRequestURL().toString();
        return new ErrorInfo(errorURL, errorMessage);
    }

    // ----------------------------------------------------------------------------------------
    /**
     * Method to log exception
     *
     * @param req - The HttpServletRequest
     * @param ex - The Throwable
     */
    private void logError(HttpServletRequest req, Throwable ex) {

        String requestInfo = "";
        try {
            String userAgent = req.getHeader("User-Agent");
            String method = req.getMethod();
            String requestURL = req.getRequestURL().toString();
            String localAddress = req.getLocalAddr();

            requestInfo = StringUtil.append("Method: ", method, "\nLocalAddress: ", localAddress, "\nRequestURL: ", requestURL);

            requestInfo = StringUtil.append(requestInfo, "\nUser-Agent: ", userAgent);

            String queryString = req.getQueryString();
            requestInfo = StringUtil.append(requestInfo, "\nQueryString: ", queryString);
            // String body = getRequestBody(req);
            // requestInfo = StringUtil.append(requestInfo, "\n----- Body ------------------------------------------------------------------\n");
            // requestInfo = StringUtil.append(requestInfo, body);
        } catch (Throwable e) {
            // Do Nothing
        } finally {
            LOGGER.error(requestInfo, ex);
        }
    }
}