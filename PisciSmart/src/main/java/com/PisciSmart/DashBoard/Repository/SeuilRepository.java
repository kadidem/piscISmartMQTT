package com.PisciSmart.DashBoard.Repository;

import com.PisciSmart.DashBoard.Model.Seuil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeuilRepository extends JpaRepository<Seuil,Long> {
}
