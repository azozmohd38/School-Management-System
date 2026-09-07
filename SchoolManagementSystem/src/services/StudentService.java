package services;

import entities.Student;
import interfaces.Manageable;
import interfaces.Searchable;

public class StudentService implements Manageable, Searchable {

    private Student[] students;
    private int count;

    public StudentService() {
        students = new Student[50];
    }

    @Override
    public boolean add(Object entity) {
        if (!(entity instanceof Student) || count >= students.length) {
            return false;
        }
        students[count++] = (Student) entity;
        return true;
    }

    @Override
    public boolean removeById(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[--count] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] getAll() {
        Student[] result = new Student[count];
        for (int i = 0; i < count; i++) {
            result[i] = students[i];
        }
        return result;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public Object[] search(String keyword) {
        Student[] matches = new Student[count];
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (students[i].getFullName().toLowerCase().contains(keyword.toLowerCase())) {
                matches[matchCount++] = students[i];
            }
        }
        Student[] result = new Student[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = matches[i];
        }
        return result;
    }

    @Override
    public Object searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equalsIgnoreCase(id)) {
                return students[i];
            }
        }
        return null;
    }

    public Student addStudent(String id, String firstName, String lastName) {
        Student student = new Student(id, firstName, lastName, "N/A", "N/A",
                "00000000", "N/A", "N/A", "N/A", 0, true,
                1, "N/A", 0.0, false);
        add(student);
        return student;
    }

    public Student addStudent(String id, String firstName, String lastName, int gradeLevel) {
        Student student = new Student(id, firstName, lastName, "N/A", "N/A",
                "00000000", "N/A", "N/A", "N/A", 0, true,
                gradeLevel, "N/A", 0.0, false);
        add(student);
        return student;
    }

    public Student addStudent(Student student) {
        return add(student) ? student : null;
    }

    public boolean updateContact(String id, String phoneNumber) {
        Student student = (Student) searchById(id);
        if (student == null) {
            return false;
        }
        student.updateContact(phoneNumber);
        return true;
    }

    public boolean updateContact(String id, String phoneNumber, String email) {
        Student student = (Student) searchById(id);
        if (student == null) {
            return false;
        }
        student.updateContact(phoneNumber, email);
        return true;
    }

    public Student[] listSeniors() {
        Student[] matches = new Student[count];
        int seniorCount = 0;
        for (int i = 0; i < count; i++) {
            if (students[i] instanceof entities.SeniorStudent) {
                matches[seniorCount++] = students[i];
            }
        }
        Student[] result = new Student[seniorCount];
        for (int i = 0; i < seniorCount; i++) {
            result[i] = matches[i];
        }
        return result;
    }

    public double totalOutstanding() {
        double total = 0.0;
        for (int i = 0; i < count; i++) {
            total += students[i].getFeeBalance();
        }
        return total;
    }

    @Override
    public boolean existsById(String id) {
        return searchById(id) != null;
    }
}
