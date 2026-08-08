package com.tatasteel.production.controller;

import com.tatasteel.production.entity.HourlyAskingRateReport;
import com.tatasteel.production.service.HourlyAskingRateReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HourlyAskingRateReportController {

    private final HourlyAskingRateReportService service;

    @GetMapping("/live-production")
    public List<HourlyAskingRateReport> getLiveProduction() {

        return service.getAllReports();
    }
}