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
        Teacher[] matches = new Teacher[count];
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (teachers[i].getFullName().toLowerCase().contains(keyword.toLowerCase())
                    || teachers[i].getSubject().toLowerCase().contains(keyword.toLowerCase())) {
                matches[matchCount++] = teachers[i];
            }
        }
        Teacher[] result = new Teacher[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = matches[i];
        }
        return result;
    }

    @Override
    public Object searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getId().equalsIgnoreCase(id)) {
                return teachers[i];
            }
        }
        return null;
    }

    public Teacher addTeacher(Teacher teacher) {
        return add(teacher) ? teacher : null;
    }

    public HeadTeacher addHeadTeacher(HeadTeacher teacher) {
        return add(teacher) ? teacher : null;
    }

    public boolean assignClass(String teacherId, String classId) {
        Teacher teacher = (Teacher) searchById(teacherId);
        return teacher != null && teacher.assignClass(classId);
    }

    @Override
    public boolean existsById(String id) {
        return searchById(id) != null;
    }
}
