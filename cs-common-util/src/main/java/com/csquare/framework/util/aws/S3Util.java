package com.csquare.framework.util.aws;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.csquare.framework.util.CommonUtil;


/**
 * Custom class for S3 bucket handler
 *
 * @copyright Copyright (c) Akoni. All Right Reserved.
 * @author Akoni
 */
public class S3Util extends AWSUtils {

    /** s3client */
    private AmazonS3 s3client = null;

    /**
     * Constructor of S3Util class
     *
     * @throws IOException - The IOException
     */
    public S3Util() throws IOException {
        super();
    }

    /**
     * Method to provide AmazonS3Client
     *
     * @return s3client - The AmazonS3
     * @throws IOException - The IOException
     */
    public AmazonS3 s3Client() throws IOException {

        if (null != s3client) {
            return s3client;
        }

        // try {
        // s3client = new AmazonS3Client();
        // return s3client;
        // } catch (Exception e) {
        s3client = new AmazonS3Client(credentials());
        return s3client;
        // }
    }

    /**
     * Method to create bucket on S3
     *
     * @param bucketName - The String
     * @throws Exception - The Exception
     */
    public void createBucket(String bucketName) throws Exception {

        s3Client().createBucket(bucketName);
    }

    /**
     * Method to delete bucket on S3
     *
     * @throws Exception - The Exception
     * @param bucketName - The String
     */
    public void deleteBucket(String bucketName) throws Exception {

        s3Client().deleteBucket(bucketName);
    }

    /**
     * Method to list bucket on S3
     *
     * @return buckets - The List<Bucket>
     * @throws Exception - The Exception
     */
    public List<Bucket> listBucket() throws Exception {

        List<Bucket> buckets = s3Client().listBuckets();
        return buckets;
    }

    /**
     * Method to upload file to S3
     *
     * @param bucketName - The String
     * @param remoteFilePath - The String
     * @param localFilePath - The String
     * @throws Exception
     * @throws Exception - The Exception
     */
    public void uploadFile(String bucketName, String remoteFilePath, String localFilePath) throws Exception {

        s3Client().putObject(new PutObjectRequest(bucketName, remoteFilePath, new File(localFilePath)));
    }

    /**
     * Method to delete file from S3
     *
     * @param bucketName - The String
     * @param filePath - The String
     * @throws Exception - THe Exception
     */
    public void deleteFile(String bucketName, String filePath) throws Exception {

        s3Client().deleteObject(bucketName, filePath);
    }

    /**
     * Method to create folder to S3
     *
     * @param bucketName - The String
     * @param folderName - The String
     * @throws IOException - The IOException
     */
    public void createFolder(String bucketName, String folderName) throws IOException {

        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by /
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, emptyContent, metadata);
        // send request to S3 to create folder
        s3Client().putObject(putObjectRequest);
    }

    /**
     * Method to download file from S3
     *
     * @param bucketName - The String
     * @param remoteFilePath - The String
     * @param localFilePath - The String
     * @throws Exception - The Exception
     */
    public void downloadFile(String bucketName, String remoteFilePath, String localFilePath) throws Exception {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            S3Object object = s3Client().getObject(new GetObjectRequest(bucketName, remoteFilePath));
            inputStream = object.getObjectContent();
            File file = new File(localFilePath);
            outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } finally {
            CommonUtil.flush(outputStream);
            CommonUtil.close(outputStream);
            CommonUtil.close(inputStream);
        }
    }

    /**
     * Method to download file from S3
     *
     * @param bucketName - The String
     * @param remoteFilePath - The String
     * @throws Exception - The Exception
     * @return inputStream - The InputStream
     * @throws IOException
     */
    public InputStream getObject(String bucketName, String remoteFilePath) throws IOException {

        GetObjectRequest objectRequest = new GetObjectRequest(bucketName, remoteFilePath);
        // objectRequest.setRange(0, 5000);
        S3Object object = s3Client().getObject(objectRequest);
        InputStream inputStream = object.getObjectContent();
        return inputStream;
    }
}