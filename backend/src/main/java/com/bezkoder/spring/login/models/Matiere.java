package com.bezkoder.spring.login.models;


import jakarta.persistence.*;

@Entity
@Table(name = "matieres")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    public Matiere() {
    }

    public Matiere(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
