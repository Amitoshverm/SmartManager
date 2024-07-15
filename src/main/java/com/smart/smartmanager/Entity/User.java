package com.smart.smartmanager.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name="user")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    private String userId;
    @Column(name="user_name", nullable=false)
    private String name;
    @Column(unique=true, nullable=false)
    private String email;
    private String password;
    @Column(length=10000)
    private String about;
    
    private String profilePicture;
    private String phoneNumber;
    // info
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneNumberlVerified=false;


    //How are user sign up (GOOGLE, SELF, FACEBOOK, TWITTER, LINKEDIN, GITHUB)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy="user", cascade= CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval=true)
    private List<Contacts> contacts = new ArrayList<>();

    
}
