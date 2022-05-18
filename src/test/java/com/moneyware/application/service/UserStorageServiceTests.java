package com.moneyware.application.service;

import com.moneyware.application.model.User;
import com.moneyware.application.repository.UserDBRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class UserStorageServiceTests {

    @Mock
    UserDBRepository userDBRepository;

    @InjectMocks
    UserStorageService userStorageService = new UserStorageServiceImpl();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("test");
        user.setPassword("testPass");

        Mockito.when(userDBRepository.save(any(User.class))).thenReturn(user);
        User responseUser = userStorageService.saveUser(user);
        Assert.assertEquals( responseUser.getName(), user.getName());
    }
}
