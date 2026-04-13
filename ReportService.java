package service;

import dao.EnrollmentDAO;
import dao.CourseDAO;
import dao.StudentDAO;
import model.Student;
import util.DatabaseUtils;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReportService {
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private StudentDAO studentDAO = new StudentDAO();

    public int getTotalEnrollments() {
        return enrollmentDAO.findAll().size();
    }

    public List<Student> getEnrolledStudents() {
        // Simplified: return all students (in full impl, would join enrollments)
        return studentDAO.findAll();
    }

    public int getTotalStudents() {
        return studentDAO.findAll().size();
    }

    public int getTotalCourses() {
        return courseDAO.findAll().size();
    }

    public double getAvgCredits() {
        String sql = "SELECT AVG(credits) FROM courses";
        try (Connection conn = DatabaseUtils.getConnection(); 
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
