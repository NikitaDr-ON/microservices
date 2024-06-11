package com.client.storeClient.Repository;

import com.client.storeClient.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeImpl implements EmployeeRepository{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Employee someClass) {
        logger.info("insert into employee " + someClass.getFio() +" " + someClass.getProfession());
        jdbcTemplate.update("insert into employee values(?,?)", someClass.getFio(), someClass.getProfession());
    }

    @Override
    public Employee getById(Long id) {
        logger.info("Select * from employee where id = " + id);
        return jdbcTemplate.queryForObject("Select * from employee where id = ?", (rs, rowNum) ->{
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFio(rs.getString("name"));
            employee.setProfession(rs.getString("profession"));
            return employee;
        },new Object[] {id});
    }

    @Override
    public void update(Employee someClass) {
    }

    @Override
    public List<Employee> getAll() {
        return jdbcTemplate.query("Select * from employee where id = ?", (rs, rowNum) ->{
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFio(rs.getString("name"));
            employee.setProfession(rs.getString("profession"));
            return employee;
        });
    }

    @Override
    public Employee findByName(String name) {
        return jdbcTemplate.queryForObject("Select * from employee where id = ?", (rs, rowNum) ->{
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFio(rs.getString("name"));
            employee.setProfession(rs.getString("profession"));
            return employee;
        },new Object[] {name});
    }

    @Override
    public List<Employee> findByProfession(String profession) {
        return jdbcTemplate.query("Select * from employee where id = ?", (rs, rowNum) ->{
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFio(rs.getString("name"));
            employee.setProfession(rs.getString("profession"));
            return employee;
        },new Object[] {profession});
    }
}
