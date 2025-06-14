package org.hbdev.services;

public interface HelperService<T, ID> {
    long count();
    boolean existsById(ID id);
}
