package com.smart.smartmanager.Services;

import java.util.List;
import java.util.Optional;

import com.smart.smartmanager.Entity.User;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUser(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean ifUserPresent(String id);
    boolean ifUserPresentByName(String email);
    List<User> getAllUser();

}
