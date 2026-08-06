# 🏭 Production Monitoring System

A Spring Boot web application for monitoring Hot Strip Mill (HSM) coil production in real time, tracking hourly production rates against required targets, and generating daily/monthly performance reports (with Excel and PDF export) for Tata Steel.

## 📋 Overview

The system periodically imports coil production data from an Excel source file, stores it in an Oracle database, and exposes it through a REST API consumed by a set of dashboard pages. It gives production staff a live view of how actual output compares to target output, historical hour-by-hour records, visual analytics, and monthly summary reports.

## ✨ Features

- 📊 **Live Dashboard** — hourly table showing date, hour range, required vs. actual production rate, number of coils rolled, and actual coil weight, refreshed automatically against a configurable daily target (default: 12,500 tons).
- 📈 **Production Analytics** — interactive chart (Chart.js, with zoom/pan support) comparing required vs. actual production rate for a selected date.
- 🕘 **History Reports** — browse past hourly production records by date.
- 🗓️ **Monthly Report** — daily target/actual/balance, coil count, and average coil weight for a selected month against a configurable monthly target (default: 360,000 tons), with a summary chart and one-click **Export to Excel** and **Export to PDF**.
- 🔄 **Automatic Excel Import** — a scheduled background job reads a coil birth/production Excel workbook and syncs new coil records into the database (`COIL_PRODUCTION` and `COIL_PDI` sheets).
- 🌗 **Light / Dark Mode** — theme toggle on the dashboard.
- 📱 **Collapsible Sidebar Navigation** — shared across all pages (Live Dashboard, Analytics, History, Monthly Report).

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot 3.5.15 (Spring Web, Spring Data JPA) |
| Database | Oracle Database (via `ojdbc11`, Hibernate `OracleDialect`) |
| Excel I/O | Apache POI (`poi-ooxml` 5.2.5) |
| PDF Export | iText (`itextpdf` 5.5.13.3) |
| Boilerplate | Lombok |
| Frontend | Plain HTML / CSS / JavaScript, Chart.js + chartjs-plugin-zoom |
| Build Tool | Maven (with Maven Wrapper) |

## 📁 Project Structure

```
production-monitoring-system/
├── src/main/java/com/tatasteel/production/
│   ├── ProductionMonitoringSystemApplication.java   # Entry point (@EnableScheduling)
│   ├── controller/
│   │   ├── ProductionController.java                # /api/production/live
│   │   ├── HourlyAskingRateReportController.java     # /api/live-production
│   │   ├── MonthlyReportController.java              # /api/reports/monthly, /api/reports/save
│   │   └── MonthlyExportController.java              # /api/reports/monthly/excel, /pdf
│   ├── service/
│   │   ├── ExcelImportService.java                   # Scheduled Excel -> DB sync
│   │   ├── HourlyAskingRateReportService.java
│   │   ├── MonthlyReportService.java                 # Builds monthly report + saves to DB
│   │   └── MonthlyExportService.java                 # Generates Excel/PDF exports
│   ├── repository/                                   # Spring Data JPA repositories
│   ├── entity/                                        # CoilPdi, CoilProduction, HourlyAskingRateReport
│   └── dto/
│       └── MonthlyReportDTO.java
├── src/main/resources/
│   ├── application.properties
│   └── static/
│       ├── html/   (index, analytics, history, monthly-report)
│       ├── css/style.css
│       └── js/     (app.js, menu.js, monthly-report.js)
├── excel-data/
│   └── Coil_Birth_Information (2).xlsx                # Source workbook for the import job
├── HSM_Table.sql                                       # Database schema / table setup
└── pom.xml
```

## 🔌 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/production/live` | Live coil production data (from `CoilPdi`), ordered by birth time |
| `GET` | `/api/live-production` | Hourly asking-rate report data, ordered by start time |
| `GET` | `/api/reports/monthly?month=&target=` | Monthly report rows for a given month, evaluated against a target (default target: 360000) |
| `POST` | `/api/reports/save` | Persist a list of monthly report rows |
| `GET` | `/api/reports/monthly/excel?month=&target=` | Download the monthly report as an `.xlsx` file |
| `GET` | `/api/reports/monthly/pdf?month=&target=` | Download the monthly report as a `.pdf` file |

All controllers are annotated `@CrossOrigin`, so the API can be called from a frontend served on a different origin.

## ✅ Prerequisites

- Java 21 (JDK)
- Maven (or use the included `mvnw` / `mvnw.cmd` wrapper)
- Oracle Database (a reachable instance, e.g. Oracle XE with pluggable database `XEPDB1`)
- The `HSM_Table.sql` script run against your schema to create the required tables

## ⚙️ Configuration

Database connection and server settings live in `src/main/resources/application.properties`:

```properties
spring.application.name=production-monitoring-system

# Oracle Database Connection
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=system
spring.datasource.password=oracle
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect

# Server Port
server.port=8080
```

> ⚠️ **Note:** `spring.jpa.hibernate.ddl-auto=none` means Hibernate will not create or update the schema. Run `HSM_Table.sql` yourself before starting the app, and update the datasource credentials for your environment before deploying.

The scheduled Excel import (`ExcelImportService`) currently reads from a hardcoded local path:

```
C:\Projects\production-monitoring-system\excel-data\Coil_Birth_Information (2).xlsx
```

Update this path (ideally by externalizing it into `application.properties`) to match your environment before running the import job.

## 🚀 Running the Application

Using the Maven wrapper:

```bash
# Windows
mvnw.cmd spring-boot:run

# macOS / Linux
./mvnw spring-boot:run
```

Or build a jar and run it directly:

```bash
./mvnw clean package
java -jar target/production-monitoring-system-0.0.1-SNAPSHOT.jar
```

The application starts on **http://localhost:8080** by default. Open the dashboard at:

```
http://localhost:8080/html/index.html
```

Other pages:
- `http://localhost:8080/html/analytics.html`
- `http://localhost:8080/html/history.html`
- `http://localhost:8080/html/monthly-report.html`

## 🧪 Running Tests

```bash
./mvnw test
```

## 📝 Notes

- The Excel import job runs on a fixed delay (`@Scheduled(fixedDelay = ...)`) once the application starts, so make sure the source workbook is available at startup.
- The monthly report's daily target and the dashboard's daily target are both adjustable directly in the UI (`monthlyTargetInput` and the target display on the live dashboard).
- Exported Excel/PDF filenames are `Monthly_Report.xlsx` and `Monthly_Report.pdf`.

## 📄 License

No license file is currently included in this project. Add one (e.g. MIT, Apache 2.0) if you intend to distribute or open-source this code.

👤 Author
Pratham Ray
Internship: TATA Steel
GitHub: @Pray025
LinkedIn: https://www.linkedin.com/in/pratham-ray