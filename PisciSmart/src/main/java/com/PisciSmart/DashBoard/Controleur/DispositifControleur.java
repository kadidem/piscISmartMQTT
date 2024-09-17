package com.PisciSmart.DashBoard.Controleur;

import com.PisciSmart.DashBoard.Model.Dispositifs;
import com.PisciSmart.DashBoard.Service.DispositifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Dispositifs")
public class DispositifControleur {

    @Autowired
    private DispositifService dispositifService;

    @GetMapping("/read")
    public List<Dispositifs> getAllDispositifs() {
        return dispositifService.getAllDispositifs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositifs> getDispositifById(@PathVariable Long id) {
        Dispositifs dispositif = dispositifService.getDispositifById(id);
        if (dispositif != null) {
            return ResponseEntity.ok(dispositif);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public Dispositifs ajouterDispositif(@RequestBody Dispositifs dispositif) {
        return dispositifService.ajouterDispositif(dispositif);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Dispositifs> modifierDispositif(@PathVariable Long id, @RequestBody Dispositifs dispositifDetails) {
        Dispositifs updatedDispositif = dispositifService.modifierDispositif(id, dispositifDetails);
        if (updatedDispositif != null) {
            return ResponseEntity.ok(updatedDispositif);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void> supprimerDispositif(@PathVariable Long id) {
        dispositifService.supprimerDispositif(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assigner")
    public ResponseEntity<Dispositifs> assignerDispositif(@RequestBody Map<String, Object> payload) {
        // Extraire les valeurs depuis le JSON
        String numSerie = (String) payload.get("numSerie");
        Long pisciculteurId = Long.valueOf(payload.get("pisciculteurId").toString());

        // Appeler le service pour assigner le dispositif
        Dispositifs dispositif = dispositifService.assignerDispositif(numSerie, pisciculteurId);
        return ResponseEntity.ok(dispositif);
    }

}
