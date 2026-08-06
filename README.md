# 🏭 Real-Time Production Monitoring System

[![TATA Steel Internship](https://img.shields.io/badge/Project-TATA%20Steel%20Internship-blue.svg)](https://www.tatasteel.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Python](https://img.shields.io/badge/Python-3.x-green.svg)](https://www.python.org/)
[![Flask](https://img.shields.io/badge/Framework-Flask-black.svg)](https://flask.palletsprojects.com/)

> An end-to-end industrial production monitoring and data logging solution developed during my internship at **TATA Steel**. The system continuously tracks shop-floor operations, captures machine telemetry in real-time, logs throughput data, and provides operational visibility to optimize productivity and minimize unplanned downtime.

---

## 📌 Project Overview

In steel manufacturing, maintaining continuous visibility over production throughput, delay events, and machine efficiency is critical. This project digitizes and automates operational data collection from shop-floor units, shifting from manual logbooks to a centralized, automated monitoring system.

### Key Objectives
- **Automated Data Capture:** Periodically poll and store shop-floor telemetry and production counts.
- **Downtime & Delay Tracking:** Log delay durations and operational bottlenecks for root-cause analysis.
- **Analytics & Reporting:** Store structured production data in Excel/database formats for downstream analysis and reporting.
- **Scalable Architecture:** Lightweight backend built with Flask and Python, easily adaptable to various industrial protocols (PLCs/SCADA/IoT edge nodes).

---

## 📂 Repository Structure

```text
Production-Monitoring-System/
├── app.py                   # Main Flask web server & API endpoints
├── data_logger.py           # Production & telemetry logging module
├── templates/               # HTML templates for operational dashboard
│   ├── index.html           # Main dashboard interface
│   └── report.html          # Production report view
├── static/                  # CSS stylesheets, JS scripts, and images
│   ├── css/
│   └── js/
├── data/                    # Local storage for logged Excel/CSV records
├── requirements.txt         # Python dependency list
├── .gitignore               # Ignored files (temporary logs, .xlsx, virtualenvs)
└── README.md                # Project documentation

🛠️ Tech Stack
Backend: Python 3, Flask

Data Handling: pandas, openpyxl (Excel automation & logging)

Frontend: HTML5, CSS3, JavaScript (Fetch API / Chart.js for real-time visualization)

Database/Storage: File-based data logging (.xlsx / SQLite)

Environment: VS Code, Git

🚀 Getting Started
Follow these steps to run the Production Monitoring System locally.

Prerequisites
Python 3.8 or higher installed on your machine.

Git installed.

1. Clone the Repository
git clone [https://github.com/YOUR-USERNAME/YOUR-REPOSITORY-NAME.git](https://github.com/YOUR-USERNAME/YOUR-REPOSITORY-NAME.git)
cd YOUR-REPOSITORY-NAME

2. Create a Virtual Environment (Recommended)
# On Windows
python -m venv venv
venv\Scripts\activate

# On macOS/Linux
python3 -m venv venv
source venv/bin/activate

3. Install Dependencies
pip install -r requirements.txt

4. Run the Application
python app.py

5. Access the Dashboard
Open your web browser and navigate to:
[http://127.0.0.1:5000/](http://127.0.0.1:5000/)

⚙️ Features
⏱️ Live Status Monitoring: Monitor real-time status of connected machinery/production lines.

📊 Shift-wise Production Logging: Record hourly and shift-wise item counts automatically.

🛑 Delay & Breakdown Management: Log downtime events with timestamps for OEE (Overall Equipment Effectiveness) tracking.

📁 Excel Export: Export generated logs directly into formatted .xlsx files for official TATA Steel shift reporting.

🔒 Confidentiality & Security Note
Note: Any proprietary machine addresses, internal IP configurations, sensitive plant metrics, or credentials related to TATA Steel have been sanitized or omitted from this public repository in compliance with NDA and internal data protection policies.

📜 License
Distributed under the MIT License. See LICENSE for more information.

👤 Author
Pratham Ray

Internship: TATA Steel

GitHub: @Pray025

LinkedIn: https://www.linkedin.com/in/pratham-ray