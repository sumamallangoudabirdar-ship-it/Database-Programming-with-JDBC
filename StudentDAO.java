package dao;

import model.Student;
import util.DatabaseUtils;
import util.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void create(Student student) {
        String sql = "INSERT INTO students (id, name, email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QueryBuilder.findAllStudents())) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student findById(int id) {
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(QueryBuilder.findStudentById(id))) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(int id, Student student) {
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> search(String keyword) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? OR email LIKE ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
