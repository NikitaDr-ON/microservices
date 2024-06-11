package com.store.constructor.repositories;

import com.store.constructor.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderImpl {

    private static final Logger logger = LoggerFactory.getLogger(OrderImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void create(String mail){
        logger.info("insert into `order` (buyer) " + mail);
        jdbcTemplate.update("insert into `order` (buyer) values(?)", mail);
    }

    public void update(long id, int price){
        logger.info("update `order` set complete = " + 1 + ", price = " + price + ", orderDate = curdate() where id = " + id );
        jdbcTemplate.update("update `order` set complete = ?, price = ?, orderDate = curdate() where id = ?", 1, price,id);
    }

    public Order getById(Long id) {
        logger.info("SELECT * FROM `order` where id = " + id);
        return jdbcTemplate.queryForObject("SELECT * FROM `order` where id = ?", (rs, rowNum) ->{
            Order order = new Order();
            order.setOrderDate(rs.getString("orderDate"));
            order.setPrice(rs.getInt("price"));
            order.setId(rs.getLong("id"));
            order.setDeliveryDate(rs.getString("deliveryDate"));
            order.setToDelivery(rs.getString("toDelivery"));
            order.setBuyerId(rs.getLong("buyer"));
            return order;
        },new Object[]{id});
    }

    public List<Order> getAll() {
           logger.info("SELECT * FROM `order`");
           return jdbcTemplate.query("SELECT * FROM `order` ", (rs, rowNum) ->{
            Order order = new Order();
            order.setOrderDate(rs.getString("orderDate"));
            order.setPrice(rs.getInt("price"));
            order.setId(rs.getLong("id"));
            order.setDeliveryDate(rs.getString("deliveryDate"));
            order.setToDelivery(rs.getString("toDelivery"));
            return order;
        });
    }

    public List<Order> getByUser(Long id){
        logger.info("SELECT * FROM `order` WHERE `order`.buyer = " + id);
        return jdbcTemplate.query("SELECT * FROM `order` WHERE `order`.buyer = ?", (rs, rowNum) ->{
            Order order = new Order();
            order.setOrderDate(rs.getString("orderDate"));
            order.setPrice(rs.getInt("price"));
            order.setId(rs.getLong("id"));
            order.setDeliveryDate(rs.getString("deliveryDate"));
            order.setToDelivery(rs.getString("toDelivery"));
            return order;
        },new Object[]{id});
    }

    public Order getByUserIncomplete(String mail){
        try {
            logger.info("SELECT * FROM `order` WHERE `order`.buyer = " + mail + " AND `order`.complete = 0");
            return jdbcTemplate.queryForObject("SELECT * FROM `order` WHERE `order`.buyer = ? AND `order`.complete = 0", (rs, rowNum) -> {
                Order order = new Order();
                order.setOrderDate(rs.getString("orderDate"));
                order.setPrice(rs.getInt("price"));
                order.setId(rs.getLong("id"));
                order.setDeliveryDate(rs.getString("deliveryDate"));
                order.setToDelivery(rs.getString("toDelivery"));
                order.setComplete(rs.getBoolean("complete"));
                return order;
            }, new Object[]{mail});
        }catch ( EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Order> getByUserComplete(String mail){
        try {
            logger.info("SELECT * FROM `order` WHERE `order`.buyer = " + mail + " AND `order`.complete = 1");
            return jdbcTemplate.query("SELECT * FROM `order` WHERE `order`.buyer = ? AND `order`.complete = 1", (rs, rowNum) -> {
                Order order = new Order();
                order.setOrderDate(rs.getString("orderDate"));
                order.setPrice(rs.getInt("price"));
                order.setId(rs.getLong("id"));
                order.setDeliveryDate(rs.getString("deliveryDate"));
                order.setToDelivery(rs.getString("toDelivery"));
                order.setComplete(rs.getBoolean("complete"));
                return order;
            }, new Object[]{mail});
        }catch ( EmptyResultDataAccessException e){
            return null;
        }
    }

}
