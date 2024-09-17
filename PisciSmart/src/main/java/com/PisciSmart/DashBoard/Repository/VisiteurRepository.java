package com.PisciSmart.DashBoard.Repository;

import com.PisciSmart.DashBoard.Model.Visiteurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisiteurRepository extends JpaRepository<Visiteurs,Long> {
}
