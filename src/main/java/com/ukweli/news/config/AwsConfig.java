package com.ukweli.news.config;

import com.amazonaws.auth.AWSCredentials;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws")
public class AwsConfig {
    private String accessKey;
    private String secretKey;
    private String baseUrl;
    private String bucket;
    private AWSCredentials credentials;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public AWSCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(AWSCredentials credentials) {
        this.credentials = credentials;
    }
}
