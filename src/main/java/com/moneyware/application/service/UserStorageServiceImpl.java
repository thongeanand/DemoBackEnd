package com.moneyware.application.service;

import com.moneyware.application.model.User;
import com.moneyware.application.repository.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStorageServiceImpl implements UserStorageService {

    @Autowired
    UserDBRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }
}
