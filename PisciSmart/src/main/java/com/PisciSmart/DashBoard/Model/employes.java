package com.PisciSmart.DashBoard.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class employes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idemploye;
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
   // @Column (nullable = false)
    //private long idpisciculteur;
   @ManyToOne
   @JoinColumn(name = "idpisciculteur")  // Clé étrangère vers Pisciculteur
   private Pisciculteurs pisciculteur;
}
