package com.PisciSmart.DashBoard.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.yaml.snakeyaml.events.Event;

@Entity
@Data
@Table(name = "DispositifsAdmin")
public class Dispositifs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDispo;
    @Column(nullable = false, unique = true)
    private String numSerie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pisciculteur_id")
    @JsonBackReference
    private Pisciculteurs pisciculteur;
}
