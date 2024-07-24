package com.example.taskmasters.repository;


import com.example.taskmasters.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Users findByPhoneNumber(String phoneNumber);
}
