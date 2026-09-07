package services;

import entities.CourseRecord;
import interfaces.Manageable;
import interfaces.Searchable;

public class CourseService implements Manageable, Searchable {

    private CourseRecord[] records;
    private int count;

    public CourseService() {
        records = new CourseRecord[60];
    }

    @Override
    public boolean add(Object entity) {
        if (!(entity instanceof CourseRecord) || count >= records.length) {
            return false;
        }
        records[count++] = (CourseRecord) entity;
        return true;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

    @Override
    public Object[] getAll() {
        CourseRecord[] result = new CourseRecord[count];
        for (int i = 0; i < count; i++) {
            result[i] = records[i];
        }
        return result;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public Object[] search(String keyword) {
        return new CourseRecord[0];
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
