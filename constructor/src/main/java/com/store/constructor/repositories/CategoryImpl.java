package com.store.constructor.repositories;

import com.store.constructor.models.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryImpl implements CategoryRepository{

    private static final Logger logger = LoggerFactory.getLogger(CategoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Category category) {
        jdbcTemplate.update("insert into Category values(?)", category.getName());
        logger.info("Insert into Category " + category.getName());
    }

    @Override
    public Category getById(Long id) {
        logger.info("Select * from Category where id = " + id);
        return jdbcTemplate.queryForObject("Select * from Category where id = ?", (rs, rowNum) ->{
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            return category;
        },new Object[] {id});
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public List<Category> getAll() {
        logger.info("select * from Category");
        return jdbcTemplate.query("Select * from Category", (rs, rowNum) ->{
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            return category;
        });
    }

    @Override
    public Category findByName(String name) {
        logger.info("Select * from Category where name = " + name);
        return jdbcTemplate.queryForObject("Select * from Category where name = ?", (rs, rowNum) ->{
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            return category;
        },new Object[] {name});
    }
}
