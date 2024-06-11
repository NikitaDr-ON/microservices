package com.client.storeClient.controller;

import com.client.storeClient.Repository.DealerImpl;
import com.client.storeClient.Repository.OrderImpl;
import com.client.storeClient.Repository.ParametersImpl;
import com.client.storeClient.Repository.ProductImpl;
import com.client.storeClient.model.Order;
import com.client.storeClient.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DealerController {
        @Autowired
        private DealerImpl dealerImpl;
        @Autowired
        private ParametersImpl parametersImpl;
        @Autowired
        private ProductImpl productImpl;
        @Autowired
        private ProductService productService;
        @Autowired
        private OrderImpl orderImpl;


        @GetMapping("/store/dealer")
        public String dealer(Model model) {
            return "dealer";
        }
        @GetMapping("/")
        public String home(Model model) {
            model.addAttribute("name");
            return "/home";
        }

}
