package com.smart.smartmanager.Services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.smart.smartmanager.Entity.Contact;
import com.smart.smartmanager.Entity.User;
import com.smart.smartmanager.Exceptions.ResourceNotFoundException;
import com.smart.smartmanager.Repositories.ContactRepository;
import com.smart.smartmanager.Services.ContactService;


@Service
public class ContactServiceImpl implements ContactService{

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact saveContact(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepository.save(contact);
    }

    @Override
    public Contact UpdateContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("contact not found with id "+ id));
    }

    @Override
    public void deleteContact(String id) {
        var contact = contactRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("contact not found with id "+ id));

        contactRepository.delete(contact);

    }

    @Override
    public List<Contact> search(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepository.findByUserId(userId);
    }

    @Override
    public List<Contact> getByUser(User user) {
        return contactRepository.findByUser(user);
    }

}
