package com.PisciSmart.DashBoard.Controleur;

import com.PisciSmart.DashBoard.Model.Pisciculteurs;
import com.PisciSmart.DashBoard.Model.Visiteurs;
import com.PisciSmart.DashBoard.Service.PisciculteurService;
import com.PisciSmart.DashBoard.Service.VisiteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/visiteurs")
public class VisiteurControleur {

        private final VisiteurService visiteurService;

        @Autowired
          public VisiteurControleur(VisiteurService visiteurService) {
        this.visiteurService = visiteurService;
    }

    @GetMapping("/read")
        public List<Visiteurs> getAllVisiteurs() {
            return visiteurService.getAllVisiteurs();
        }

    }
