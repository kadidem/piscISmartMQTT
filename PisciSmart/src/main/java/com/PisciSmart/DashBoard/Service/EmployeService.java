package com.PisciSmart.DashBoard.Service;

import com.PisciSmart.DashBoard.Model.Visiteurs;
import com.PisciSmart.DashBoard.Model.employes;
import com.PisciSmart.DashBoard.Repository.EmployeRepository;
import com.PisciSmart.DashBoard.Repository.VisiteurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService {
    private final EmployeRepository employeRepository;

    @Autowired
    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public List<employes> getAllEmployes() {
        List<employes> employer = employeRepository.findAll();
        if (employer.isEmpty()) {
            throw new EntityNotFoundException("Aucun employer trouvé");
        }
        return employer;
    }

    public List<employes> getEmployesByPisciculteur(long idpisciculteur) {

        List<employes> employe = employeRepository.findByPisciculteur_idpisciculteur(idpisciculteur);

        if (employe.isEmpty()) {
            throw new EntityNotFoundException(
                    "Aucune employé trouvée pour l'utilisateur avec l'ID : " + idpisciculteur);
        }

        return employe;
    }

    public long countEmployes() {
        return employeRepository.count();
    }

    // public List<employes> getEmployesByPisciculteur(Long idpisciculteur) {
    // return employeRepository.findByPisciculteur_idpisciculteur(idpisciculteur);
    // }
}
