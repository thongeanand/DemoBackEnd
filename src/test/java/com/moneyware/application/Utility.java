package com.moneyware.application;

import com.moneyware.application.model.FileDB;

import java.util.Date;

public class Utility {

    public static FileDB dummyFileDB() {
        return new FileDB("hello.txt", 10000, new Date().toString(), 1001, "KYC", "C", "".getBytes());
    }
}
