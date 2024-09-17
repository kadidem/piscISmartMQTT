package com.PisciSmart.DashBoard.Service;

import com.PisciSmart.DashBoard.Model.Pisciculteurs;
import com.PisciSmart.DashBoard.Repository.PisciculteurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PisciculteurService {

    private final PisciculteurRepository pisciculteurRepository;

    @Autowired
    public PisciculteurService(PisciculteurRepository pisciculteurRepository) {
        this.pisciculteurRepository = pisciculteurRepository;
    }

    public List<Pisciculteurs> getAllPisciculteurs() {
        List<Pisciculteurs> pisciculteurs = pisciculteurRepository.findAll();
        if (pisciculteurs.isEmpty()) {
            throw new EntityNotFoundException("Aucun pisciculteur trouvé");
        }
        return pisciculteurs;
    }

    public Pisciculteurs activerOuDesactiverCompte(Long pisciculteurId, boolean activer) {
        // Trouver le pisciculteur par ID
        Pisciculteurs pisciculteur = pisciculteurRepository.findById(pisciculteurId)
                .orElseThrow(() -> new EntityNotFoundException("Pisciculteur non trouvé"));

        // Mettre à jour l'état d'activation
        pisciculteur.setActive(activer);

        // Sauvegarder les modifications
        return pisciculteurRepository.save(pisciculteur);
    }
}