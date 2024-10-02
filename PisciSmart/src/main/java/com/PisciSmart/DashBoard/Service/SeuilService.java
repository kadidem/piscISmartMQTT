package com.PisciSmart.DashBoard.Service;

import com.PisciSmart.DashBoard.Model.Seuil;
import com.PisciSmart.DashBoard.Repository.SeuilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeuilService {
    @Autowired
    private SeuilRepository seuilRepository;

    public void saveSeuils(Seuil seuils) {
        seuilRepository.save(seuils);
        System.out.println("Seuils enregistrés avec succès.");
    }
}
