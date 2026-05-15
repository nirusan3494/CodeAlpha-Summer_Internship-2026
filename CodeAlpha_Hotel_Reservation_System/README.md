# 🏨 Hotel Reservation System

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Server-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Data_Access-blue?style=for-the-badge)

Welcome to the **Hotel Reservation System**, a robust and interactive console-based Java application developed as part of the **CodeAlpha Internship**. 

This project simulates a real-world hotel booking environment. [cite_start]It allows users to search for available rooms across different categories, process simulated payments, generate unique booking IDs, and manage their reservations—all backed by a secure MySQL database to ensure data persistence and transaction integrity[cite: 76, 94].

---

## ✨ Features

- **Dynamic Room Browsing:** Search and view real-time available rooms categorized by type (Standard, Deluxe, Suite) and price per night.
- **Booking & Payment Simulation:** Execute reservations with a built-in payment simulation. [cite_start]The system validates sufficient funds before confirming the booking and calculates any change due[cite: 76, 86].
- **Reservation Management:** Instantly retrieve detailed booking information using a unique Reservation ID, or cancel active reservations to free up room availability.
- **Real-Time Data Sync:** Availability statuses are dynamically updated in the database the moment a room is booked or a reservation is canceled.
- **ACID Compliant Transactions:** Uses JDBC `setAutoCommit(false)` to ensure that marking a room as unavailable and logging the reservation happen simultaneously. If one fails, the entire booking rolls back to prevent orphaned data[cite: 105, 106].

---

## 🏗️ Architecture & Design

This project strictly adheres to Object-Oriented Programming (OOP) principles and implements the **DAO (Data Access Object) Pattern** to maintain a clean separation of concerns, making the code modular and enterprise-ready:

### Project Structure
```text
📦 src
 ┣ 📂 Models
 ┃ ┣ 📜 Room.java               # Represents individual hotel rooms, pricing, and categories
 ┃ ┗ 📜 Reservation.java        # Represents a booking linking a guest to a specific Room
 ┣ 📂 Database & DAO
 ┃ ┣ 📜 DataBaseConfig.java     # Securely loads database credentials from db.properties
 ┃ ┣ 📜 DataBaseConnection.java # Establishes the JDBC connection
 ┃ ┗ 📜 HotelDAO.java           # Handles all raw SQL queries, JOINs, and DB transactions
 ┗ 📂 Controller
   ┗ 📜 HotelReservationSystem.java # The main entry point and interactive console UI
