package com.PisciSmart.DashBoard.Repository;

import com.PisciSmart.DashBoard.Model.Pisciculteurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PisciculteurRepository extends JpaRepository<Pisciculteurs,Long> {

}
