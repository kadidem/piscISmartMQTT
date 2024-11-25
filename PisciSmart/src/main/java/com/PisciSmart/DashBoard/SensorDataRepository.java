package com.PisciSmart.DashBoard;

import com.PisciSmart.DashBoard.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findByIdDispo(long idDispo);
    List<SensorData> findByNumSerie(String numSerie);
}
