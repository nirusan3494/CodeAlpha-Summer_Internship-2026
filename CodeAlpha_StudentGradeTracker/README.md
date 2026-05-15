# 📊 Student Grade Tracker

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Server-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Data_Access-blue?style=for-the-badge)


Welcome to the Student Grade Tracker, a robust and interactive console-based Java application developed as part of the CodeAlpha Internship.

This project simulates a comprehensive grading environment. It allows educators to input student details, automatically calculate performance metrics, generate analytical reports, and manage academic records—all backed by a secure MySQL database to ensure data persistence and reliable tracking.

✨ Features
---
Comprehensive Student Management: Seamlessly add new student records, view the entire roster, and instantly search for individual student details using a unique auto-generated Student ID.


Automated Grade Computation: Automatically calculates total marks, average scores, letter grades (A+ to F), and Pass/Fail statuses based on inputted subject marks during object instantiation.


Performance Analytics: Quickly identify class trends by generating a Class Summary Report (showing total students, highest/lowest averages, and class average), or fetch specific milestones like the Class Topper and the Lowest Scorer.


Robust Database Integration: Utilizes JDBC to store all student data in a MySQL database, ensuring that academic records are securely persisted beyond a single application session.


Input Validation: Built-in loops ensure users enter valid marks (between 0 and 100) before processing the data, preventing faulty database entries.

🏗️ Architecture & Design
---
This project strictly adheres to Object-Oriented Programming (OOP) principles and implements the DAO (Data Access Object) Pattern to maintain a clean separation of concerns, making the code modular and enterprise-ready:

Project Structure
---
src/Models/Student.java - Represents individual student data, encapsulates marks, and calculates grading logic
src/Database & DAO/DataBaseConfig.java - Securely loads database credentials from a db.properties file
src/Database & DAO/DatabaseCon.java - Establishes and returns the JDBC connection to MySQL
src/Database & DAO/StudentDataAccessObject.java - Handles all raw SQL queries (INSERT, SELECT) and database fetching logic
src/Controller/Main.java - The main entry point to launch the application
src/Controller/GradeTracker.java - Manages the interactive console UI and user inputs
