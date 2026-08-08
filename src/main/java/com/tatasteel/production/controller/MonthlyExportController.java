package com.tatasteel.production.controller;

import com.tatasteel.production.service.MonthlyExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports/monthly")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MonthlyExportController {

    private final MonthlyExportService exportService;

    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportExcel(
            @RequestParam String month,
            @RequestParam Double target) throws Exception {

        byte[] data =
                exportService.exportExcel(month, target);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Monthly_Report.xlsx"
                )
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )
                .body(data);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportPdf(
            @RequestParam String month,
            @RequestParam Double target) throws Exception {

        byte[] data =
                exportService.exportPdf(month, target);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Monthly_Report.pdf"
                )
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(data);
    }
}