package entities;

import utils.HelperUtils;

public class Student extends Person {

    private int gradeLevel;
    private String enrollmentDate;
    private String[] enrolledSubjects;
    private int subjectCount;
    private String[] pastCourseRecordIds;
    private int recordCount;
    private double feeBalance;
    private boolean scholarship;

    public Student(String id, String firstName, String lastName, String dateOfBirth,
                   String gender, String phoneNumber, String email, String address,
                   String nationalId, int age, boolean active, int gradeLevel,
                   String enrollmentDate, double feeBalance, boolean scholarship) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, nationalId, age, active);
        setGradeLevel(gradeLevel);
        setEnrollmentDate(enrollmentDate);
        setFeeBalance(feeBalance);
        setScholarship(scholarship);
        enrolledSubjects = new String[20];
        pastCourseRecordIds = new String[30];
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        if (!HelperUtils.isInRange(gradeLevel, 1, 12)) {
            throw new IllegalArgumentException("Grade level must be between 1 and 12");
        }
        this.gradeLevel = gradeLevel;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        if (!HelperUtils.isValidText(enrollmentDate)) {
            throw new IllegalArgumentException("Enrollment date cannot be empty");
        }
        this.enrollmentDate = enrollmentDate.trim();
    }

    public double getFeeBalance() {
        return feeBalance;
    }

    public void setFeeBalance(double feeBalance) {
        if (feeBalance < 0) {
            throw new IllegalArgumentException("Fee balance cannot be negative");
        }
        this.feeBalance = feeBalance;
    }

    public boolean isScholarship() {
        return scholarship;
    }

    public void setScholarship(boolean scholarship) {
        this.scholarship = scholarship;
    }

    public boolean addSubject(String subject) {
        if (!HelperUtils.isValidText(subject) || subjectCount >= enrolledSubjects.length || hasSubject(subject)) {
            return false;
        }
        enrolledSubjects[subjectCount++] = subject.trim();
        return true;
    }

    public boolean hasSubject(String subject) {
        if (subject == null) {
            return false;
        }
        for (int i = 0; i < subjectCount; i++) {
            if (enrolledSubjects[i].equalsIgnoreCase(subject.trim())) {
                return true;
            }
        }
        return false;
    }

    public void listSubjects() {
        for (int i = 0; i < subjectCount; i++) {
            System.out.println(enrolledSubjects[i]);
        }
    }

    public boolean addRecordId(String recordId) {
        if (!HelperUtils.isValidText(recordId) || recordCount >= pastCourseRecordIds.length) {
            return false;
        }
        pastCourseRecordIds[recordCount++] = recordId.trim();
        return true;
    }

    public int getSubjectCount() {
        return subjectCount;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void addToBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        setFeeBalance(feeBalance + amount);
    }

    public void clearBalance() {
        setFeeBalance(0);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Grade Level: " + gradeLevel);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Fee Balance: " + feeBalance);
        System.out.println("Scholarship: " + scholarship);
    }

    @Override
    public void displaySummary() {
        System.out.println(getId() + " - " + getFullName() + " - Grade " + gradeLevel);
    }
}
