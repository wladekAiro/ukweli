package com.ukweli.news.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    public String uploadImage(MultipartFile multipartFile);
}
