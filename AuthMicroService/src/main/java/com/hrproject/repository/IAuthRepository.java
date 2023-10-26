package com.hrproject.repository;

import com.hrproject.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthRepository extends JpaRepository<Auth, Long> {
    boolean existsByUsername(String username);

    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);
}
