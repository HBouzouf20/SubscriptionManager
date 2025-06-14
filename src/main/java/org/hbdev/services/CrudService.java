package org.hbdev.services;

public interface CrudService<T, ID> {
    T save(T entity);
    void deleteById(ID id);
    T findById(ID id);
    Iterable<T> findAll();
    void delete(T entity);
    void deleteAll();
    T update(T entity, ID id);
}
