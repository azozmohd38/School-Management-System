package entities;

import utils.HelperUtils;

public class Teacher extends Person {

    private String subject;
    private int experienceYears;
    private double salary;
    private String[] availableTimeSlots;
    private int slotCount;
    private String[] assignedClassIds;
    private int classCount;
    private boolean formTeacher;
    private String lastSalaryReason;

    public Teacher(String id, String firstName, String lastName, String dateOfBirth,
                   String gender, String phoneNumber, String email, String address,
                   String nationalId, int age, boolean active, String subject,
                   int experienceYears, double salary, boolean formTeacher) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, nationalId, age, active);
        setSubject(subject);
        setExperienceYears(experienceYears);
        setSalary(salary);
        setFormTeacher(formTeacher);
        availableTimeSlots = new String[20];
        assignedClassIds = new String[20];
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (!HelperUtils.isValidText(subject)) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }
        this.subject = subject.trim();
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new IllegalArgumentException("Experience years cannot be negative");
        }
        this.experienceYears = experienceYears;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    public boolean isFormTeacher() {
        return formTeacher;
    }

    public void setFormTeacher(boolean formTeacher) {
        this.formTeacher = formTeacher;
    }

    public boolean addSlot(String slot) {
        if (!HelperUtils.isValidText(slot) || slotCount >= availableTimeSlots.length || hasSlot(slot)) {
            return false;
        }
        availableTimeSlots[slotCount++] = slot.trim();
        return true;
    }

    public boolean removeSlot(String slot) {
        for (int i = 0; i < slotCount; i++) {
            if (availableTimeSlots[i].equalsIgnoreCase(slot)) {
                for (int j = i; j < slotCount - 1; j++) {
                    availableTimeSlots[j] = availableTimeSlots[j + 1];
                }
                availableTimeSlots[--slotCount] = null;
                return true;
            }
        }
        return false;
    }

    public boolean hasSlot(String slot) {
        if (slot == null) {
            return false;
        }
        for (int i = 0; i < slotCount; i++) {
            if (availableTimeSlots[i].equalsIgnoreCase(slot.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean assignClass(String classId) {
        if (!HelperUtils.isValidText(classId) || classCount >= assignedClassIds.length) {
            return false;
        }
        assignedClassIds[classCount++] = classId.trim();
        return true;
    }

    public int getClassLoad() {
        return classCount;
    }

    public void raiseSalary(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Salary increase must be positive");
        }
        setSalary(salary + amount);
    }

    public void updateSalary(double salary) {
        setSalary(salary);
        lastSalaryReason = null;
    }

    public void updateSalary(double salary, String reason) {
        setSalary(salary);
        lastSalaryReason = HelperUtils.isValidText(reason) ? reason.trim() : null;
    }

    public String getLastSalaryReason() {
        return lastSalaryReason;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Subject: " + subject);
        System.out.println("Experience Years: " + experienceYears);
        System.out.println("Salary: " + salary);
        System.out.println("Form Teacher: " + formTeacher);
    }

    @Override
    public void displaySummary() {
        System.out.println(getId() + " - " + getFullName() + " - " + subject);
    }
}
