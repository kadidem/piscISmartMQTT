package com.PisciSmart.DashBoard.Repository;

import com.PisciSmart.DashBoard.Model.Dispositifs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositifRepository extends JpaRepository<Dispositifs,Long> {
     Dispositifs findByNumSerie(String numSerie);
}
