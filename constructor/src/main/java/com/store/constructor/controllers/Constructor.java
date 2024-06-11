package com.store.constructor.controllers;

import com.store.constructor.models.Buyer;
import com.store.constructor.models.Order;
import com.store.constructor.repositories.OrderImpl;
import com.store.constructor.repositories.ProductsOfOrderImpl;
import com.store.constructor.services.ConstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class Constructor {

    @Autowired
    OrderImpl orderImpl;
    @Autowired
    ConstructorService constructorService;
    @Autowired
    ProductsOfOrderImpl productsOfOrder;

    @GetMapping("/constructor")
    public String getConstructor(Principal principal, Model model){
        if(orderImpl.getByUserIncomplete(principal.getName()) == null)
            orderImpl.create(principal.getName());
        Order order = constructorService.getProductsOfOrder(principal.getName());
        model.addAttribute("order", order);
        return "constructor";
    }
    @PostMapping("/constructor/deleteFromOrder")
    public String deleteProductFromOrder(@RequestParam Long product, @RequestParam Long order){
        productsOfOrder.deleteFromOrder(product,order);
        return "redirect:http://localhost:8083/constructor";
    }
}
