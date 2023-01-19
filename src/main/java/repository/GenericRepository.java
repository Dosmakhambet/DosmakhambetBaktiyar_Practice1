package repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository <T,ID>{
    void create(T t);

    T get(ID id);

    List<T> getAll();

    void delete(ID id);

    int update(T object);
}
