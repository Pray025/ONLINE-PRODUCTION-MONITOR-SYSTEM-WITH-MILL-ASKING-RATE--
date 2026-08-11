package com.tatasteel.production.service;

import com.tatasteel.production.entity.HourlyAskingRateReport;
import com.tatasteel.production.repository.HourlyAskingRateReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HourlyAskingRateReportService {

    private final HourlyAskingRateReportRepository repository;

    public List<HourlyAskingRateReport> getAllReports() {

        return repository.findAllByOrderByStartTimeAsc();
    }
}