package com.vunh.repository;

import com.vunh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User WHERE username = :username")
    Optional<User> findByUsername(String username);
}
