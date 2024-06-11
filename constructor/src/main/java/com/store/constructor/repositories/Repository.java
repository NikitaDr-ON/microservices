package com.store.constructor.repositories;

import java.util.List;

public interface Repository<T> {

    void create(T someClass);
    T getById(Long id);
    void update(T someClass);
    List<T> getAll();
    T findByName(String name);

}
