package com.store.constructor.services;



import com.store.constructor.models.Category;
import com.store.constructor.models.Characteristics;
import com.store.constructor.models.Order;
import com.store.constructor.models.Product;
import com.store.constructor.repositories.CategoryImpl;
import com.store.constructor.repositories.CharacteristicsImpl;
import com.store.constructor.repositories.ParametersImpl;
import com.store.constructor.repositories.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductImpl productImpl;
    @Autowired
    CharacteristicsImpl characteristicsImpl;
    @Autowired
    CategoryImpl categoryImpl;


    @Autowired
    ParametersImpl parametersImpl;

    public Product getProductByName(String name) {
        Product product = productImpl.findByName(name);
        ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
        product.setCharacteristics(characteristics);
        product.setCategory(parametersImpl.getById(characteristics.get(1).getParameterId()).getCategory());
        return product;
    }

    public Product getProductById(Long id) {
        Product product = productImpl.getById(id);
        ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId()); //берем все характеристики одного объекта
        product.setCharacteristics(characteristics);
        product.setCategory(parametersImpl.getById(characteristics.get(1).getParameterId()).getCategory());
        System.out.println(product.toString());
        return product;
    }

    public List<Product> getProductByCategory(String category) {
        Category findCategory = categoryImpl.findByName(category);
        List<Product> products = productImpl.findByCategory(category, findCategory.getId());
        for (Product product : products) {
            ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
            System.out.println(product.toString());
        }
        return products;
    }
}
