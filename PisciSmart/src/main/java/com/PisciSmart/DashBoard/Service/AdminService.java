package com.PisciSmart.DashBoard.Service;

import com.PisciSmart.DashBoard.Model.Admin;
import com.PisciSmart.DashBoard.Repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class AdminService {
    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostConstruct
    public void init() {
        // Créer une liste d'administrateurs au démarrage de l'application
        List<Admin> adminsToCreate = Arrays.asList(
                new Admin("74746643", "kadidem", "Dembele", "Kadidia mah"),
                new Admin("72746643", "password1", "Maiga", "Arhamatou"),
                new Admin("73746643", "password2", "Kane", "Fatoumata"),
                new Admin("75746643", "password3", "Ifi", "boy"),
                new Admin("76746643", "password4", "Maiga", "Mahamar")
        );

        // Boucle pour vérifier et ajouter chaque administrateur s'il n'existe pas
        for (Admin admin : adminsToCreate) {
            Admin existingAdmin = adminRepository.findByTelephone(admin.getTelephone());
            if (existingAdmin == null) {
                // Hacher le mot de passe avant de sauvegarder l'admin
                admin.setMotDePasse((admin.getMotDePasse()));
                adminRepository.save(admin);
            } else {
                System.out.println("L'administrateur avec le telephone suivant " + admin.getTelephone() + " existe déjà.");
            }
        }
    }

    public Admin createAdmin(Admin admin) {
        if (adminRepository.findByTelephone(admin.getTelephone()) == null) {
            admin.setMotDePasse((admin.getMotDePasse())); // Hacher le mot de passe
            return adminRepository.save(admin);
        } else {
            throw new EntityExistsException("Cet numéro existe déjà");
        }
    }

    public Admin connectionAdmin(String telephone, String motDePasse) {
        Admin utilisateur = adminRepository.findByTelephone(telephone);
       // Admin admin = adminRepository.findByEmailAndMotDePasse(email, motDePasse);
        if (utilisateur == null) {
            throw new EntityNotFoundException("Cet utilisateur n'existe pas");
        }
        if (!utilisateur.getMotDePasse().equals(motDePasse)) {
            throw new EntityNotFoundException("Mot de passe incorrect");
        }

        // Authentification réussie, retourner l'utilisateur
        return utilisateur;// Authentification réussie, retourner l'utilisateur
    }

    // Méthode pour hacher un mot de passe
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Méthode pour vérifier un mot de passe
    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public List<Admin> getAllAdmin() {
        List<Admin> admins = adminRepository.findAll();
        if (admins.isEmpty()) throw new EntityNotFoundException("Aucun utilisateur trouvé");
        return admins;
    }

    public Admin getAdminById(long idAdmin) {
        Admin admin = adminRepository.findByIdAdmin(idAdmin);
        if (admin == null) throw new EntityNotFoundException("Cet utilisateur n'existe pas");
        return admin;
    }

    public Admin editadmin(Admin admin) {
        Admin admins = adminRepository.findByIdAdmin(admin.getIdAdmin());
        if (admins == null) throw new EntityNotFoundException("Cet utilisateur n'existe pas");
        return adminRepository.save(admin);
    }

    public String deleteAdminById(Admin admin) {
        Admin utilisateur = adminRepository.findByIdAdmin(admin.getIdAdmin());
        if (utilisateur == null) throw new EntityNotFoundException("Désolé, l'utilisateur à supprimer n'existe pas");
        adminRepository.delete(admin);
        return "Utilisateur Supprimé";
    }
}
