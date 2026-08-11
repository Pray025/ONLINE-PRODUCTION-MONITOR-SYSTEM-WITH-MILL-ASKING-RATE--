package com.tatasteel.production.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.tatasteel.production.dto.MonthlyReportDTO;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlyExportService {

    private final MonthlyReportService monthlyReportService;

    public byte[] exportExcel(
            String month,
            Double target) throws Exception {

        List<MonthlyReportDTO> data =
                monthlyReportService.getMonthlyReport(
                        month,
                        target
                );

        Workbook workbook =
                new XSSFWorkbook();

        Sheet sheet =
                workbook.createSheet(
                        "Monthly Report"
                );

        Row header =
                sheet.createRow(0);

        String[] columns = {
                "Date",
                "Target(tn)",
                "Actual(tn)",
                "Balance(tn)",
                "No. Of Coils(Pcs)",
                "Avg Weight(tn)"
        };

        for(int i=0;i<columns.length;i++) {

            header.createCell(i)
                    .setCellValue(columns[i]);
        }

        CellStyle greenStyle =
                workbook.createCellStyle();

        greenStyle.setFillForegroundColor(
                IndexedColors.LIGHT_GREEN.getIndex()
        );

        greenStyle.setFillPattern(
                FillPatternType.SOLID_FOREGROUND
        );

        CellStyle redStyle =
                workbook.createCellStyle();

        redStyle.setFillForegroundColor(
                IndexedColors.ROSE.getIndex()
        );

        redStyle.setFillPattern(
                FillPatternType.SOLID_FOREGROUND
        );

        int rowNum = 1;

        for(MonthlyReportDTO d : data) {

            Row row =
                    sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(d.getDate());

            row.createCell(1)
                    .setCellValue(d.getTarget());

            row.createCell(2)
                    .setCellValue(d.getActual());

            Cell balanceCell =
                    row.createCell(3);

            balanceCell.setCellValue(
                    d.getBalance()
            );

            if(d.getBalance() >= 0) {

                balanceCell.setCellStyle(
                        greenStyle
                );
            }
            else {

                balanceCell.setCellStyle(
                        redStyle
                );
            }

            row.createCell(4)
                    .setCellValue(
                            d.getCoilCount()
                    );

            row.createCell(5)
                    .setCellValue(
                            d.getAvgWeight()
                    );
        }

        for(int i=0;i<6;i++) {

            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        workbook.write(out);

        workbook.close();

        return out.toByteArray();
    }

    public byte[] exportPdf(
            String month,
            Double target) throws Exception {

        List<MonthlyReportDTO> data =
                monthlyReportService.getMonthlyReport(
                        month,
                        target
                );

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        Document document =
                new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(
                document,
                out
        );

        document.open();

        document.add(
                new Paragraph(
                        "Monthly Production Report"
                )
        );

        document.add(
                new Paragraph(
                        "Month : " + month
                )
        );

        document.add(new Paragraph(" "));

        PdfPTable table =
                new PdfPTable(6);

        table.setWidthPercentage(100);

        table.addCell("Date");
        table.addCell("Target(tn)");
        table.addCell("Actual(tn)");
        table.addCell("Balance(tn)");
        table.addCell("No. Of Coils(Pcs)");
        table.addCell("Avg Weight(tn)");

        for(MonthlyReportDTO d : data) {

            table.addCell(d.getDate());

            table.addCell(
                    String.valueOf(
                            d.getTarget()
                    )
            );

            table.addCell(
                    String.valueOf(
                            d.getActual()
                    )
            );

            PdfPCell balanceCell =
                    new PdfPCell(
                            new Phrase(
                                    String.valueOf(
                                            d.getBalance()
                                    )
                            )
                    );

            if(d.getBalance() >= 0) {

                balanceCell.setBackgroundColor(
                        BaseColor.GREEN
                );
            }
            else {

                balanceCell.setBackgroundColor(
                        BaseColor.RED
                );
            }

            table.addCell(balanceCell);

            table.addCell(
                    String.valueOf(
                            d.getCoilCount()
                    )
            );

            table.addCell(
                    String.valueOf(
                            d.getAvgWeight()
                    )
            );
        }

        document.add(table);

        document.close();

        return out.toByteArray();
    }
}