package util;

public class QueryBuilder {
    public static String findAllStudents() {
        return "SELECT * FROM students";
    }

    public static String findStudentById(int id) {
        return "SELECT * FROM students WHERE id = " + id;
    }

    public static String findAllCourses() {
        return "SELECT * FROM courses";
    }

    public static String findAllEnrollments() {
        return "SELECT * FROM enrollments";
    }
}
