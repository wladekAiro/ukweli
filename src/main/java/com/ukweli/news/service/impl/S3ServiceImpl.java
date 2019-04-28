package com.ukweli.news.service.impl;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.json.JSONObject;
import com.ukweli.news.config.AwsConfig;
import com.ukweli.news.service.S3Service;
import com.ukweli.news.utils.FileStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3ServiceImpl implements S3Service {
    private static final Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);

    private static final String FOLDER_SUFFIX = "/";

    @Autowired AwsConfig awsConfig;
    @Autowired Environment env;

    @Override
    public FileStream getAssetByName(String path , String name)
            throws FileNotFoundException {
        logger.info("Get Asset By name Method called by {} " , name);
        AmazonS3Client s3 = new AmazonS3Client(
                new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey()));
        s3.setEndpoint(awsConfig.getBaseUrl());
        s3.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));

        logger.info("Get s3Path()  {} " , getS3Path(path));
        S3Object obj = s3.getObject(new GetObjectRequest(awsConfig.getBucket(), getS3Path(path) + name));
        return new FileStream(obj.getObjectContent(), obj.getObjectMetadata().getContentLength());
    }

    @Override
    public List<String> getAssetList(String path) {
        AmazonS3Client s3 = new AmazonS3Client(
                new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey()));
        s3.setEndpoint(awsConfig.getBaseUrl());
        s3.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
        List<String> result = new ArrayList<>();
        ObjectListing objList = s3.listObjects(awsConfig.getBucket(), getS3Path(path));
        for (S3ObjectSummary summary:objList.getObjectSummaries()) {
            //ignore folders
            if(! summary.getKey().endsWith(FOLDER_SUFFIX)){
                result.add(summary.getKey().substring(path.length()));
            }
        }

        return result;
    }

    @Override
    public void putAsset(String path, String assetName, InputStream asset) {
        logger.info(" Uploading asset ");
        AmazonS3Client s3 = new AmazonS3Client(
                new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey()));
        s3.setEndpoint(awsConfig.getBaseUrl());
        s3.setRegion(Region.getRegion(Regions.EU_WEST_1));
        s3.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(((ByteArrayInputStream) asset).available());
        PutObjectResult result = s3.putObject(new PutObjectRequest(awsConfig.getBucket(), assetName, asset, meta)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        logger.debug(" aws upload result : {} " , new JSONObject(result));
    }

    private String getS3Path(String path) {
        //remove root path: /
        if (path.startsWith(FOLDER_SUFFIX)) {
            path = path.substring(1);
        }

        return path + FOLDER_SUFFIX;
    }
}
