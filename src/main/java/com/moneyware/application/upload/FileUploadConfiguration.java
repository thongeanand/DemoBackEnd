package com.moneyware.application.upload;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.MessageHandler;
import org.springframework.expression.common.LiteralExpression;

import java.time.LocalDateTime;

@Configuration
public class FileUploadConfiguration {

    @Bean
    public DefaultSftpSessionFactory gimmeFactory(){
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost("0.0.0.0");
        factory.setPort(22);
        factory.setAllowUnknownKeys(true);
        factory.setUser("anand");
        factory.setPassword("password123");
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "uploadfile")
    MessageHandler uploadHandler(DefaultSftpSessionFactory factory){
        SftpMessageHandler messageHandler = new SftpMessageHandler(gimmeFactory());
        messageHandler.setRemoteDirectoryExpression(new LiteralExpression("/upload/topsecret"));
        messageHandler.setFileNameGenerator(message -> String.format("mytextfile_%s.txt", LocalDateTime.now()));
        return messageHandler;
    }
}
