package com.ltran.ecm.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "enterprise")
@Table(name = "ENTERPRISE")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "TVA_NUMBER", nullable = false, unique = true)
    private String tvaNumber;

    @ManyToMany(targetEntity = Contact.class, mappedBy = "enterprises")
    private List<Contact> contacts;

    public void addContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            contact.getEnterprises().add(this);
        }
    }

}
