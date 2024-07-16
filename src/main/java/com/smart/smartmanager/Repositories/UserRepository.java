package com.smart.smartmanager.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.smartmanager.Entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);;
}
