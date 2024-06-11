package com.client.storeClient.Repository;

import com.client.storeClient.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductImpl implements ProductRepository{

    private static final Logger logger = LoggerFactory.getLogger(ProductImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Product someClass) {
        logger.info("insert into product (characteristics,dealer,name,price)" + someClass.getCharacteristicsId() + " " + someClass.getDealerId() + " " +  someClass.getName()+ " " + someClass.getPrice());
        jdbcTemplate.update("insert into product (characteristics,dealer,name,price) values(?,?,?,?)",someClass.getCharacteristicsId(), someClass.getDealerId(),
                someClass.getName(),someClass.getPrice());
    }

    @Override
    public Product getById(Long id) {
        try {
            logger.info("SELECT dealer.name as dealer,dealer.id as dealerId, product.* from product Join dealer on product.dealer=dealer.id where product.id= " + id);
            return jdbcTemplate.queryForObject("SELECT dealer.name as dealer,dealer.id as dealerId, product.* from product Join dealer on product.dealer=dealer.id where product.id=?", (rs, rowNum) -> {
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDealer(rs.getString("dealer"));
                product.setId(rs.getLong("id"));
                product.setDealerId(rs.getLong("dealerId"));
                product.setCharacteristicsId(rs.getLong("characteristics"));
                return product;
            }, new Object[]{id});
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void update(Product someClass) {
        ProductRepository.super.update(someClass);
    }

    @Override
    public List<Product> getAll() {
        logger.info("SELECT dealer.name as dealer, product.* from product Join dealer on product.dealer=dealer.id");
        return jdbcTemplate.query("SELECT dealer.name as dealer, product.* from product Join dealer on product.dealer=dealer.id", (rs, rowNum) ->{
        Product product = new Product();
        product.setName(rs.getString("name"));
        product.setPrice(rs.getInt("price"));
        product.setDealer(rs.getString("dealer"));
        product.setId(rs.getLong("id"));
        product.setCharacteristicsId(rs.getLong("characteristics"));
        return product;
    });
    }

    @Override
    public Product findByName(String name) {
        try{
            logger.info("SELECT dealer.name as dealer, product.* from product Join dealer on product.dealer=dealer.id where product.name= " + name);
            return jdbcTemplate.queryForObject("SELECT dealer.name as dealer, product.* from product Join dealer on product.dealer=dealer.id where product.name=?", (rs, rowNum) ->{
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDealer(rs.getString("dealer"));
                product.setId(rs.getLong("id"));
                product.setCharacteristicsId(rs.getLong("characteristics"));
                return product;
            }, new Object[]{name});
        } catch (EmptyResultDataAccessException e){
                    return null;
                }
    }

    public List<Product> findByCategory(String category, long categoryId){
        logger.info("SELECT DISTINCT product.*, category.id,category.name, dealer.name as dealername from product " +
                "Join dealer on product.dealer=dealer.id " +
                "Join  characteristics On product.characteristics = characteristics.id " +
                "Join parametersofcharacteristics on characteristics.id=parametersofcharacteristics.characteristics " +
                "Join parameters on parametersofcharacteristics.parameters=parameters.id " +
                "Join parametersofcategory ON parameters.id = parametersofcategory.parameter " +
                "Join category on parametersofcategory.category = category.id where category.name= "+ category + " AND category.id  =  " + categoryId +
                " GROUP BY product.name\n" +
                "HAVING COUNT(parametersofcharacteristics.parameters) >= 2");
        return jdbcTemplate.query("SELECT DISTINCT product.*, category.id,category.name, dealer.name as dealername from product " +
                "Join dealer on product.dealer=dealer.id " +
                "Join  characteristics On product.characteristics = characteristics.id " +
                "Join parametersofcharacteristics on characteristics.id=parametersofcharacteristics.characteristics " +
                "Join parameters on parametersofcharacteristics.parameters=parameters.id " +
                "Join parametersofcategory ON parameters.id = parametersofcategory.parameter " +
                "Join category on parametersofcategory.category = category.id where category.name=? AND category.id  = ? " +
                "GROUP BY product.name\n" +
                "HAVING COUNT(parametersofcharacteristics.parameters) >= 2", (rs, rowNum) ->{
            Product product = new Product();
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setDealer(rs.getString("dealername"));
            product.setId(rs.getLong("id"));
            product.setCharacteristicsId(rs.getLong("characteristics"));
            product.setCategory(rs.getString("name"));
            return product;
        }, new Object[]{category, categoryId});
    }


}
