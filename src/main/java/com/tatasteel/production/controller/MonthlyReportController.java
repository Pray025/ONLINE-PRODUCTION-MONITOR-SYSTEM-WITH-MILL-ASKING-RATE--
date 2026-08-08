package com.tatasteel.production.controller;

import com.tatasteel.production.dto.MonthlyReportDTO;
import com.tatasteel.production.service.MonthlyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MonthlyReportController {

    private final MonthlyReportService monthlyReportService;

    @GetMapping("/test")
    public String test() {
        return "Monthly Controller Working";
    }

    @GetMapping("/monthly")
    public List<MonthlyReportDTO> getMonthlyReport(
            @RequestParam String month,
            @RequestParam(required = false, defaultValue = "360000")
            Double target) {

        return monthlyReportService.getMonthlyReport(
                month,
                target
        );
    }

    @PostMapping("/save")
    public String saveMonthlyReport(@RequestBody List<MonthlyReportDTO> data) {
        monthlyReportService.saveAll(data);
        
        return "Saved Successfully";
    }
}