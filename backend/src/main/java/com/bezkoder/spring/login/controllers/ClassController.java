package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.Classe;
import com.bezkoder.spring.login.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    ClasseRepository classeRepository;

    @GetMapping
    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasseById(@PathVariable Long id) {
        return classeRepository.findById(id)
                .map(classe -> ResponseEntity.ok().body(classe))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Classe createClasse(@RequestBody Classe classe) {
        return classeRepository.save(classe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> updateClasse(@PathVariable Long id, @RequestBody Classe classeDetails) {
        return classeRepository.findById(id)
                .map(classe -> {
                    classe.setNom(classeDetails.getNom());
                    Classe updatedClasse = classeRepository.save(classe);
                    return ResponseEntity.ok().body(updatedClasse);
                }).orElse(ResponseEntity.notFound().build());
    }
/*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        return classeRepository.findById(id)
                .map(classe -> {
                    classeRepository.delete(classe);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }*/
}
