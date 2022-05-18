package com.moneyware.application.controller;


import com.moneyware.application.service.FileStorageServiceImpl;
import com.moneyware.application.upload.UploadMessagingGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.moneyware.application.Utility.dummyFileDB;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UploadMessagingGateway gateway;
    @Mock
    private FileStorageServiceImpl service;

    @InjectMocks
    FileController controller;


    @Test
    public void testUploadFile() throws Exception {

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        Mockito.doNothing().when(gateway).uploadFile(any(File.class));
        Mockito.when(service.store(any(MultipartFile.class), anyString(), anyInt())).thenReturn(dummyFileDB());

        this.mockMvc.perform(multipart("/file/upload").file(file).param("type", "KYC").param("customerId", "1001"))
                .andExpect(status().isOk());

    }
}
