package com.example.taskmasters.repository;


import com.example.taskmasters.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);


//    Users findBy(String phoneNumber);

    Users findByPhoneNumber(String phoneNumber);
}
