package dao;

import model.Course;
import util.DatabaseUtils;
import util.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public void create(Course course) {
        String sql = "INSERT INTO courses (id, name, credits) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, course.getId());
            pstmt.setString(2, course.getName());
            pstmt.setInt(3, course.getCredits());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QueryBuilder.findAllCourses())) {
            while (rs.next()) {
                courses.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getInt("credits")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
