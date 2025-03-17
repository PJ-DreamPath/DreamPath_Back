package com.korit.dreampath_back.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    private String rootPath;

    public String saveFile(String path, MultipartFile file) {

        if (file.isEmpty()) {
            return null;
        }

        String newFilename = null;
        try {
            String originalFilename = file.getOriginalFilename();
            newFilename = UUID.randomUUID().toString().replaceAll("-", "") + originalFilename;

            File newFilePath = new File(rootPath + "/" + path);
            if (!newFilePath.exists()) {
                newFilePath.mkdirs();;
            }

            File newFile = new File(rootPath + "/" + path + "/" + newFilename);
            file.transferTo(newFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFilename;
    }

    public void deleteFile(String path) {

        File file = new File(rootPath + "/" + path);

        if (file.exists()) {
            file.delete();
        }
    }
}
