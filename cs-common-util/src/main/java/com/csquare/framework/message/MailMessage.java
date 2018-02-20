package com.csquare.framework.message;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class MailMessage implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 7995837576084765930L;

    private String toAddress;
    private String ccAddress;
    private String bccAddress;

    private String subject;

    private String body;

    private String env;
    private String containerName;
    private String remoteFileName;

    private byte[] localFileData;
    private String fileNameDisplay;

    private String attachmentMimeType;

    private boolean isAttached = false;

    public String getToAddress() {

        return toAddress;
    }

    public void setToAddress(String toAddress) {

        this.toAddress = toAddress;
    }

    public String getCcAddress() {

        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {

        this.ccAddress = ccAddress;
    }

    public String getBccAddress() {

        return bccAddress;
    }

    public void setBccAddress(String bccAddress) {

        this.bccAddress = bccAddress;
    }

    public String getSubject() {

        return subject;
    }

    public void setSubject(String subject) {

        this.subject = subject;
    }

    public String getBody() {

        return body;
    }

    public void setBody(String body) {

        this.body = body;
    }

    public String getEnv() {

        return env;
    }

    public void setEnv(String env) {

        this.env = env;
    }

    public String getContainerName() {

        return containerName;
    }

    public void setContainerName(String containerName) {

        this.containerName = containerName;
    }

    public String getRemoteFileName() {

        return remoteFileName;
    }

    public void setRemoteFileName(String remoteFileName) {

        this.remoteFileName = remoteFileName;
    }

    public String getAttachmentMimeType() {

        return attachmentMimeType;
    }

    public void setAttachmentMimeType(String attachmentMimeType) {

        this.attachmentMimeType = attachmentMimeType;
    }

    public boolean isAttached() {

        return isAttached;
    }

    public void setAttached(boolean isAttached) {

        this.isAttached = isAttached;
    }

    public void setRemoteAttachment(String env, String containerName, String remoteFileName) {

        this.env = env;
        this.containerName = containerName;
        this.remoteFileName = remoteFileName;

        String[] fileNameSplit = remoteFileName.split("\\[###\\]");
        if (fileNameSplit.length == 2) {
            this.fileNameDisplay = fileNameSplit[1];
        } else {
            this.fileNameDisplay = remoteFileName;
        }
        this.attachmentMimeType = findAttachmentMimeType(remoteFileName);
        this.isAttached = true;
    }

    public void setLocalAttachment(String localFileURL) {

        try {
            Path path = Paths.get(localFileURL);

            byte[] data = Files.readAllBytes(path);
            this.localFileData = data;
        } catch (IOException e) {
            new RuntimeException(e);
        }
        this.env = "LOCAL";
        String[] fileNameSplit = localFileURL.split("\\[###\\]");
        if (fileNameSplit.length == 2) {
            this.fileNameDisplay = fileNameSplit[1];
        } else {
            this.fileNameDisplay = localFileURL;
        }
        this.attachmentMimeType = findAttachmentMimeType(localFileURL);
        this.isAttached = true;
    }

    public String getFileNameDisplay() {

        return fileNameDisplay;
    }

    public void setFileNameDisplay(String fileNameDisplay) {

        this.fileNameDisplay = fileNameDisplay;
    }

    public byte[] getLocalFileData() {

        return localFileData;
    }

    public void setLocalFileData(byte[] localFileData) {

        this.localFileData = localFileData;
    }

    /**
     * Method to check whether the file is of PDF type.
     *
     * @param fileUrl - The String
     * @return true/false - The boolean
     */
    public String findAttachmentMimeType(String fileUrl) {

        if (null == fileUrl || fileUrl.isEmpty()) {
            return null;
        }

        fileUrl = fileUrl.toLowerCase();
        if (fileUrl.endsWith(".pdf")) {
            return "application/pdf";
        }

        if (fileUrl.endsWith(".xls")) {
            return "application/vnd.ms-excel";
        }

        if (fileUrl.endsWith(".doc")) {
            return "application/msword";
        }

        if (fileUrl.endsWith(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }

        if (fileUrl.endsWith(".xlsx")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }

        if (fileUrl.endsWith(".docx")) {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        }

        if (fileUrl.endsWith(".pptx")) {
            return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
        }

        return "application/text";

    }

}
