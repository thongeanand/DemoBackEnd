package com.moneyware.application.controller;

import com.moneyware.application.message.ResponseMessage;
import com.moneyware.application.model.FileDB;
import com.moneyware.application.service.FileStorageService;
import com.moneyware.application.upload.UploadMessagingGateway;
import com.moneyware.application.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.moneyware.application.util.Constants.FILE_UPLOAD_ERROR_MESSAGE;
import static com.moneyware.application.util.Constants.FILE_UPLOAD_SUCCESS_MESSAGE;

@RestController
@RequestMapping("/file")
@CrossOrigin()
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private UploadMessagingGateway gateway;

    @PostMapping(value = "/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("type") String documentType,
                                                      @RequestParam("customerId") int customerId) {
        String message = "";
        try {
            FileDB fileDB = fileStorageService.store(file, documentType, customerId);

            message = FILE_UPLOAD_SUCCESS_MESSAGE + file.getOriginalFilename();
            File uploadFile = FileUtils.generateIndexFile(fileDB);
            gateway.uploadFile(uploadFile);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = FILE_UPLOAD_ERROR_MESSAGE + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
