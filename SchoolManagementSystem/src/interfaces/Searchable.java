package interfaces;

public interface Searchable {

    Object[] search(String keyword);

    Object searchById(String id);

    boolean existsById(String id);

    default boolean hasMatches(String keyword) {
        Object[] results = search(keyword);
        return results != null && results.length > 0;
    }
}
