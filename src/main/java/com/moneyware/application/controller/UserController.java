package com.moneyware.application.controller;


import com.moneyware.application.message.ResponseMessage;
import com.moneyware.application.model.User;
import com.moneyware.application.service.UserStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.moneyware.application.util.Constants.CREATE_USER_ERROR_MESSAGE;
import static com.moneyware.application.util.Constants.CREATE_USER_SUCCESS_MESSAGE;

@RestController
@RequestMapping("/user")
@CrossOrigin()
public class UserController {
    @Autowired
    UserStorageServiceImpl service;
    @PostMapping("/createUser")
    public ResponseEntity<ResponseMessage> createUser(@RequestBody User user) {
        String message = "";
        try {
            User createdUser = service.saveUser(user);
            message = CREATE_USER_SUCCESS_MESSAGE + createdUser.getName();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = CREATE_USER_ERROR_MESSAGE + user.getName() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}

