package com.ltran.ecm.rest;

import com.ltran.ecm.exception.ContactNotFoundException;
import com.ltran.ecm.exception.InvalidInputException;
import com.ltran.ecm.persistence.entity.Contact;
import com.ltran.ecm.persistence.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact create(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/{id}")
    public Contact update(@RequestBody Contact contact, @PathVariable Long id) {
        if (id == null || !id.equals(contact.getId())) {
            throw new InvalidInputException("Contact Id is not valid");
        }
        contactRepository.findById(id)
                .orElseThrow(ContactNotFoundException::new);
        return contactRepository.save(contact);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (id == null) {
            throw new InvalidInputException("Contact Id cannot be null");
        }
        contactRepository.findById(id)
                .orElseThrow(ContactNotFoundException::new);
        contactRepository.deleteById(id);
    }

    @GetMapping
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

}
