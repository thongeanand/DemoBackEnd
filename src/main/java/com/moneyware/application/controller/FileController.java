package com.moneyware.application.controller;

import com.moneyware.application.message.ResponseMessage;
import com.moneyware.application.model.FileDB;
import com.moneyware.application.service.FileStorageService;
import com.moneyware.application.upload.UploadMessagingGateway;
import com.moneyware.application.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private UploadMessagingGateway gateway;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("type") String documentType,
                                                      @RequestParam("customerId") int customerId) {
        String message = "";
        try {
            FileDB fileDB = fileStorageService.store(file, documentType, customerId);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            File uploadFile = FileUtils.generateIndexFile(fileDB);
            gateway.uploadFile(uploadFile);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
