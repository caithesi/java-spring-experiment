package com.thesi.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

//@DataJpaTest
@SpringBootTest
@Import(UserPersistenceAdapter.class)
class UserPersistenceAdapterTest {
    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;

    @Test
    void getById() {
        userPersistenceAdapter.getById("10");
    }
}