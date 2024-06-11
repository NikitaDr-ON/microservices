package com.client.storeClient.Repository;


import com.client.storeClient.model.Employee;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee>{
    @Override
    default void create(Employee someClass) {

    }

    @Override
    default Employee getById(Long id) {
        return null;
    }

    @Override
    default void update(Employee someClass) {

    }

    @Override
    default List<Employee> getAll() {
        return null;
    }

    @Override
    default Employee findByName(String name) {
        return null;
    }

    List<Employee> findByProfession(String profession);
}
