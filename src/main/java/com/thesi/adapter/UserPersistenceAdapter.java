package com.thesi.adapter;

import com.thesi.adapter.entity.User;
import com.thesi.adapter.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPersistenceAdapter {
    private final UserRepository userRepository;

    public Optional<User> getById(long id) {
       return userRepository.findById(id);
    }
}
