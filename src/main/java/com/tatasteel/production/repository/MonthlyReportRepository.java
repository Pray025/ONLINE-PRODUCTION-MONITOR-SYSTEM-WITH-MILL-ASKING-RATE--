package com.tatasteel.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tatasteel.production.entity.HourlyAskingRateReport;

import java.time.LocalDateTime;
import java.util.List;

public interface MonthlyReportRepository
        extends JpaRepository<HourlyAskingRateReport, LocalDateTime> {

    @Query(value = """
        SELECT
            CASE
                WHEN EXTRACT(HOUR FROM START_TIME) < 6
                THEN TRUNC(START_TIME) - 1
                ELSE TRUNC(START_TIME)
            END AS PRODUCTION_DATE,

            SUM(ACTUAL_PRODUCTION) AS ACTUAL,

            SUM(COILS_PRODUCED_COUNT) AS COIL_COUNT

        FROM HOURLY_ASKING_RATE_REPORT

        WHERE START_TIME >= :startDate
          AND START_TIME < :endDate

        GROUP BY
            CASE
                WHEN EXTRACT(HOUR FROM START_TIME) < 6
                THEN TRUNC(START_TIME) - 1
                ELSE TRUNC(START_TIME)
            END

        ORDER BY PRODUCTION_DATE
        """,
        nativeQuery = true)
    List<Object[]> getMonthlyReport(
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}