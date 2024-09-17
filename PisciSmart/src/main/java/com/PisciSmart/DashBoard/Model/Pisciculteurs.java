package com.PisciSmart.DashBoard.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Pisciculteurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idpisciculteur;
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
    // Attribut active pour activer/désactiver le compte
    private boolean active;

    @OneToMany(mappedBy = "pisciculteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<employes> employe = new HashSet<>();
    @OneToMany(mappedBy = "pisciculteur", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Dispositifs> dispositifs = new ArrayList<>();
  //  @OneToMany(mappedBy = "pisciculteur")
    //private List<Dispositifs> dispositifs;


}
