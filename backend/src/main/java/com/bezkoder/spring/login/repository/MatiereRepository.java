package com.bezkoder.spring.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.login.models.Matiere;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    Optional<Matiere> findByNom(String nom);
}
