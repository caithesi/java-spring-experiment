package com.thesi.adapter;

import com.thesi.adapter.entity.User;
import com.thesi.adapter.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.Objects;

@DataJpaTest
@Import({UserPersistenceAdapter.class})
@ContextConfiguration(initializers = {MySQLContainerInitializer.class})
public class UserPersistenceAdapteWithFixturerTest {
    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getById() {
        var u = new User();
        u.setPassword("password");
        u.setUsername("username");
        var saved = userRepository.save(u);
        var expected = userPersistenceAdapter.getById(saved.getId()).get();
        assert Objects.equals(expected.getPassword(), u.getPassword());
        assert Objects.equals(expected.getUsername(), u.getUsername());

    }

    @Test
    void getById2() {
        var u1 = new User();
        u1.setPassword("password");
        u1.setUsername("username");
        var saved = userRepository.save(u1);
        var expected = userPersistenceAdapter.getById(saved.getId()).get();
        assert Objects.equals(expected.getPassword(), u1.getPassword());
        assert Objects.equals(expected.getUsername(), u1.getUsername());

        var count = userRepository.count();
        assert count == 1;

        var u2 = new User();
        u2.setPassword("password");
        u2.setUsername("username");
        var saved2 = userRepository.save(u2);
        var expected2 = userPersistenceAdapter.getById(saved2.getId()).get();
        assert Objects.equals(expected2.getPassword(), u1.getPassword());
        assert Objects.equals(expected2.getUsername(), u1.getUsername());
        var count2 = userRepository.count();
        assert count2 == 2;



    }
}
