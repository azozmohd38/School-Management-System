package interfaces;

public interface Manageable {

    boolean add(Object entity);

    boolean removeById(String id);

    Object[] getAll();

    int count();

    default boolean isEmpty() {
        return count() == 0;
    }
}
