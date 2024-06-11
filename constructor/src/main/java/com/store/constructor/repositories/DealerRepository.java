package com.store.constructor.repositories;


import com.store.constructor.models.Dealer;

import java.util.List;

public interface DealerRepository extends Repository<Dealer> {
    @Override
    default void create(Dealer someClass) {

    }

    @Override
    default Dealer getById(Long id) {
        return null;
    }

    @Override
    default void update(Dealer someClass) {

    }

    @Override
    default List<Dealer> getAll() {
        return null;
    }

    @Override
    default Dealer findByName(String name) {
        return null;
    }
}
