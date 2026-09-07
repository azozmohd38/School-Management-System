package services;

import entities.Enrollment;
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

    @Override
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

    @Override
    public boolean existsById(String id) {
        return searchById(id) != null;
    }
}
