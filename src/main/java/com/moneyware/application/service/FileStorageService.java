package com.moneyware.application.service;

import com.moneyware.application.model.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    public FileDB store(MultipartFile file, String documentType, int customerId) throws IOException;
}
