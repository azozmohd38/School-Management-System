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
        for (int i = 0; i < count; i++) {
            if (records[i].getRecordId().equalsIgnoreCase(id)) {
                for (int j = i; j < count - 1; j++) {
                    records[j] = records[j + 1];
                }
                records[--count] = null;
                return true;
            }
        }
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
        CourseRecord[] matches = new CourseRecord[count];
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (records[i].getStudentId().toLowerCase().contains(keyword.toLowerCase())
                    || records[i].getTeacherId().toLowerCase().contains(keyword.toLowerCase())
                    || records[i].getTerm().toLowerCase().contains(keyword.toLowerCase())) {
                matches[matchCount++] = records[i];
            }
        }
        CourseRecord[] result = new CourseRecord[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = matches[i];
        }
        return result;
    }

    @Override
    public Object searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (records[i].getRecordId().equalsIgnoreCase(id)) {
                return records[i];
            }
        }
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return searchById(id) != null;
    }
}
