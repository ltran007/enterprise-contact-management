package com.ltran.ecm.rest;

import com.ltran.ecm.exception.ContactNotFoundException;
import com.ltran.ecm.exception.EnterpriseNotFoundException;
import com.ltran.ecm.exception.InvalidInputException;
import com.ltran.ecm.persistence.entity.Contact;
import com.ltran.ecm.persistence.entity.Enterprise;
import com.ltran.ecm.persistence.repository.ContactRepository;
import com.ltran.ecm.persistence.repository.EnterpriseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enterprises")
public class EnterpriseController {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Enterprise create(@RequestBody Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    @PutMapping("/{id}")
    public Enterprise update(@RequestBody Enterprise enterprise, @PathVariable Long id) {
        if (id == null | !id.equals(enterprise.getId())) {
            throw new InvalidInputException("Enterprise Id is not valid");
        }
        enterpriseRepository.findById(id)
                .orElseThrow(EnterpriseNotFoundException::new);
        return enterpriseRepository.save(enterprise);
    }

    @GetMapping("/{tvaNumber}")
    public Enterprise findByTvaNumber(@PathVariable String tvaNumber) {
        if (StringUtils.isBlank(tvaNumber)) {
            throw new InvalidInputException("TVA number cannot be null or empty");
        }
        return enterpriseRepository.findByTvaNumber(tvaNumber)
                .orElseThrow(EnterpriseNotFoundException::new);
    }

    @GetMapping
    public List<Enterprise> findAll() {
        return enterpriseRepository.findAll();
    }

    @PutMapping("/{contactId}/{enterpriseId}")
    public Enterprise addContactToEnterprise(@PathVariable Long contactId, @PathVariable Long enterpriseId) {
        if (contactId == null || enterpriseId == null) {
            throw new InvalidInputException("Contact Id and/or Enterprise Id cannot be null");
        }
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(ContactNotFoundException::new);
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(EnterpriseNotFoundException::new);

        enterprise.addContact(contact);

        return enterpriseRepository.save(enterprise);
    }

}
