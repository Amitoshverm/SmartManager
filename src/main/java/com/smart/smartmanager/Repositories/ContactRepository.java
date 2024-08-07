package com.smart.smartmanager.Repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.smartmanager.Entity.Contact;
import com.smart.smartmanager.Entity.User;


public interface ContactRepository extends JpaRepository<Contact,String>{

    List<Contact> findByUser(User user);

    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId")String userId);
}
