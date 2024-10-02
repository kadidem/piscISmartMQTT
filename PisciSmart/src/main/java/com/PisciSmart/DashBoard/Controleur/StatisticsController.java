package com.PisciSmart.DashBoard.Controleur;

import com.PisciSmart.DashBoard.Service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")

public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<Map<String, Object>>> getMonthlyStatistics() {
        List<Map<String, Object>> stats = statisticsService.getMonthlyStatistics();
        if (stats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stats);
    }
}
