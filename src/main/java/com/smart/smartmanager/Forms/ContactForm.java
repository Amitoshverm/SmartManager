package com.smart.smartmanager.Forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String description;
    private boolean favorite;
    private String linkedInLink;
}