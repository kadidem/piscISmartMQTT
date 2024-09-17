package com.PisciSmart.DashBoard.Repository;

import com.PisciSmart.DashBoard.Model.employes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeRepository extends JpaRepository<employes,Long> {
    //List<Medicament> findByUser_IdUser(long idUser);
    List<employes> findByPisciculteur_idpisciculteur(Long idpisciculteur);
}
