package com.store.constructor.repositories;

import com.store.constructor.models.Buyer;

import java.util.List;

public interface BuyerRepository extends Repository<Buyer>{
    @Override
    default void create(Buyer someClass) {

    }

    @Override
    default Buyer getById(Long id) {
        return null;
    }

    @Override
    default void update(Buyer someClass) {

    }

    @Override
    default List<Buyer> getAll() {
        return null;
    }

    @Override
    default Buyer findByName(String fio) {
        return null;
    }

    default Buyer findByMail(String mail) {
        return null;
    }

    default List<Buyer> getByActivation(boolean activation) {
        return null;
    }
}
