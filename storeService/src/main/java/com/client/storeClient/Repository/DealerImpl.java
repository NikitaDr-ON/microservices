package com.client.storeClient.Repository;

import com.client.storeClient.model.Dealer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DealerImpl implements DealerRepository{

    private static final Logger logger = LoggerFactory.getLogger(DealerImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Dealer dealer) {
        logger.info("insert into Dealer (name) " + dealer.getName());
        jdbcTemplate.update("insert into Dealer (name) values(?)", dealer.getName());
    }

    @Override
    public Dealer getById(Long id) {

        logger.info("Select * from Dealer where id = " + id);

        return jdbcTemplate.queryForObject("Select * from Dealer where id = ?", (rs, rowNum) ->{
            Dealer dealer = new Dealer();
            dealer.setId(rs.getInt("id"));
            dealer.setName(rs.getString("name"));
            return dealer;
        },new Object[] {id});
    }

    @Override
    public void update(Dealer dealer) {

    }

    @Override
    public List<Dealer> getAll() {

        logger.info("Select * from Dealer");

        return jdbcTemplate.query("Select * from Dealer", (rs, rowNum) ->{
            Dealer dealer = new Dealer();
            dealer.setId(rs.getInt("id"));
            dealer.setName(rs.getString("name"));
            return dealer;
        });
    }

    @Override
    public Dealer findByName(String name) {
        try {
            logger.info("Select * from Dealer where name = " + name);
            return jdbcTemplate.queryForObject("Select * from Dealer where name = ?", (rs, rowNum) -> {
                Dealer dealer = new Dealer();
                dealer.setId(rs.getInt("id"));
                dealer.setName(rs.getString("name"));
                return dealer;
            }, new Object[]{name});
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }
}
