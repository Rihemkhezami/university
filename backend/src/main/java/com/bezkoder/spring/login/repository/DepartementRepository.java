package com.bezkoder.spring.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.login.models.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
     Optional<Departement> findByNom(String nom);
}
