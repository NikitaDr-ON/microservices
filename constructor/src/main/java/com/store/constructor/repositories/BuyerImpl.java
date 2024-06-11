package com.store.constructor.repositories;


import com.store.constructor.models.Buyer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuyerImpl implements BuyerRepository {

    private static final Logger logger = LoggerFactory.getLogger(BuyerImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Buyer someClass) {
        jdbcTemplate.update("insert into buyer (mail) values(?)", someClass.getMail());
        logger.info("insert into buyer (mail)" + someClass.getMail());
    }


    @Override
    public void update(Buyer someClass) {
    }

    @Override
    public List<Buyer> getAll() {
        logger.info("Select mail from buyer");
        return jdbcTemplate.query("Select mail from buyer", (rs, rowNum) ->{
            Buyer buyer = new Buyer();
            buyer.setMail(rs.getString("mail"));
            return buyer;
        });
    }

    @Override
    public Buyer findByMail(String mail) {
        try {
            logger.info("Select mail from buyer where mail = " + mail);
            return jdbcTemplate.queryForObject("Select mail from buyer where mail = ?", (rs, rowNum) -> {
                Buyer buyer = new Buyer();
                buyer.setMail(rs.getString("mail"));
                return buyer;
            }, new Object[]{mail});
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }


}
