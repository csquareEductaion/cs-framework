package com.csquare.framework.message;

import java.io.Serializable;


public class MeterMessage implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 7995837576084765930L;

    private String type;
    private String fromAddress;
    private String fromEmail;
    private String bccAddress;

    private String body;

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getFromAddress() {

        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {

        this.fromAddress = fromAddress;
    }

    public String getFromEmail() {

        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {

        this.fromEmail = fromEmail;
    }

    public String getBccAddress() {

        return bccAddress;
    }

    public void setBccAddress(String bccAddress) {

        this.bccAddress = bccAddress;
    }

    public String getBody() {

        return body;
    }

    public void setBody(String body) {

        this.body = body;
    }

}
