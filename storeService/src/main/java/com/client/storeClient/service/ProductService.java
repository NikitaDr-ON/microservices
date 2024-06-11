package com.client.storeClient.service;


import com.client.storeClient.Repository.CategoryImpl;
import com.client.storeClient.Repository.CharacteristicsImpl;
import com.client.storeClient.Repository.ParametersImpl;
import com.client.storeClient.Repository.ProductImpl;
import com.client.storeClient.model.Category;
import com.client.storeClient.model.Characteristics;
import com.client.storeClient.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Product getProductByName(String name){
        Product product = productImpl.findByName(name);
        ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
        product.setCharacteristics(characteristics);
        product.setCategory(parametersImpl.getById(characteristics.get(1).getParameterId()).getCategory());
        return product;
    }
    public Product getProductById(Long id){
        Product product = productImpl.getById(id);
        ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId()); //берем все характеристики одного объекта
        product.setCharacteristics(characteristics);
        product.setCategory(parametersImpl.getById(characteristics.get(1).getParameterId()).getCategory());
        System.out.println(product.toString());
        return product;
    }
    public List<Product> getProductByCategory(String category){
        Category findCategory = categoryImpl.findByName(category);
        List<Product> products = productImpl.findByCategory(category, findCategory.getId());
        for (Product product: products) {
            ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
            product.setCharacteristics(characteristics);
            System.out.println(product.toString());
        }
        return products;
    }
    public List<Product> getAll(){
        List<Product> products = productImpl.getAll();
        for (Product product: products) {
            ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
            product.setCharacteristics(characteristics);
            product.setCategory(parametersImpl.getById(characteristics.get(1).getParameterId()).getCategory());
        }
        return products;
    }
}
