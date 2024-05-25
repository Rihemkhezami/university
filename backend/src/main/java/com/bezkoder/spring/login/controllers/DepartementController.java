package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.Departement;
import com.bezkoder.spring.login.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/departements")
public class DepartementController {

    @Autowired
    DepartementRepository departementRepository;

    @GetMapping
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable Long id) {
        return departementRepository.findById(id)
                .map(departement -> ResponseEntity.ok().body(departement))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Departement createDepartement(@RequestBody Departement departement) {
        return departementRepository.save(departement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departement> updateDepartement(@PathVariable Long id, @RequestBody Departement departementDetails) {
        return departementRepository.findById(id)
                .map(departement -> {
                    departement.setNom(departementDetails.getNom());
                    Departement updatedDepartement = departementRepository.save(departement);
                    return ResponseEntity.ok().body(updatedDepartement);
                }).orElse(ResponseEntity.notFound().build());
    }
/*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long id) {
        return departementRepository.findById(id)
                .map(departement -> {
                    departementRepository.delete(departement);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }*/
}
