package com.thesi.adapter.repository;

import com.thesi.adapter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Add custom methods if needed
}
