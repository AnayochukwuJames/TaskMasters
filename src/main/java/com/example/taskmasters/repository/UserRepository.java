package com.example.taskmasters.repository;


import com.example.taskmasters.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsernameIgnoreCase(String username);

    Users findByUsername(String email);

    Optional<Object> findByEmail(String userEmail);
}
