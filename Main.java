import config.DatabaseInitializer;
import dao.StudentDAO;
import dao.CourseDAO;
import dao.EnrollmentDAO;
import model.Student;
import model.Course;
import model.Enrollment;
import service.StudentService;
import service.ReportService;
import util.DatabaseUtils;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentService studentService = new StudentService();
    private static ReportService reportService = new ReportService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

DatabaseInitializer.init(); // Auto init DB on start
                switch (choice) {
                case 1:
                    DatabaseInitializer.init();
                    System.out.println("Database initialized!");
                    break;
                case 2:
                    addStudent(scanner);
                    break;
                case 3:
                    viewAllStudents();
                    break;
                case 4:
                    searchStudents(scanner);
                    break;
                case 5:
                    updateStudent(scanner);
                    break;
                case 6:
                    deleteStudent(scanner);
                    break;
                case 7:
                    batchAddStudents(scanner);
                    break;
                case 8:
                    callStoredProcedure();
                    break;
                case 9:
                    showStatistics();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== STUDENT MANAGEMENT SYSTEM WITH JDBC ===");
        System.out.println("1. Initialize Database");
        System.out.println("2. Add New Student");
        System.out.println("3. View All Students");
        System.out.println("4. Search Students");
        System.out.println("5. Update Student");
        System.out.println("6. Delete Student");
        System.out.println("7. Batch Operations");
        System.out.println("8. Call Stored Procedure");
        System.out.println("9. Database Statistics");
        System.out.println("10. Exit");
    }

private static void addStudent(Scanner scanner) {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine().trim();
            studentService.addStudent(new Student(id, name, email));
            System.out.println("Student added!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

    private static void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void searchStudents(Scanner scanner) {
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();
        List<Student> students = studentService.searchStudents(keyword);
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

private static void updateStudent(Scanner scanner) {
        try {
            System.out.print("Enter student ID to update: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Student student = studentService.getStudentById(id);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            System.out.print("Enter new name (current: " + student.getName() + "): ");
            student.setName(scanner.nextLine().trim());
            System.out.print("Enter new email (current: " + student.getEmail() + "): ");
            student.setEmail(scanner.nextLine().trim());
            studentService.updateStudent(id, student);
            System.out.println("Student updated!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

private static void deleteStudent(Scanner scanner) {
        try {
            System.out.print("Enter student ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            studentService.deleteStudent(id);
            System.out.println("Student deleted (if existed).");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

private static void batchAddStudents(Scanner scanner) {
        try {
            System.out.print("How many students to add: ");
            int n = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < n; i++) {
                addStudent(scanner);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please enter a number.");
        }
    }

    private static void callStoredProcedure() {
        // Simple function for student count
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM students");
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                System.out.println("Total students via 'procedure': " + rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showStatistics() {
        System.out.println("Total Students: " + reportService.getTotalStudents());
        System.out.println("Total Courses: " + reportService.getTotalCourses());
        System.out.println("Total Enrollments: " + reportService.getTotalEnrollments());
        System.out.println("Average Credits: " + reportService.getAvgCredits());
    }
}
