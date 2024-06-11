package com.client.storeClient.controller;


import com.client.storeClient.Repository.OrderImpl;
import com.client.storeClient.model.Buyer;
import com.client.storeClient.model.Order;
import com.client.storeClient.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PrivateOfficeController {

    @Autowired
    OrderImpl orderImpl;
    @Autowired
    OrderService orderService;

    @GetMapping("/store/privateOffice")
    public String getPrivateOfficeController(Principal principal, Model model){
        Buyer buyer = new Buyer();
        buyer.setMail(principal.getName());
        List<Order> orders = orderService.getProductsOfCompleteOrder(buyer);
        model.addAttribute("orderList", orders);
        return "privateOffice";
    }
}
