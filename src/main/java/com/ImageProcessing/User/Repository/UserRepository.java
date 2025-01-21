package com.ImageProcessing.User.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ImageProcessing.User.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    Optional<User> findByEmail(String email);
}
