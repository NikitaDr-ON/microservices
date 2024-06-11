package com.store.constructor.repositories;

import com.store.constructor.models.Characteristics;
import com.store.constructor.models.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParametersOfCharacteristicsImpl {

    private static final Logger logger = LoggerFactory.getLogger(ParametersOfCharacteristicsImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Parameters parameters, Characteristics characteristics) {
        logger.info("insert into parametersOfCharacteristics " + parameters.getId() + " " + characteristics.getId());
        jdbcTemplate.update("insert into parametersOfCharacteristics values(?,?)", parameters.getId(),characteristics.getId());
    }
}
