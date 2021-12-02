package com.ltran.ecm.persistence.repository;

import com.ltran.ecm.persistence.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    Optional<Enterprise> findByTvaNumber(String tvaNumber);

}
