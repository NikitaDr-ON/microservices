package com.client.storeClient.controller;

import com.client.storeClient.Repository.ProductImpl;
import com.client.storeClient.model.Buyer;
import com.client.storeClient.model.Product;
import com.client.storeClient.service.OrderService;
import com.client.storeClient.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductImpl productImpl;
    @Autowired
    OrderService orderService;

    @GetMapping("/store/products")
    public String getAll(Principal principal, Model model){
        System.out.println(principal.getName());
        List<Product> product = productImpl.getAll();
        model.addAttribute("products", product);
        return "products";
    }
    @GetMapping("/store/products/{id}")
    public String getCurrentProduct(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/store/products/gpu")
    public String getGpu(Model model){
            List<Product> product = productService.getProductByCategory("gpu");
            model.addAttribute("products", product);
            return "gpu";
    }
    @GetMapping("/store/products/gpu/{id}")
    public String getCurrentGpu(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }
    @PostMapping("/store/products/getRequest")
    public String addGpu(@RequestParam long id, Principal principal, Model model){
        Buyer buyer = new Buyer();
        buyer.setMail(principal.getName());
        Product product = productService.getProductById(id);
        orderService.addProductToOrder(product,buyer);
        return "redirect:http://localhost:8083/store/order";
    }



    @GetMapping("/store/products/cpu")
    public String getCpu(Model model){
        List<Product> product = productService.getProductByCategory("cpu");
        model.addAttribute("products", product);
        return "cpu";
    }
    @GetMapping("/store/products/cpu/{id}")
    public String getCurrentCpu(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/store/products/ram")
    public String getRam(Model model){
        List<Product> product = productService.getProductByCategory("ram");
        model.addAttribute("products", product);
        return "ram";
    }
    @GetMapping("/store/products/ram/{id}")
    public String getCurrentRam(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/store/products/cooler")
    public String getCooler(Model model){
        List<Product> product = productService.getProductByCategory("cooler");
        model.addAttribute("products", product);
        return "cooler";
    }
    @GetMapping("/store/products/cooler/{id}")
    public String getCurrentCooler(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/store/products/power")
    public String getPower(Model model){
        List<Product> product = productService.getProductByCategory("power");
        model.addAttribute("products", product);
        return "power";
    }
    @GetMapping("/store/products/power/{id}")
    public String getCurrentPower(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }



    @GetMapping("/store/products/body")
    public String getBody(Model model){
        List<Product> product = productService.getProductByCategory("body");
        model.addAttribute("products", product);
        return "body";
    }
    @GetMapping("/store/products/body/{id}")
    public String getCurrentBody(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/store/products/ssd")
    public String getSsd(Model model){
        List<Product> product = productService.getProductByCategory("ssd");
        model.addAttribute("products", product);
        return "ssd";
    }
    @GetMapping("/store/products/ssd/{id}")
    public String getCurrentSsd(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/store/products/motherboard")
    public String getMotherboard(Model model){
        List<Product> product = productService.getProductByCategory("motherboard");
        model.addAttribute("products", product);
        return "motherboard";
    }
    @GetMapping("/store/products/motherboard/{id}")
    public String getCurrentMotherboard(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "motherboard";
    }


}
