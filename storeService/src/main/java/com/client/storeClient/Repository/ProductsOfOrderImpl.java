package com.client.storeClient.Repository;

import com.client.storeClient.model.Order;
import com.client.storeClient.model.Product;
import com.client.storeClient.model.ProductsOfOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsOfOrderImpl {

    private static final Logger logger = LoggerFactory.getLogger(ProductsOfOrderImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addProduct(Product product, Order order) {
        logger.info("insert into productsOfOrder " + product.getId() + " " +order.getId() + " " + product.getPrice());
        jdbcTemplate.update("insert into productsOfOrder values(?,?,?)", product.getId(),order.getId(), product.getPrice());
    }
    public List<ProductsOfOrder> getProductsOfOrder(Long id){
        logger.info("SELECT * from productsOfOrder  where productsOfOrder.order = " + id);
        return jdbcTemplate.query("SELECT * from productsOfOrder  where productsOfOrder.order = ? ", (rs, rowNum) ->{
            ProductsOfOrder productsOfOrder = new ProductsOfOrder();
            productsOfOrder.setOrderId(rs.getLong("order"));
            productsOfOrder.setProductId(rs.getInt("product"));
            return productsOfOrder;
        }, new Object[]{id});
    }

    public ProductsOfOrder getProduct(long id){
        try {
            logger.info("SELECT * from productsOfOrder  where productsOfOrder.product = " + id);
            return jdbcTemplate.queryForObject("SELECT * from productsOfOrder  where productsOfOrder.product = ? ", (rs, rowNum) -> {
                ProductsOfOrder productsOfOrder = new ProductsOfOrder();
                productsOfOrder.setOrderId(rs.getLong("order"));
                productsOfOrder.setProductId(rs.getInt("product"));
                return productsOfOrder;
            }, new Object[]{id});
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void deleteFromOrder(long product, long order){
        logger.info("delete from productsOfOrder where productsOfOrder.product = "+ product +" and productsoforder.`order` = " + order);
        jdbcTemplate.update("delete from productsOfOrder where productsOfOrder.product = ? and productsoforder.`order` = ?",product,order);
    }
}
