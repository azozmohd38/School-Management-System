package entities;

import interfaces.Displayable;
import utils.HelperUtils;

public class CourseRecord implements Displayable {

    private String recordId;
    private String studentId;
    private String teacherId;
    private String term;
    private double grade;
    private String remarks;
    private String notes;
    private boolean finalized;

    public CourseRecord(String recordId, String studentId, String teacherId, String term,
                        double grade, String remarks, String notes, boolean finalized) {
        setRecordId(recordId);
        setStudentId(studentId);
        setTeacherId(teacherId);
        setTerm(term);
        setGrade(grade);
        setRemarks(remarks);
        setNotes(notes);
        this.finalized = finalized;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        if (!HelperUtils.isValidText(recordId)) {
            throw new IllegalArgumentException("Record id cannot be empty");
        }
        this.recordId = recordId.trim();
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        if (!HelperUtils.isValidText(teacherId)) {
            throw new IllegalArgumentException("Teacher id cannot be empty");
        }
        this.teacherId = teacherId.trim();
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        if (!HelperUtils.isValidText(term)) {
            throw new IllegalArgumentException("Term cannot be empty");
        }
        this.term = term.trim();
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (!HelperUtils.isInRange(grade, 0.0, 100.0)) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? "" : remarks.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? "" : notes.trim();
    }

    public void appendNote(String note) {
        if (!HelperUtils.isValidText(note)) {
            throw new IllegalArgumentException("Note cannot be empty");
        }
        notes = notes.isEmpty() ? note.trim() : notes + " | " + note.trim();
    }

    public void finalizeRecord() {
        finalized = true;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public boolean isPassed() {
        return grade >= 50.0;
    }

    @Override
    public void displayInfo() {
        System.out.println("Record ID: " + recordId);
        System.out.println("Student ID: " + studentId);
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Term: " + term);
        System.out.println("Grade: " + grade);
        System.out.println("Remarks: " + remarks);
        System.out.println("Notes: " + notes);
        System.out.println("Finalized: " + finalized);
    }

    @Override
    public void displaySummary() {
        System.out.println(recordId + " - " + studentId + " - " + term + " - " + grade);
    }
}
