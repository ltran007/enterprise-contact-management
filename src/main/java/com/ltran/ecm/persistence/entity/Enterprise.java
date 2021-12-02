package com.ltran.ecm.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
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
    private Set<Contact> contacts;

    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.getEnterprises().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enterprise enterprise = (Enterprise) o;
        return id == enterprise.id
                && Objects.equals(address, enterprise.address)
                && Objects.equals(tvaNumber, enterprise.tvaNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, tvaNumber);
    }

}
