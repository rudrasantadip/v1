package com.biologix.v1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biologix.v1.entities.User;

public interface UserRepo extends JpaRepository<User,Long> 
{
    @Query(value = "SELECT u FROM User u where u.emailAddress = :email")
    Optional<User> findUserByEmail(String email);
}
