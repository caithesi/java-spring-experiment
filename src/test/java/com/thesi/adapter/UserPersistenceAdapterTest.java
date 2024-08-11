package com.thesi.adapter;

import com.thesi.adapter.entity.User;
import com.thesi.adapter.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Objects;

@DataJpaTest(properties = {
  "spring.datasource.url=jdbc:h2:mem:testdb",
  "spring.jpa.hibernate.ddl-auto=create-drop"
})
//@SpringBootTest
@Import({UserPersistenceAdapter.class})
class UserPersistenceAdapterTest {
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
}