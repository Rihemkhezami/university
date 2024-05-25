package com.bezkoder.spring.login.models;

import jakarta.persistence.*;

@Entity
@Table(name = "enseignants")
public class Enseignant extends User {

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;
    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public Enseignant() {}

    public Enseignant(String username, String email, String password, Matiere matiere, Classe classe, Departement departement) {
        super(username, email, password);
        this.matiere = matiere;
        this.classe = classe;
        this.departement = departement;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
