package services;

import entities.HeadTeacher;
import entities.Teacher;
import interfaces.Manageable;
import interfaces.Searchable;

public class TeacherService implements Manageable, Searchable {

    private Teacher[] teachers;
    private int count;

    public TeacherService() {
        teachers = new Teacher[50];
    }

    @Override
    public boolean add(Object entity) {
        if (!(entity instanceof Teacher) || count >= teachers.length) {
            return false;
        }
        teachers[count++] = (Teacher) entity;
        return true;
    }

    @Override
    public boolean removeById(String id) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < count - 1; j++) {
                    teachers[j] = teachers[j + 1];
                }
                teachers[--count] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] getAll() {
        Teacher[] result = new Teacher[count];
        for (int i = 0; i < count; i++) {
            result[i] = teachers[i];
        }
        return result;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public Object[] search(String keyword) {
        return new Teacher[0];
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
