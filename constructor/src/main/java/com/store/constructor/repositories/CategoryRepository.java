package com.store.constructor.repositories;

import com.store.constructor.models.Category;

import java.util.List;

public interface CategoryRepository extends Repository<Category> {

    @Override
    default void create(Category someClass) {

    }

    @Override
    default Category getById(Long id) {
        return null;
    }

    @Override
    default void update(Category someClass) {

    }

    @Override
    default List<Category> getAll() {
        return null;
    }

    @Override
    default Category findByName(String name) {
        return null;
    }
}
