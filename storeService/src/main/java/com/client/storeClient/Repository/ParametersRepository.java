package com.client.storeClient.Repository;

import com.client.storeClient.model.Parameters;

import java.util.List;

public interface ParametersRepository extends Repository<Parameters>{
    @Override
    default void create(Parameters someClass) {

    }

    @Override
    default Parameters getById(Long id) {
        return null;
    }

    @Override
    default void update(Parameters someClass) {

    }

    @Override
    default List<Parameters> getAll(){
        return null;
    }

    @Override
    default Parameters findByName(String name) {
        return null;
    }
}
