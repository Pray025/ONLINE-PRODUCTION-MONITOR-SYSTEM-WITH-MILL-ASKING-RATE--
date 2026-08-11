package com.tatasteel.production.service;

import com.tatasteel.production.dto.MonthlyReportDTO;
import com.tatasteel.production.repository.MonthlyReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlyReportService {

    private final MonthlyReportRepository repository;
    private final JdbcTemplate jdbcTemplate;

    public List<MonthlyReportDTO> getMonthlyReport(
            String month,
            Double monthlyTarget
    ) {

        YearMonth yearMonth = YearMonth.parse(month);

        int days = yearMonth.lengthOfMonth();
        double hoursPerDay = 24;
        double downtimeHours = 0;

        double finalMonthlyTarget =
                (monthlyTarget != null && monthlyTarget > 0)
                        ? monthlyTarget
                        : 360000;

        double totalHours = days * hoursPerDay;
        double workingHours = totalHours - downtimeHours;

        double hourlyAskingRate = finalMonthlyTarget / workingHours;
        double dailyTarget = hourlyAskingRate * hoursPerDay;

        LocalDateTime startDate =
                yearMonth.atDay(1).atTime(6, 0);

        LocalDateTime endDate =
                yearMonth.plusMonths(1).atDay(1).atTime(6, 0);

        List<Object[]> result =
                repository.getMonthlyReport(startDate, endDate);

        List<MonthlyReportDTO> response = new ArrayList<>();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Object[] row : result) {

            LocalDate productionDate =
                    ((Timestamp) row[0])
                            .toLocalDateTime()
                            .toLocalDate();

            double actual =
                    row[1] == null
                            ? 0
                            : ((Number) row[1]).doubleValue();

            int coilCount =
                    row[2] == null
                            ? 0
                            : ((Number) row[2]).intValue();

            double avgWeight =
                    coilCount == 0 ? 0 : actual / coilCount;

            double balance =
                    actual - dailyTarget;

            response.add(new MonthlyReportDTO(
                    productionDate.format(formatter),
                    round(12500.0), // dailyTarget,
                    round(actual),
                    round(balance),
                    coilCount,
                    round(avgWeight),
                    round(totalHours),
                    round(workingHours),
                    round(hourlyAskingRate),
                    round(dailyTarget)
            ));
        }

        return response;
    }

    public void saveAll(List<MonthlyReportDTO> dataList) {

        String sql = """
            INSERT INTO MONTHLY_PRODUCTION_REPORT
            (REPORT_DATE, TARGET, ACTUAL, BALANCE, COIL_COUNT, AVG_WEIGHT)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (MonthlyReportDTO d : dataList) {

            LocalDate date =
                    LocalDate.parse(d.getDate(), formatter);

            jdbcTemplate.update(sql,
                    java.sql.Date.valueOf(date),
                    d.getTarget(),
                    d.getActual(),
                    d.getBalance(),
                    d.getCoilCount(),
                    d.getAvgWeight()
            );
        }
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}