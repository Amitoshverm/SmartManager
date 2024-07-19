package com.smart.smartmanager.Services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.smartmanager.Entity.User;
import com.smart.smartmanager.Exceptions.ResourceNotFoundException;
import com.smart.smartmanager.Repositories.UserRepository;
import com.smart.smartmanager.Services.UserService;
import com.smart.smartmanager.helper.AppConstants;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set role

        user.setRolesList(List.of(AppConstants.ROLE_USER));

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
       
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepository.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found "));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setAbout(user.getAbout());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePicture(user.getProfilePicture());
        user2.setEnabled(user.isEnabled());
        user2.setPhoneNumberlVerified(user.isPhoneNumberlVerified());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        User savedUser = userRepository.save(user2);
        return Optional.ofNullable(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found "));

        userRepository.delete(user);
    }

    @Override
    public boolean ifUserPresent(String id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public boolean ifUserPresentByName(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


}
