package com.smart.smartmanager.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.relation.RoleList;

import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User implements UserDetails{

    @Id
    private String userId;
    @Column(name="user_name", nullable=false)
    private String name;
    @Column(unique=true, nullable=false)
    private String email;
    @Getter(value=AccessLevel.NONE)
    private String password;
    @Column(length=10000)
    private String about;
    private String profilePicture;
    private String phoneNumber;
    // info

    @Getter(value=AccessLevel.NONE)
    private boolean enabled=true;
    private boolean emailVerified=false;
    private boolean phoneNumberlVerified=false;
    //How are user sign up (GOOGLE, SELF, FACEBOOK, TWITTER, LINKEDIN, GITHUB)
    @Enumerated(value=EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;
    @OneToMany(mappedBy="user", cascade= CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval=true)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> rolesList = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // List of roles {user , admin}
        Collection<SimpleGrantedAuthority> roles = rolesList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
