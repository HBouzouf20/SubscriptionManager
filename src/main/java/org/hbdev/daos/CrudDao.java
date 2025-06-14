package org.hbdev.daos;

public interface CrudDao<T, ID>{
    T save(T entity);
    void deleteById(ID id);
    T findById(ID id);
    Iterable<T> findAll();
    T update(T entity, ID id);
    void delete(T entity);
    void deleteAll();
}
