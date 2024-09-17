package com.PisciSmart.DashBoard.Repository;

import com.PisciSmart.DashBoard.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin ,Long> {
    public Admin findByTelephone(String Telephone);
    public Admin findByTelephoneAndMotDePasse(String telephone, String mot_de_passe);
    Admin findByIdAdmin(long id);
}
