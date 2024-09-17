package com.PisciSmart.DashBoard.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAdmin;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String telephone;
    @Column(nullable = false, unique = true)
    private String motDePasse;

    public Admin() {
    }
    public Admin(String telephone, String motDePasse, String nom, String prenom) {
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
    }
}
