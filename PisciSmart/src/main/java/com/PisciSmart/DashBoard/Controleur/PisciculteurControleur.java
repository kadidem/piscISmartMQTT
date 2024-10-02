package com.PisciSmart.DashBoard.Controleur;

import com.PisciSmart.DashBoard.Model.Pisciculteurs;
import com.PisciSmart.DashBoard.Service.PisciculteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pisciculteurs")
public class PisciculteurControleur {
    private final PisciculteurService pisciculteurService;

    @Autowired
    public PisciculteurControleur(PisciculteurService pisciculteurService) {
        this.pisciculteurService = pisciculteurService;
    }

    @GetMapping("/read")
    public List<Pisciculteurs> getAllPisciculteurs() {
        return pisciculteurService.getAllPisciculteurs();
    }

    @GetMapping("/count")
    public long countPisciculteurs() {
        return pisciculteurService.countPisciculteurs();
    }


        // Appeler le service pour activer ou désactiver le compte
      //  Pisciculteurs pisciculteur = pisciculteurService.activerOuDesactiverCompte(pisciculteurId, active);
      //  return ResponseEntity.ok(pisciculteur);
   // }

    // @PostMapping("/status")
    // public ResponseEntity<Pisciculteurs> activerOuDesactiverCompte(@RequestBody
    // Map<String, Object> body) {
    // Extraire les valeurs depuis la Map
    // Long pisciculteurId = Long.valueOf(body.get("pisciculteurId").toString());
    // boolean active = Boolean.parseBoolean(body.get("active").toString());

    // Appeler le service pour activer ou désactiver le compte
    // Pisciculteurs pisciculteur =
    // pisciculteurService.activerOuDesactiverCompte(pisciculteurId, active);
    // return ResponseEntity.ok(pisciculteur);
    // }

}
