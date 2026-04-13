package config;

import java.sql.Connection;
import java.sql.Statement;

import util.DatabaseUtils;

public class DatabaseInitializer {
    public static void init() {
        Connection conn = DatabaseUtils.getConnection();
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY, name VARCHAR(100), email VARCHAR(100))");
            stmt.execute("CREATE TABLE IF NOT EXISTS courses (id INT PRIMARY KEY, name VARCHAR(100), credits INT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS enrollments (id INT PRIMARY KEY, student_id INT, course_id INT, enrollment_date VARCHAR(50), FOREIGN KEY(student_id) REFERENCES students(id), FOREIGN KEY(course_id) REFERENCES courses(id))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
