package com.store.constructor.repositories;

import com.store.constructor.models.Parameters;
import com.store.constructor.models.ParametersOfCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParametersOfCategoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(ParametersOfCategoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createCpu(Parameters someClass) {
        logger.info("insert into parametersOfCategory " + someClass.getId() + " " + 1);
        jdbcTemplate.update("insert into parametersOfCategory values(?,?)", someClass.getId(),1);
    }
    public void createGpu(Parameters someClass) {
        logger.info("insert into parametersOfCategory " + someClass.getId() + " " + 2);
        jdbcTemplate.update("insert into parametersOfCategory values(?,?)", someClass.getId(),2);
    }
    public void createRam(Parameters someClass) {
        logger.info("insert into parametersOfCategory " + someClass.getId() + " " + 3);
        jdbcTemplate.update("insert into parametersOfCategory values(?,?)", someClass.getId(),3);
    }

    public void createPower(Parameters someClass) {
        logger.info("insert into parametersOfCategory " + someClass.getId() + " " + 4);
        jdbcTemplate.update("insert into parametersOfCategory values(?,?)", someClass.getId(),4);
    }

    public void createCooler(Parameters someClass) {
        logger.info("insert into parametersOfCategory " + someClass.getId() + " " + 5);
        jdbcTemplate.update("insert into parametersOfCategory values(?,?)", someClass.getId(),5);
    }

    public void createBody(Parameters someClass) {
        logger.info("insert into parametersOfCategory " + someClass.getId() + " " + 6);
        jdbcTemplate.update("insert into parametersOfCategory values(?,?)", someClass.getId(),6);
    }

    public void createSsd(Parameters someClass) {
        logger.info("insert into parametersOfCategory " + someClass.getId() + " " + 7);
        jdbcTemplate.update("insert into parametersOfCategory values(?,?)", someClass.getId(),7);
    }

    public void createMotherboard(Parameters someClass) {
        logger.info("insert into parametersOfCategory " + someClass.getId() + " " + 8);
        jdbcTemplate.update("insert into parametersOfCategory values(?,?)", someClass.getId(),8);
    }

    public ParametersOfCategory check(long id) {
        try{
            logger.info("SELECT parametersofcategory.* FROM parametersofcategory WHERE category=1 AND parametersofcategory.parameter= " + id);
       return jdbcTemplate.queryForObject("SELECT parametersofcategory.* FROM parametersofcategory WHERE category=1 AND parametersofcategory.parameter=?", (rs, rowNum) -> {
            ParametersOfCategory parametersOfCategory = new ParametersOfCategory();
            parametersOfCategory.setParameterId(rs.getLong("parameter"));
            parametersOfCategory.setCategoryId(rs.getLong("category"));
            return parametersOfCategory;
        }, new Object[]{id});
        } catch (EmptyResultDataAccessException e){
            return null;
        }
        }
    }
