package com.PisciSmart.DashBoard.Service;

import com.PisciSmart.DashBoard.Repository.PisciculteurRepository;
import com.PisciSmart.DashBoard.Repository.EmployeRepository;
import com.PisciSmart.DashBoard.Repository.VisiteurRepository;
import com.PisciSmart.DashBoard.Repository.DispositifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    private final PisciculteurRepository pisciculteurRepository;
    private final EmployeRepository employeRepository;
    private final VisiteurRepository visiteurRepository;
    private final DispositifRepository dispositifRepository;

    @Autowired
    public StatisticsService(PisciculteurRepository pisciculteurRepository, EmployeRepository employeRepository,
            VisiteurRepository visiteurRepository, DispositifRepository dispositifRepository) {
        this.pisciculteurRepository = pisciculteurRepository;
        this.employeRepository = employeRepository;
        this.visiteurRepository = visiteurRepository;
        this.dispositifRepository = dispositifRepository;
    }

    public List<Map<String, Object>> getMonthlyStatistics() {
        List<Map<String, Object>> stats = new ArrayList<>();

        String[] months = { "Jan", "Fev", "Mar", "Avr", "Mai", "Jun", "Jul", "Aou", "Sep", "Oct", "Nov", "Dec" };

        for (int i = 0; i < 12; i++) {
            Map<String, Object> monthData = new HashMap<>();
            monthData.put("month", months[i]);

            // monthData.put("pisciculteurs", pisciculteurRepository.countByMonth(i + 1));
            // monthData.put("employes", employeRepository.countByMonth(i + 1));
            // monthData.put("visiteurs", visiteurRepository.countByMonth(i + 1));
            // monthData.put("dispositifs", dispositifRepository.countByMonth(i + 1));

            stats.add(monthData);
        }
        return stats;
    }
}
