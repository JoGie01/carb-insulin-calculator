package proto.storage;

import java.util.List;

public interface Repository <T> {

    void save(T item);
    T findByName(String name);
    List<T> findAll();
    
}
