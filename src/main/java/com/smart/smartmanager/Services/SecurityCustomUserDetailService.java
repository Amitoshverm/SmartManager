package com.smart.smartmanager.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smart.smartmanager.Repositories.UserRepository;


@Service
public class SecurityCustomUserDetailService implements UserDetailsService{

    private UserRepository userRepository;

    public SecurityCustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }

}
