package interfaces;

public interface Searchable {
    Object[] search(String keyword);
    Object searchById(String id);
}
