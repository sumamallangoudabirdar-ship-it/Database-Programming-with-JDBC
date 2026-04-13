package service;

import dao.StudentDAO;
import model.Student;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();

    public void addStudent(Student student) {
        studentDAO.create(student);
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    public Student getStudentById(int id) {
        return studentDAO.findById(id);
    }

    public void updateStudent(int id, Student student) {
        studentDAO.update(id, student);
    }

    public void deleteStudent(int id) {
        studentDAO.delete(id);
    }

    public List<Student> searchStudents(String keyword) {
        return studentDAO.search(keyword);
    }
}
