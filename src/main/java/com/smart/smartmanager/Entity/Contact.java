package com.smart.smartmanager.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    private String id;

    @NotBlank(message="name is required")
    private String name;

    @Email(message = "invalid email")
    private String email;

    private String phoneNumber;

     @NotBlank(message="address is requrired")
    private String address;
    private String profilePic;
    private String description;
    private boolean favroite=false;
    private String linkedIn;
    // private List<String> socialLinks = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy="contact", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
    private List<SocialLinks> links = new ArrayList<>();
}
