package com.PisciSmart.DashBoard.Controleur;

import com.PisciSmart.DashBoard.Model.Visiteurs;
import com.PisciSmart.DashBoard.Model.employes;
import com.PisciSmart.DashBoard.Service.EmployeService;
import com.PisciSmart.DashBoard.Service.VisiteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employe")
public class EmployeControleur {
    private final EmployeService employeService;
    @Autowired
    public EmployeControleur(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping("/read")
    public List<employes> getAllEmployer() {
        return employeService.getAllEmployes();
    }
    @GetMapping("/{idpisciculteur}/employes")
    public ResponseEntity<List<employes>> getEmployesByPisciculteur(@PathVariable Long idpisciculteur) {
        List<employes> employes = employeService.getEmployesByPisciculteur(idpisciculteur);
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }
}
