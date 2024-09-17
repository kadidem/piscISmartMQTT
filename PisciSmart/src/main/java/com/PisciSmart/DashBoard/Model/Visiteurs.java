package com.PisciSmart.DashBoard.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Visiteurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idvisiteur;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String telephone;
    @Column(nullable = false, unique = true)
    private String password;
    @Column (nullable = false)
    private String adresse;



}
