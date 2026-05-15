# 📈 Stock Trading Platform

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Server-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Data_Access-blue?style=for-the-badge)

Welcome to the **Stock Trading Platform**, a robust and interactive console-based Java application developed as part of the **CodeAlpha Internship**. 

This project simulates a real-time stock market environment. It allows users to create accounts, view dynamically fluctuating stock prices, manage virtual portfolios, and execute buy/sell trades—all backed by a secure MySQL database to ensure data persistence and transaction integrity.

---

## ✨ Features

- **User Accounts & Virtual Wallets:** Seamlessly log in or create a new account. New users are automatically credited with a default virtual cash balance to start their trading journey.
- **Dynamic Market Simulation:** Features realistic price fluctuations. Every time you return to the main menu, stock prices shift up or down, mimicking real-world market volatility.
- **Trade Execution (Buy/Sell):** Execute trades with real-time validations. The system prevents buying with insufficient funds and prevents selling shares you don't own. 
- **Portfolio Management:** Instantly view your current cash balance, active stock holdings, average share prices, and total combined portfolio value.
- **Transaction History:** Keep a detailed ledger of all past trades, complete with timestamps, transaction types, quantities, and total execution costs.
- **ACID Compliant Transactions:** Uses JDBC `setAutoCommit(false)` to ensure that cash balance deductions, transaction logging, and holding updates happen simultaneously. If one fails, the entire trade rolls back, preventing data corruption.

---

## 🏗️ Architecture & Design

This project strictly adheres to Object-Oriented Programming (OOP) principles and implements the **DAO (Data Access Object) Pattern** to maintain a clean separation of concerns:

### Project Structure
```text
📦 src
 ┣ 📂 Models
 ┃ ┣ 📜 Stock.java          # Represents individual stock properties
 ┃ ┗ 📜 User.java           # Represents the user and their virtual wallet
 ┣ 📂 Database & DAO
 ┃ ┣ 📜 DataBaseConfig.java # Securely loads database credentials from db.properties
 ┃ ┣ 📜 DataBaseConnection.java # Establishes the JDBC connection
 ┃ ┗ 📜 TradingDAO.java     # Handles all raw SQL queries and DB transactions
 ┣ 📂 Core Logic
 ┃ ┗ 📜 Market.java         # Initializes predefined stocks and handles price fluctuation logic
 ┗ 📂 Controller
   ┗ 📜 TradingPlatform.java# The main entry point and interactive console UI
