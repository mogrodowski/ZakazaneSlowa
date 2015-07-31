package pl.mogrodowski.zakazaneslowa.data;

import java.util.List;

public interface DaoI<T> {

    long save(T type);

    void update(T type);

    void delete(T type);

    T get(long id);

    List<T> getAll();
}
