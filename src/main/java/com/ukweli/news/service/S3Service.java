package com.ukweli.news.service;

import com.ukweli.news.utils.FileStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by achachiez on 16/12/14.
 */
public interface S3Service {
    public void putAsset(String path, String assetName, InputStream asset);
    public List<String> getAssetList(String path);
    public FileStream getAssetByName(String path, String name) throws FileNotFoundException , IOException;
}
