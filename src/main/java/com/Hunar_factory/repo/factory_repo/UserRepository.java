package com.Hunar_factory.repo.factory_repo;

import com.Hunar_factory.model.factory.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);
    Optional<User> findByEmail(String email);

    User findByVerificationCode(String verificationCode);

    Optional<User> findByUsername(String username);
}
