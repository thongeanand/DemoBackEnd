package com.moneyware.application.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.moneyware.application.util.Constants.JWT_SECRET;
import static com.moneyware.application.util.Constants.JWT_USER;

@SpringBootTest
public class JwtUserDetailsServiceTests {


    @Test
    public void testLoadUserByUsername() {
        UserDetailsService userDetailsService = new JwtUserDetailsService();
        UserDetails userDetails =  userDetailsService.loadUserByUsername(JWT_USER);

        Assert.assertEquals(userDetails.getPassword() , JWT_SECRET);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByInvalidUsername() {
        UserDetailsService userDetailsService = new JwtUserDetailsService();
        userDetailsService.loadUserByUsername("Invalid UserName");
    }
}
