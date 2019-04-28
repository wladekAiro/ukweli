package com.ukweli.news.service.impl;

import com.ukweli.news.config.AwsConfig;
import com.ukweli.news.service.ImageUploadService;
import com.ukweli.news.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {
    private static final Logger log = LoggerFactory.getLogger(ImageUploadServiceImpl.class);

    private final AwsConfig awsConfig;
    private final S3Service s3Service;
    private final Environment env;

    @Autowired
    public ImageUploadServiceImpl(AwsConfig awsConfig, S3Service s3Service, Environment env) {
        this.awsConfig = awsConfig;
        this.s3Service = s3Service;
        this.env = env;
    }

    @Override
    @Transactional
    public String uploadImage(MultipartFile multipartFile) {
        String fullNameImagePath = null;
        byte[] bytes;

        try {
            byte[] array = new byte[9]; // length is bounded by 7
            new Random().nextBytes(array);
            String prefix = UUID.randomUUID().toString();
            String fileName = multipartFile.getOriginalFilename().replaceAll("\\s", "_");
            String name = prefix+"_"+fileName;
            bytes = multipartFile.getBytes();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            s3Service.putAsset(prefix,name,inputStream);
            fullNameImagePath = awsConfig.getBaseUrl()+"/"+
                    awsConfig.getBucket()+"/"+name;

            if (Objects.equals(env.getProperty("spring.profiles.active"), "prod")){
                fullNameImagePath= awsConfig.getBaseUrl()+"/"+
                        awsConfig.getBucket()+"/"+name;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullNameImagePath;
    }
}
