package com.PisciSmart.DashBoard.Service;

import com.PisciSmart.DashBoard.Model.Dispositifs;
import com.PisciSmart.DashBoard.Model.Pisciculteurs;
import com.PisciSmart.DashBoard.Repository.DispositifRepository;
import com.PisciSmart.DashBoard.Repository.PisciculteurRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositifService {
    @Autowired
    private DispositifRepository dispositifRepository;
    @Autowired
    private PisciculteurRepository pisciculteurRepository;

    public List<Dispositifs> getAllDispositifs() {
        return dispositifRepository.findAll();
    }

    public Dispositifs getDispositifById(Long idDispositif) {

        return dispositifRepository.findById(idDispositif).orElse(null);
    }

    public Dispositifs ajouterDispositif(Dispositifs dispositif) {
        if (dispositifRepository.findByNumSerie(dispositif.getNumSerie()) == null) {
            return dispositifRepository.save(dispositif);
        } else {
            throw new EntityExistsException("Cet dispositif avec ce numero de serie existe déjà");
        }
    }

    public Dispositifs modifierDispositif(Long id, Dispositifs dispositifDetails) {
        Dispositifs dispositif = dispositifRepository.findById(id).orElse(null);
        if (dispositif != null) {
            dispositif.setNumSerie(dispositifDetails.getNumSerie());
            return dispositifRepository.save(dispositif);
        }
        return null;
    }

    public void supprimerDispositif(Long idDispositif) {
        dispositifRepository.deleteById(idDispositif);
    }

    public Dispositifs assignerDispositif(String numSerie, Long pisciculteurId) {
        // Récupérer le dispositif par numéro de série
        Dispositifs dispositif = dispositifRepository.findByNumSerie(numSerie);
        if (dispositif == null) {
            throw new EntityNotFoundException("Dispositif non trouvé");
        }

        // Récupérer le pisciculteur par ID
        Pisciculteurs pisciculteur = pisciculteurRepository.findById(pisciculteurId)
                .orElseThrow(() -> new EntityNotFoundException("Pisciculteur non trouvé"));

        // Assigner le pisciculteur au dispositif
        dispositif.setPisciculteur(pisciculteur);

        // Sauvegarder les modifications
        return dispositifRepository.save(dispositif);
    }

    public long countDispositifs() {
        return dispositifRepository.count();
    }

}
