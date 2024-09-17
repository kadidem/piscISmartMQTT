package com.PisciSmart.DashBoard.Controleur;

import com.PisciSmart.DashBoard.Model.Admin;
import com.PisciSmart.DashBoard.Service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("Admin")
@AllArgsConstructor
public class AdminControleur {
    private final AdminService adminService;
    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin){
        return new ResponseEntity<>(adminService.createAdmin(admin), HttpStatus.OK);
    }
    @GetMapping("/read")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmin();
    }
    @CrossOrigin
    @GetMapping("/read/{id}")
    // @Operation(summary = "Affichage  d'un utilisateur")
    public ResponseEntity<Admin> getAdminById(@Valid @PathVariable long id){
        return new ResponseEntity<>(adminService.getAdminById(id),HttpStatus.OK) ;}
    @CrossOrigin
    @PutMapping("/update")
    //  @Operation(summary = "Modification d'un utilisateur")
    public ResponseEntity<Admin>  editAdmin(@Valid @RequestBody Admin admin){
        return new ResponseEntity<>( adminService.editadmin(admin),HttpStatus.OK);}
    @CrossOrigin
    @DeleteMapping("/delete")
    //@Operation(summary = "Suppression d'un utilisateur")
    public ResponseEntity<String> deleteAdminById(@Valid @RequestBody Admin admin){
        return new ResponseEntity<>(adminService.deleteAdminById(admin),HttpStatus.OK);}
    @CrossOrigin
    @PostMapping("/login")
    // @Operation(summary = "Connexion d'un utilisateur")
    public Object connexion(@RequestParam("telephone") String telephone,@RequestParam("motDePasse") String motDePasse) {
        System.out.println(telephone);
        System.out.println(motDePasse);
        return adminService.connectionAdmin(telephone, motDePasse);
    }
}
