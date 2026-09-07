package interfaces;

public interface Manageable {

    boolean add(Object entity);

    boolean removeById(String id);

    Object[] getAll();
}
