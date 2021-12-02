package com.ltran.ecm.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "contact")
@Table(name = "CONTACT")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "TVA_NUMBER", unique = true)
    private String tvaNumber;

    @ManyToMany(targetEntity = Enterprise.class)
    private List<Enterprise> enterprises;

}
