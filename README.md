# Student Management System with JDBC & H2 Database

## Overview
Interactive CLI application for managing students, courses, and enrollments using Java, JDBC, and H2 in-memory database. Features full CRUD operations, batch processing, statistics, and error handling.

## Features
- Initialize database schema
- Add, view, search, update, delete students
- Batch student addition
- Database statistics and reports
- Stored procedure simulation
- Robust input validation (no NumberFormatException)

## Prerequisites
- Java 8+
- [H2 Database JAR](https://h2database.com) (`h2.jar` included)

## Setup & Run
```
javac -cp "h2.jar" -d . src/Main.java src/config/*.java src/dao/*.java src/model/*.java src/service/*.java src/util/*.java
java -cp ".;h2.jar" Main
```

## Menu Options
```
=== STUDENT MANAGEMENT SYSTEM WITH JDBC ===
1. Initialize Database
2. Add New Student
3. View All Students
4. Search Students
5. Update Student
6. Delete Student
7. Batch Operations
8. Call Stored Procedure  
9. Database Statistics
10. Exit
```

## Project Structure
```
.
├── h2.jar                 # H2 Database driver
├── src/                   # Source code
│   ├── config/            # DB config & initializer
│   ├── dao/               # Data Access Objects
│   ├── model/             # Entity classes
│   ├── service/           # Business logic
│   └── util/              # Utility classes
├── *.class                # Compiled classes
└── README.md              # This file
```

## Usage Example
1. Choose `1` to init DB
2. Choose `2` → Enter ID, Name, Email
3. Choose `3` to view students
4. Choose `9` for stats

Database is in-memory (resets on restart).

## Troubleshooting
- Ensure `h2.jar` in project root
- Run compile command before java
- Input numbers for IDs, text for names/emails

Built with robust error handling for production-ready CLI experience.
