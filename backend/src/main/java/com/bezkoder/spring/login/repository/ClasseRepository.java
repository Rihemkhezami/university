package com.bezkoder.spring.login.repository;

import java.util.Optional;

import com.bezkoder.spring.login.models.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
     Optional<Classe> findByNom(String nom);
}
