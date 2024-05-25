package com.bezkoder.spring.login.controllers;


import com.bezkoder.spring.login.models.Classe;
import com.bezkoder.spring.login.models.Departement;
import com.bezkoder.spring.login.models.Matiere;
import com.bezkoder.spring.login.repository.ClasseRepository;
import com.bezkoder.spring.login.repository.DepartementRepository;
import com.bezkoder.spring.login.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private DepartementRepository departementRepository;
/*
    @Autowired
    private EnseignantRepository enseignantRepository;
*/
    @GetMapping("/matieres")
    public ResponseEntity<List<Matiere>> getAllMatieres() {
        List<Matiere> matieres = matiereRepository.findAll();
        return ResponseEntity.ok(matieres);
    }

    @GetMapping("/classes")
    public ResponseEntity<List<Classe>> getAllClasses() {
        List<Classe> classes = classeRepository.findAll();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/departements")
    public ResponseEntity<List<Departement>> getAllDepartements() {
        List<Departement> departements = departementRepository.findAll();
        return ResponseEntity.ok(departements);
    }
/*
    @GetMapping("/enseignants")
    public ResponseEntity<List<Enseignant>> getAllEnseignants() {
        List<Enseignant> enseignants = enseignantRepository.findAll();
        return ResponseEntity.ok(enseignants);
    }
*/

}
