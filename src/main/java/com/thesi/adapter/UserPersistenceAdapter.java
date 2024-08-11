package com.thesi.adapter;

import org.springframework.stereotype.Service;

@Service
public class UserPersistenceAdapter {
    public String getById(String id) {
        throw new RuntimeException();
    }
}
