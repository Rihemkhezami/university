// StatisticsController.java
package com.bezkoder.spring.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.login.repository.UserRepository;
import com.bezkoder.spring.login.repository.MatiereRepository;
import com.bezkoder.spring.login.repository.ClasseRepository;
import com.bezkoder.spring.login.repository.DepartementRepository;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/stats")
public class StatisticsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MatiereRepository matiereRepository;

    @Autowired
    ClasseRepository classeRepository;

    @Autowired
    DepartementRepository departementRepository;

    @GetMapping("/counts")
    public ResponseEntity<Map<String, Long>> getCounts() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("users", userRepository.count());
        counts.put("matieres", matiereRepository.count());
        counts.put("classes", classeRepository.count());
        counts.put("departements", departementRepository.count());
        return ResponseEntity.ok(counts);
    }
}
