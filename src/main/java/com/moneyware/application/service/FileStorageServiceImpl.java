package com.moneyware.application.service;

import com.moneyware.application.model.FileDB;
import com.moneyware.application.repository.FileDBRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.moneyware.application.util.Constants.COMPLETED_STATUS;

@Service
public class FileStorageServiceImpl implements FileStorageService{
  @Autowired
  private FileDBRepository fileDBRepository;

   public FileDB store(@NonNull MultipartFile file, String documentType, int customerId) throws IOException {
     String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    FileDB fileDB = new FileDB(fileName, file.getSize(), new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
            customerId, documentType, COMPLETED_STATUS, file.getBytes());


    return fileDBRepository.save(fileDB);
  }
}
