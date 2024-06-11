package com.store.constructor.repositories;

import com.store.constructor.models.Characteristics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacteristicsImpl{

    private static final Logger logger = LoggerFactory.getLogger(CharacteristicsImpl.class);
    @Autowired
    JdbcTemplate jdbcTemplate;
    public Characteristics getCount(){
        logger.info("SELECT COUNT(*) as count FROM characteristics");
       return jdbcTemplate.queryForObject("SELECT COUNT(*) as count FROM characteristics", (rs, rowNum) ->{
            Characteristics characteristics = new Characteristics();
            characteristics.setId(rs.getLong("count"));
            return characteristics;
        });
    }

    public void create(Characteristics someClass) {
        logger.info("insert into characteristics (id) " + someClass.getId()+1);
        jdbcTemplate.update("insert into characteristics (id) values(?)", someClass.getId()+1);

    }

    public List<Characteristics> getById(Long id) {
        logger.info("SELECT  parameters.id, parameters.parameter, parameters.nameofparameter\n" +
                "FROM parameters \n" +
                "JOIN parametersofcharacteristics \n" +
                "ON parameters.id = parametersofcharacteristics.parameters\n" +
                "JOIN characteristics\n" +
                "ON parametersofcharacteristics.characteristics = characteristics.id where characteristics.id = " + id);

        return jdbcTemplate.query("SELECT  parameters.id, parameters.parameter, parameters.nameofparameter\n" +
                "FROM parameters \n" +
                "JOIN parametersofcharacteristics \n" +
                "ON parameters.id = parametersofcharacteristics.parameters\n" +
                "JOIN characteristics\n" +
                "ON parametersofcharacteristics.characteristics = characteristics.id where characteristics.id = ?", (rs, rowNum) ->{
            Characteristics characteristics = new Characteristics();
            //characteristics.setId(rs.getInt("id"));
            characteristics.setParameter(rs.getString("parameter"));
            characteristics.setNameOfParameter(rs.getString("nameofparameter"));
            characteristics.setParameterId(rs.getLong("id"));
            return characteristics;
        },new Object[] {id});
    }

    public List<Characteristics> getByParameterAndId(String parameter, Long id) {
        logger.info("SELECT  parameters.id, parameters.parameter, parameters.nameofparameter\n" +
                "FROM parameters \n" +
                "JOIN parametersofcharacteristics \n" +
                "ON parameters.id = parametersofcharacteristics.parameters AND parameters.parameter = " + parameter + "\n" +
                "JOIN characteristics\n" +
                "ON parametersofcharacteristics.characteristics = characteristics.id where characteristics.id = " + id);

        return jdbcTemplate.query("SELECT  parameters.id, parameters.parameter, parameters.nameofparameter\n" +
                "FROM parameters \n" +
                "JOIN parametersofcharacteristics \n" +
                "ON parameters.id = parametersofcharacteristics.parameters AND parameters.parameter = ?\n" +
                "JOIN characteristics\n" +
                "ON parametersofcharacteristics.characteristics = characteristics.id where characteristics.id = ?", (rs, rowNum) ->{
            Characteristics characteristics = new Characteristics();
            //characteristics.setId(rs.getInt("id"));
            characteristics.setParameter(rs.getString("parameter"));
            characteristics.setNameOfParameter(rs.getString("nameofparameter"));
            characteristics.setParameterId(rs.getLong("id"));
            return characteristics;
        },new Object[] {parameter, id});
    }




}
