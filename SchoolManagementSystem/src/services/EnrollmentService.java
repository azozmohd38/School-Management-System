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
        return new Enrollment[0];
    }

    @Override
    public Object searchById(String id) {
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return searchById(id) != null;
    }
}
