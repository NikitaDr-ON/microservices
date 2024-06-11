package com.client.storeClient.Repository;

import com.client.storeClient.model.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product>{
    @Override
    default void create(Product someClass) {
    }

    @Override
    default Product getById(Long id) {
        return null;
    }

    @Override
    default void update(Product someClass) {

    }

    @Override
    default List<Product> getAll() {
        return null;
    }

    @Override
    default Product findByName(String name) {
        return null;
    }
}
