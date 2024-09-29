package com.thesi.adapter;

import com.thesi.adapter.entity.User;
import com.thesi.adapter.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Objects;
@Testcontainers
@DataJpaTest
@Import({UserPersistenceAdapter.class})
class UserPersistenceAdapterTest {

    @Container
    static MySQLContainer<?> mySQLContainer =
      new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"));

    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;

    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

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
}