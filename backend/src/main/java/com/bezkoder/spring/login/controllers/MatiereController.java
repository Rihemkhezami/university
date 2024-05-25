package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.Matiere;
import com.bezkoder.spring.login.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/matieres")
public class MatiereController {

    @Autowired
    MatiereRepository matiereRepository;

    @GetMapping
    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable Long id) {
        return matiereRepository.findById(id)
                .map(matiere -> ResponseEntity.ok().body(matiere))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Matiere createMatiere(@RequestBody Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matiere> updateMatiere(@PathVariable Long id, @RequestBody Matiere matiereDetails) {
        return matiereRepository.findById(id)
                .map(matiere -> {
                    matiere.setNom(matiereDetails.getNom());
                    Matiere updatedMatiere = matiereRepository.save(matiere);
                    return ResponseEntity.ok().body(updatedMatiere);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMatiere(@PathVariable Long id) {
        return matiereRepository.findById(id)
                .map(matiere -> {
                    matiereRepository.delete(matiere);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
