package com.moneyware.application.upload;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.io.File;

import static com.moneyware.application.util.Constants.GATEWAY_CHANNEL;

@MessagingGateway
public interface UploadMessagingGateway {

    @Gateway(requestChannel = GATEWAY_CHANNEL)
    public void uploadFile(File file);

}
