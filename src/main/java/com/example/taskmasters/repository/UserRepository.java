package com.example.taskmasters.repository;

import com.example.taskmasters.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
