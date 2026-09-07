package entities;

import utils.HelperUtils;

public class SeniorStudent extends Student {

    private String major;
    private double gpa;
    private String graduationDate;
    private int creditsEarned;
    private boolean graduated;

    public SeniorStudent(String id, String firstName, String lastName, String dateOfBirth,
                         String gender, String phoneNumber, String email, String address,
                         String nationalId, int age, boolean active, int gradeLevel,
                         String enrollmentDate, double feeBalance, boolean scholarship,
                         String major, double gpa, String graduationDate, int creditsEarned) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address,
                nationalId, age, active, gradeLevel, enrollmentDate, feeBalance, scholarship);
        setMajor(major);
        setGpa(gpa);
        setGraduationDate(graduationDate);
        setCreditsEarned(creditsEarned);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if (!HelperUtils.isValidText(major)) {
            throw new IllegalArgumentException("Major cannot be empty");
        }
        this.major = major.trim();
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (!HelperUtils.isInRange(gpa, 0.0, 4.0)) {
            throw new IllegalArgumentException("GPA must be between 0 and 4");
        }
        this.gpa = gpa;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        if (!HelperUtils.isValidText(graduationDate)) {
            throw new IllegalArgumentException("Graduation date cannot be empty");
        }
        this.graduationDate = graduationDate.trim();
    }

    public int getCreditsEarned() {
        return creditsEarned;
    }

    public void setCreditsEarned(int creditsEarned) {
        if (creditsEarned < 0) {
            throw new IllegalArgumentException("Credits cannot be negative");
        }
        this.creditsEarned = creditsEarned;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public boolean isReadyToGraduate() {
        return creditsEarned >= 120 && gpa >= 2.0;
    }

    public void promote(String major, double gpa, String graduationDate, int creditsEarned) {
        setMajor(major);
        setGpa(gpa);
        setGraduationDate(graduationDate);
        setCreditsEarned(creditsEarned);
        graduated = false;
    }

    public void graduate() {
        graduated = true;
        setActive(false);
    }

    public int totalCredits() {
        return creditsEarned;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Major: " + major);
        System.out.println("GPA: " + gpa);
        System.out.println("Graduation Date: " + graduationDate);
        System.out.println("Credits Earned: " + creditsEarned);
        System.out.println("Graduated: " + graduated);
    }

    @Override
    public void displaySummary() {
        System.out.println(getId() + " - " + getFullName() + " - " + major + " - GPA " + gpa);
    }
}
