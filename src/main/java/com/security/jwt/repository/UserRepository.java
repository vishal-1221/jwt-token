package com.security.jwt.repository;

import com.security.jwt.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUsername(String username);
}
