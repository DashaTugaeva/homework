package org.example.DAO;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T, V> {
    Optional<T> get(V id);

    Collection<T> getAll();

    Optional<V> save(T t);

    void update(T t);

    void delete(T t);
}
