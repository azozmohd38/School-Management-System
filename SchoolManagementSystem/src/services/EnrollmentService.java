package services;

import entities.CourseRecord;
import entities.Enrollment;
import entities.Student;
import interfaces.Manageable;
import interfaces.Searchable;
import utils.HelperUtils;

public class EnrollmentService implements Manageable, Searchable {

    private Enrollment[] enrollments;
    private int count;

    public EnrollmentService() {
        enrollments = new Enrollment[80];
    }

    @Override
    public boolean add(Object entity) {
        if (!(entity instanceof Enrollment) || count >= enrollments.length) {
            return false;
        }
        enrollments[count++] = (Enrollment) entity;
        return true;
    }

    @Override
    public boolean removeById(String id) {
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getEnrollmentId().equalsIgnoreCase(id)) {
                for (int j = i; j < count - 1; j++) {
                    enrollments[j] = enrollments[j + 1];
                }
                enrollments[--count] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] getAll() {
        Enrollment[] result = new Enrollment[count];
        for (int i = 0; i < count; i++) {
            result[i] = enrollments[i];
        }
        return result;
    }

    public int count() {
        return count;
    }

    @Override
    public Object[] search(String keyword) {
        Enrollment[] matches = new Enrollment[count];
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudentId().toLowerCase().contains(keyword.toLowerCase())
                    || enrollments[i].getCourseId().toLowerCase().contains(keyword.toLowerCase())
                    || enrollments[i].getStatus().toLowerCase().contains(keyword.toLowerCase())) {
                matches[matchCount++] = enrollments[i];
            }
        }
        Enrollment[] result = new Enrollment[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = matches[i];
        }
        return result;
    }

    @Override
    public Object searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getEnrollmentId().equalsIgnoreCase(id)) {
                return enrollments[i];
            }
        }
        return null;
    }

    public Enrollment enroll(String studentId, String courseId, String date) {
        Enrollment enrollment = new Enrollment(
                HelperUtils.generateId("ENR-"),
                studentId,
                courseId,
                date,
                "ACTIVE",
                "",
                false
        );
        add(enrollment);
        return enrollment;
    }

    public Enrollment enroll(String studentId, String courseId, String date, String term) {
        Enrollment enrollment = enroll(studentId, courseId, date);
        enrollment.addNotes("Term: " + term);
        return enrollment;
    }

    public Enrollment enroll(Student student, CourseRecord course, String date, String reason) {
        Enrollment enrollment = new Enrollment(
                HelperUtils.generateId("ENR-"),
                student.getId(),
                course.getRecordId(),
                date,
                "ACTIVE",
                reason,
                false
        );
        return add(enrollment) ? enrollment : null;
    }

    public boolean cancel(String enrollmentId) {
        Enrollment enrollment = (Enrollment) searchById(enrollmentId);
        if (enrollment == null) {
            return false;
        }
        enrollment.cancel();
        return true;
    }

    public boolean complete(String enrollmentId) {
        Enrollment enrollment = (Enrollment) searchById(enrollmentId);
        if (enrollment == null) {
            return false;
        }
        enrollment.complete();
        return true;
    }

    public boolean transfer(String enrollmentId, String newCourseId, String newDate) {
        Enrollment enrollment = (Enrollment) searchById(enrollmentId);
        if (enrollment == null) {
            return false;
        }
        enrollment.transfer(newCourseId, newDate);
        return true;
    }

    public Enrollment[] listByStatus(String status) {
        Enrollment[] matches = new Enrollment[count];
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStatus().equalsIgnoreCase(status)) {
                matches[matchCount++] = enrollments[i];
            }
        }
        Enrollment[] result = new Enrollment[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = matches[i];
        }
        return result;
    }

    public Enrollment[] listByStudent(String studentId) {
        Enrollment[] matches = new Enrollment[count];
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudentId().equalsIgnoreCase(studentId)) {
                matches[matchCount++] = enrollments[i];
            }
        }
        Enrollment[] result = new Enrollment[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = matches[i];
        }
        return result;
    }

    public boolean existsById(String id) {
        return searchById(id) != null;
    }
}
