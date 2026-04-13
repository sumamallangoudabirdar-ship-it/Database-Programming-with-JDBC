package model;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private String enrollmentDate;

    public Enrollment() {}

    public Enrollment(int id, int studentId, int courseId, String enrollmentDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(String enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    @Override
    public String toString() {
        return "Enrollment{id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + ", enrollmentDate='" + enrollmentDate + "'}";
    }
}
