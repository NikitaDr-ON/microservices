package com.store.constructor.controllers;


import com.store.constructor.models.Buyer;
import com.store.constructor.models.Product;
import com.store.constructor.repositories.ProductImpl;
import com.store.constructor.services.ConstructorService;
import com.store.constructor.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductImpl productImpl;
    @Autowired
    ConstructorService constructorService;

    @GetMapping("/constructor/products")
    public String getAll(Principal principal, Model model){
        List<Product> product = productImpl.getAll();
        model.addAttribute("products", product);
        return "products";
    }
    @GetMapping("/constructor/products/{id}")
    public String getCurrentProduct(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/constructor/products/gpu")
    public String getGpu(Model model,Principal principal){
            List<Product> product = constructorService.getGpu(principal.getName());
            model.addAttribute("products", product);
            return "gpu";
    }
    @GetMapping("/constructor/products/gpu/{id}")
    public String getCurrentGpu(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }
    @PostMapping("/constructor/products/getRequest")
    public String addGpu(@RequestParam long id, Principal principal, Model model){
        Buyer buyer = new Buyer();
        buyer.setMail(principal.getName());
        Product product = productService.getProductById(id);
        constructorService.addProductToOrder(product,buyer);
        return "redirect:http://localhost:8083/constructor";
    }



    @GetMapping("/constructor/products/cpu")
    public String getCpu(Model model, Principal principal){
        List<Product> product = constructorService.getCpu(principal.getName());
        model.addAttribute("products", product);
        return "cpu";
    }
    @GetMapping("/constructor/products/cpu/{id}")
    public String getCurrentCpu(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/constructor/products/ram")
    public String getRam(Model model, Principal principal){
        List<Product> product = constructorService.getRam(principal.getName());
        model.addAttribute("products", product);
        return "ram";
    }
    @GetMapping("/constructor/products/ram/{id}")
    public String getCurrentRam(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/constructor/products/cooler")
    public String getCooler(Model model){
        List<Product> product = productService.getProductByCategory("cooler");
        model.addAttribute("products", product);
        return "cooler";
    }
    @GetMapping("/constructor/products/cooler/{id}")
    public String getCurrentCooler(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/constructor/products/power")
    public String getPower(Model model, Principal principal){
        List<Product> product = constructorService.getPsu(principal.getName());
        model.addAttribute("products", product);
        return "power";
    }
    @GetMapping("/constructor/products/power/{id}")
    public String getCurrentPower(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }



    @GetMapping("/constructor/products/body")
    public String getBody(Model model){
        List<Product> product = productService.getProductByCategory("body");
        model.addAttribute("products", product);
        return "body";
    }
    @GetMapping("/constructor/products/body/{id}")
    public String getCurrentBody(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/constructor/products/ssd")
    public String getSsd(Model model){
        List<Product> product = productService.getProductByCategory("ssd");
        model.addAttribute("products", product);
        return "ssd";
    }
    @GetMapping("/constructor/products/ssd/{id}")
    public String getCurrentSsd(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "current";
    }


    @GetMapping("/constructor/products/motherboard")
    public String getMotherboard(Model model,Principal principal){
        System.out.println(principal.getName());
        List<Product> product = constructorService.getMotherboards(principal.getName());
        model.addAttribute("products", product);
        return "products";
    }
    @GetMapping("/constructor/products/motherboard/{id}")
    public String getCurrentMotherboard(@PathVariable("id") long id,Model model){
        Product product = productService.getProductById((long) id);
        model.addAttribute("products", product);
        return "motherboard";
    }


}
