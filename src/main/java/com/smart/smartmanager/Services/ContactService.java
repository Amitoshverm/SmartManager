package com.smart.smartmanager.Services;

import java.util.List;

import com.smart.smartmanager.Entity.Contact;
import com.smart.smartmanager.Entity.User;

public interface ContactService {

    Contact saveContact(Contact contact);

    Contact UpdateContact(Contact contact);

    //get all contacts
    List<Contact> getAll();
    
    Contact getById(String id);

    void deleteContact(String id);

    //search contact 
    List<Contact> search(String name);


    List<Contact> getByUserId(String userId);
    List<Contact> getByUser(User user);
}
