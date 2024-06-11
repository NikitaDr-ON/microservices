package com.store.constructor.repositories;

import com.store.constructor.models.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParametersImpl implements ParametersRepository{

    private static final Logger logger = LoggerFactory.getLogger(ParametersImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Parameters someClass) {
        logger.info("insert into parameters (parameter,nameofparameter)" + someClass.getParameter() + " " + someClass.getNameOfParameter());
        jdbcTemplate.update("insert into parameters (parameter,nameofparameter) values(?,?)", someClass.getParameter(), someClass.getNameOfParameter());
    }

    @Override
    public Parameters getById(Long id) {
        logger.info("SELECT parameters.nameofparameter,parameters.parameter, category.id,category.name\n" +
                "FROM parameters \n" +
                "JOIN parametersofcategory \n" +
                "ON parameters.id = parametersofcategory.parameter\n" +
                "JOIN category\n" +
                "ON parametersofcategory.category = category.id where parameters.id = " + id +
                " GROUP BY parameters.nameofparameter");
        return jdbcTemplate.queryForObject("SELECT parameters.nameofparameter,parameters.parameter, category.id,category.name\n" +
                "FROM parameters \n" +
                "JOIN parametersofcategory \n" +
                "ON parameters.id = parametersofcategory.parameter\n" +
                "JOIN category\n" +
                "ON parametersofcategory.category = category.id where parameters.id = ? " +
                "GROUP BY parameters.nameofparameter", (rs, rowNum) ->{
            Parameters parameters = new Parameters();
            parameters.setParameter(rs.getString("parameter"));
            parameters.setNameOfParameter(rs.getString("nameofparameter"));
            parameters.setCategory(rs.getString("name"));
            parameters.setId(rs.getLong("id"));
            return parameters;
        }, new Object[] {id});
    }

    @Override
    public void update(Parameters someClass) {
    }

    @Override
    public List<Parameters> getAll() {
        logger.info("SELECT parameters.nameofparameter, parameters.parameter, category.id,category.name\n" +
                "FROM parameters \n" +
                "JOIN parametersofcategory \n" +
                "ON parameters.id = parametersofcategory.parameter\n" +
                "JOIN category\n" +
                "ON parametersofcategory.category = category.id");
        return jdbcTemplate.query("SELECT parameters.nameofparameter, parameters.parameter, category.id,category.name\n" +
                "FROM parameters \n" +
                "JOIN parametersofcategory \n" +
                "ON parameters.id = parametersofcategory.parameter\n" +
                "JOIN category\n" +
                "ON parametersofcategory.category = category.id", (rs, rowNum) ->{
            Parameters parameters = new Parameters();
            parameters.setParameter(rs.getString("parameter"));
            parameters.setNameOfParameter(rs.getString("nameofparameter"));
            parameters.setCategory(rs.getString("name"));
            return parameters;
        });
    }

    @Override
    public Parameters findByName(String name) {
        try {
            logger.info("SELECT parameters.id as parameterId,parameters.nameofparameter,parameters.parameter,category.id, category.name\n" +
                    "FROM parameters \n" +
                    "JOIN parametersofcategory \n" +
                    "ON parameters.id = parametersofcategory.parameter\n" +
                    "JOIN category\n" +
                    "ON parametersofcategory.category = category.id where parameters.parameter = " + name);
            return jdbcTemplate.queryForObject("SELECT parameters.id as parameterId,parameters.nameofparameter,parameters.parameter,category.id, category.name\n" +
                    "FROM parameters \n" +
                    "JOIN parametersofcategory \n" +
                    "ON parameters.id = parametersofcategory.parameter\n" +
                    "JOIN category\n" +
                    "ON parametersofcategory.category = category.id where parameters.parameter = ?", (rs, rowNum) -> {
                Parameters parameters = new Parameters();
                parameters.setParameter(rs.getString("parameter"));
                parameters.setNameOfParameter(rs.getString("nameofparameter"));
                parameters.setCategory(rs.getString("name"));
                parameters.setId(rs.getLong("parameterId"));
                return parameters;
            }, new Object[]{name});
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }


        public Parameters findByParameter(String parameter){
            try{
                logger.info("SELECT parameters.id as parameterId,parameters.nameofparameter,parameters.parameter from parameters where parameters.parameter = " + parameter);
                return jdbcTemplate.queryForObject("SELECT parameters.id as parameterId,parameters.nameofparameter,parameters.parameter from parameters where parameters.parameter = ?", (rs, rowNum) ->{
                    Parameters parameters = new Parameters();
                    parameters.setParameter(rs.getString("parameter"));
                    parameters.setNameOfParameter(rs.getString("nameofparameter"));
                    parameters.setId(rs.getLong("parameterId"));
                    return parameters;
                }, new Object[] {parameter});
            } catch (
                    EmptyResultDataAccessException e){
                return null;
            }
    }



}
