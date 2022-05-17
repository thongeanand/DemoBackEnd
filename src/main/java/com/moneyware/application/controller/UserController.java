package com.moneyware.application.controller;


import com.moneyware.application.message.ResponseMessage;
import com.moneyware.application.model.User;
import com.moneyware.application.service.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserStorageService service;
    @PostMapping("/createUser")
    public ResponseEntity<ResponseMessage> createUser(@RequestBody User user) {
        String message = "";
        try {
            User createdUser = service.saveUser(user);
            message = "User Created successfully: " + createdUser.getName();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "Could not Created the User: " + user.getName() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}

