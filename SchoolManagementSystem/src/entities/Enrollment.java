package entities;

import interfaces.Displayable;
import utils.HelperUtils;

public class Enrollment implements Displayable {

    private String enrollmentId;
    private String studentId;
    private String courseId;
    private String enrollDate;
    private String status;
    private String reason;
    private boolean repeat;
    private String notes;

    public Enrollment(String enrollmentId, String studentId, String courseId, String enrollDate,
                      String status, String reason, boolean repeat) {
        setEnrollmentId(enrollmentId);
        setStudentId(studentId);
        setCourseId(courseId);
        setEnrollDate(enrollDate);
        setStatus(status);
        setReason(reason);
        setRepeat(repeat);
        notes = "";
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        if (!HelperUtils.isValidText(enrollmentId)) {
            throw new IllegalArgumentException("Enrollment id cannot be empty");
        }
        this.enrollmentId = enrollmentId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if (!HelperUtils.isValidText(studentId)) {
            throw new IllegalArgumentException("Student id cannot be empty");
        }
        this.studentId = studentId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        if (!HelperUtils.isValidText(courseId)) {
            throw new IllegalArgumentException("Course id cannot be empty");
        }
        this.courseId = courseId.trim();
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        if (!HelperUtils.isValidText(enrollDate)) {
            throw new IllegalArgumentException("Enroll date cannot be empty");
        }
        this.enrollDate = enrollDate.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String[] allowed = {"ACTIVE", "CANCELLED", "COMPLETED", "TRANSFERRED"};
        if (!HelperUtils.isOneOf(status, allowed)) {
            throw new IllegalArgumentException("Invalid enrollment status");
        }
        this.status = status.trim().toUpperCase();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? "" : reason.trim();
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isActiveEnrollment() {
        return "ACTIVE".equals(status);
    }

    public void cancel() {
        setStatus("CANCELLED");
    }

    public void complete() {
        setStatus("COMPLETED");
    }

    public void transfer(String newCourseId, String newDate) {
        setCourseId(newCourseId);
        setEnrollDate(newDate);
        setStatus("TRANSFERRED");
    }

    public boolean isPast(String date) {
        if (!HelperUtils.isValidText(date)) {
            throw new IllegalArgumentException("Comparison date cannot be empty");
        }
        return enrollDate.compareTo(date.trim()) < 0;
    }

    public void addNotes(String note) {
        if (!HelperUtils.isValidText(note)) {
            throw new IllegalArgumentException("Note cannot be empty");
        }
        notes = notes.isEmpty() ? note.trim() : notes + " | " + note.trim();
    }

    public void addNotes(String note, String author) {
        if (!HelperUtils.isValidText(author)) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        addNotes(author.trim() + ": " + note);
    }

    @Override
    public void displayInfo() {
        System.out.println("Enrollment ID: " + enrollmentId);
        System.out.println("Student ID: " + studentId);
        System.out.println("Course ID: " + courseId);
        System.out.println("Enroll Date: " + enrollDate);
        System.out.println("Status: " + status);
        System.out.println("Reason: " + reason);
        System.out.println("Repeat: " + repeat);
        System.out.println("Notes: " + notes);
    }

    @Override
    public void displaySummary() {
        System.out.println(enrollmentId + " - " + studentId + " - " + courseId + " - " + status);
    }
}
