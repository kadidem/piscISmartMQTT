package com.PisciSmart.DashBoard.Service;

import com.PisciSmart.DashBoard.Model.Pisciculteurs;
import com.PisciSmart.DashBoard.Model.Visiteurs;
import com.PisciSmart.DashBoard.Repository.PisciculteurRepository;
import com.PisciSmart.DashBoard.Repository.VisiteurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisiteurService {
    private final VisiteurRepository visiteurRepository;
    @Autowired
        public VisiteurService(VisiteurRepository visiteurRepository) {
        this.visiteurRepository = visiteurRepository;
    }

    public List<Visiteurs> getAllVisiteurs() {
        List<Visiteurs> visiteurs = visiteurRepository.findAll();
        if (visiteurs.isEmpty()) {
            throw new EntityNotFoundException("Aucun visiteurs trouvé");
        }
        return visiteurs;
    }
    
}
