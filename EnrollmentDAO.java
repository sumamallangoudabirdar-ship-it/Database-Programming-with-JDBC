package dao;

import model.Enrollment;
import util.DatabaseUtils;
import util.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    public void create(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (id, student_id, course_id, enrollment_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getId());
            pstmt.setInt(2, enrollment.getStudentId());
            pstmt.setInt(3, enrollment.getCourseId());
            pstmt.setString(4, enrollment.getEnrollmentDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Enrollment> findAll() {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QueryBuilder.findAllEnrollments())) {
            while (rs.next()) {
                enrollments.add(new Enrollment(rs.getInt("id"), rs.getInt("student_id"), rs.getInt("course_id"), rs.getString("enrollment_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
}
