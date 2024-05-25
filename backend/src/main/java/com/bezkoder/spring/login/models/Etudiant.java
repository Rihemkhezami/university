package com.bezkoder.spring.login.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "etudiants")
public class Etudiant extends User {

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;



    public Etudiant(String username, String email, String password, Matiere matiere, Classe classe) {
        super(username, email, password);
        this.matiere = matiere;
        this.classe = classe;
    }

    public Etudiant() {
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


}
