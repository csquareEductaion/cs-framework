package com.csquare.framework.message;

import java.io.Serializable;


public class AuditMessage implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 7995837576084765930L;

    private String messageType;
    private String requestURL;
    private String queryString;
    private String userAgent;
    private Long startTime;
    private Long endTime;
    private String user;
    private String clientIP;

    private String exception;

    public String getMessageType() {

        return messageType;
    }

    public void setMessageType(String messageType) {

        this.messageType = messageType;
    }

    public String getRequestURL() {

        return requestURL;
    }

    public void setRequestURL(String requestURL) {

        this.requestURL = requestURL;
    }

    public String getQueryString() {

        return queryString;
    }

    public void setQueryString(String queryString) {

        this.queryString = queryString;
    }

    public String getUserAgent() {

        return userAgent;
    }

    public void setUserAgent(String userAgent) {

        this.userAgent = userAgent;
    }

    public Long getStartTime() {

        return startTime;
    }

    public void setStartTime(Long startTime) {

        this.startTime = startTime;
    }

    public Long getEndTime() {

        return endTime;
    }

    public void setEndTime(Long endTime) {

        this.endTime = endTime;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {

        this.user = user;
    }

    public String getClientIP() {

        return clientIP;
    }

    public void setClientIP(String clientIP) {

        this.clientIP = clientIP;
    }

    public String getException() {

        return exception;
    }

    public void setException(String exception) {

        this.exception = exception;
    }
}
