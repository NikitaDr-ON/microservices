package com.client.storeClient.controller;


import com.client.storeClient.Repository.OrderImpl;
import com.client.storeClient.Repository.ProductsOfOrderImpl;
import com.client.storeClient.model.Buyer;
import com.client.storeClient.model.Order;
import com.client.storeClient.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class OrderController {

    @Autowired
    OrderImpl orderImpl;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductsOfOrderImpl productsOfOrder;

    @GetMapping("/store/order")
    public String showOrder(Principal principal, Model model){
        Buyer buyer = new Buyer(principal.getName());
        if(orderImpl.getByUserIncomplete(principal.getName()) == null)
            orderImpl.create(principal.getName());
        Order order = orderService.getProductsOfOrder(buyer);
        orderService.checkCompatibility(principal.getName(), model);
        model.addAttribute("order", order);
        return "order";
    }

    @PostMapping("/store/orderComplete")
    public String orderComplete(Principal principal){
        orderService.orderComplete(principal.getName());
        return "redirect:http://localhost:8083/store/order";
    }
    @PostMapping("/store/deleteFromOrder")
    public String deleteProductFromOrder(@RequestParam Long product, @RequestParam Long order){
        productsOfOrder.deleteFromOrder(product,order);
        return "redirect:http://localhost:8083/store/order";
    }
}
