package com.client.storeClient.service;


import com.client.storeClient.Repository.OrderImpl;
import com.client.storeClient.Repository.ProductsOfOrderImpl;
import com.client.storeClient.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderImpl orderImpl;
    @Autowired
    private ProductsOfOrderImpl productsOfOrder;
    @Autowired
    private ProductService productService;

    private void ifNotConsistProductOfCategory(Product product, Order order){
        if(product.getCategory().equals("cpu") && order.getCpu() == null)
            productsOfOrder.addProduct(product, order);
        if(product.getCategory().equals("gpu") && order.getGpu() == null)
            productsOfOrder.addProduct(product, order);
        if(product.getCategory().equals("power") && order.getPsu() == null)
            productsOfOrder.addProduct(product, order);
        if(product.getCategory().equals("ram") && order.getRam() == null)
            productsOfOrder.addProduct(product, order);
        if(product.getCategory().equals("body") && order.getBody() == null)
            productsOfOrder.addProduct(product, order);
        if(product.getCategory().equals("cooler") && order.getCooler() == null)
            productsOfOrder.addProduct(product, order);
        if(product.getCategory().equals("motherboard") && order.getMotherboard() == null)
            productsOfOrder.addProduct(product, order);
        if(product.getCategory().equals("ssd") && order.getSsd() == null){
            productsOfOrder.addProduct(product, order);}
    }
    private void addIfNull(Product product, Order order){
            ifNotConsistProductOfCategory(product,order);

    }

    public void addProductToOrder(Product product, Buyer buyer){
        if(orderImpl.getByUserIncomplete(buyer.getMail()) != null) {
            Order order = getProductsOfOrder(buyer);
            
           addIfNull(product, order);
        }else {
            orderImpl.create(buyer.getMail());
            Order order = orderImpl.getByUserIncomplete(buyer.getMail());
            addIfNull(product, order);
        }
    }
    public List<Order> getProductsOfCompleteOrder(Buyer buyer){
        List<Order> orders = orderImpl.getByUserComplete(buyer.getMail());
        for (Order order: orders) {
            List<ProductsOfOrder> currentProducts = productsOfOrder.getProductsOfOrder(order.getId());
            for (ProductsOfOrder products : currentProducts) {
                Product currentProduct = productService.getProductById(products.getProductId());
                switch (currentProduct.getCategory()) {
                    case ("ram"):
                        order.setRam(currentProduct);
                        break;
                    case ("cpu"):
                        order.setCpu(currentProduct);
                        break;
                    case ("gpu"):
                        order.setGpu(currentProduct);
                        break;
                    case ("power"):
                        order.setPsu(currentProduct);
                        break;
                    case ("body"):
                        order.setBody(currentProduct);
                        break;
                    case ("motherboard"):
                        order.setMotherboard(currentProduct);
                        break;
                    case ("cooler"):
                        order.setCooler(currentProduct);
                        break;
                    case ("ssd"):
                        order.setSsd(currentProduct);
                        break;
                }
            }
        }
        return orders;
    }
    public Order getProductsOfOrder(Buyer buyer){
        Order order = orderImpl.getByUserIncomplete(buyer.getMail());
        List<ProductsOfOrder> currentProducts = productsOfOrder.getProductsOfOrder(order.getId());
        for (ProductsOfOrder products: currentProducts) {
            Product currentProduct = productService.getProductById(products.getProductId());
            switch (currentProduct.getCategory()){
                case("ram"): order.setRam(currentProduct);
                break;
                case("cpu"): order.setCpu(currentProduct);
                break;
                case("gpu"): order.setGpu(currentProduct);
                    break;
                case("power"): order.setPsu(currentProduct);
                    break;
                case("body"): order.setBody(currentProduct);
                    break;
                case("motherboard"): order.setMotherboard(currentProduct);
                    break;
                case("cooler"): order.setCooler(currentProduct);
                    break;
                case("ssd"): order.setSsd(currentProduct);
                    break;
            }
        }
        return order;
    }

    public void checkCompatibility(String mail, Model model) {
        Buyer buyer = new Buyer(mail);
        System.out.println("checkCompatibility");
        Order order = getProductsOfOrder(buyer);
        String cpuSocket = null;
        String gpuRecommendedPowerSupply = null;
        String coolerSocket = null;
        String caseFormFactorOfCompatibleBoards = null;
        String ramMemoryType = null;
        String ramNumberOfModules = null;
        String psuPower = null;
        String motherboardSupportedMemoryType = null;
        String motherboardSocket = null;
        String motherboardFormFactor = null;
        String motherboardNumberOfMemorySlots = null;
        if (order.getCpu() != null) {
            for (Characteristics characteristics : order.getCpu().getCharacteristics()) {
                if (characteristics.getNameOfParameter().equals("Socket"))
                    cpuSocket = characteristics.getParameter();
            }
        }
        if (order.getGpu() != null) {
            for (Characteristics characteristics : order.getGpu().getCharacteristics()) {
                if (characteristics.getNameOfParameter().equals("RecommendedPowerSupply"))
                    gpuRecommendedPowerSupply = characteristics.getParameter();
            }
        }
        if (order.getMotherboard() != null) {
            for (Characteristics characteristics : order.getMotherboard().getCharacteristics()) {
                if (characteristics.getNameOfParameter().equals("Form Factor"))
                    motherboardFormFactor = characteristics.getParameter();
                if (characteristics.getNameOfParameter().equals("Socket"))
                    motherboardSocket = characteristics.getParameter();
                if (characteristics.getNameOfParameter().equals("Supported Memory Type"))
                    motherboardSupportedMemoryType = characteristics.getParameter();
                if (characteristics.getNameOfParameter().equals("Number Of Memory Slots"))
                    motherboardNumberOfMemorySlots = characteristics.getParameter();
            }
        }
        if (order.getRam() != null) {
            for (Characteristics characteristics : order.getRam().getCharacteristics()) {
                if (characteristics.getNameOfParameter().equals("Memory Type"))
                    ramMemoryType = characteristics.getParameter();
                if (characteristics.getNameOfParameter().equals("Number Of Modules"))
                    ramNumberOfModules = characteristics.getParameter();
            }
        }
        if (order.getPsu() != null) {
            for (Characteristics characteristics : order.getPsu().getCharacteristics()) {
                if (characteristics.getNameOfParameter().equals("power"))
                    psuPower = characteristics.getParameter();
            }
        }
        if (order.getBody() != null) {
            for (Characteristics characteristics : order.getBody().getCharacteristics()) {
                if (characteristics.getNameOfParameter().equals("Form Factor Of Compatible Boards"))
                    caseFormFactorOfCompatibleBoards = characteristics.getParameter();
            }
        }
        if (order.getCooler() != null) {
            for (Characteristics characteristics : order.getCooler().getCharacteristics()) {
                if (characteristics.getNameOfParameter().equals("Socket"))
                    coolerSocket = characteristics.getParameter();
            }
        }
        if(gpuRecommendedPowerSupply != null && psuPower != null && Integer.parseInt(gpuRecommendedPowerSupply) < Integer.parseInt(psuPower)){
            model.addAttribute("gpuAndPsu" , "The power supply does not fit the GPU");
        }
        if(cpuSocket != null && motherboardSocket != null && !cpuSocket.equals(motherboardSocket)){
            model.addAttribute("cpuAndMotherboard" , "The motherboard socket does not fit the cpu");
        }
        if(cpuSocket != null && coolerSocket != null && !cpuSocket.equals(coolerSocket)){
            model.addAttribute("cpuAndCooler" , "The cooler socket does not fit the cpu");
        }
        if(motherboardSupportedMemoryType != null && ramMemoryType != null && !motherboardSupportedMemoryType.equals(ramMemoryType)){
            model.addAttribute("motherboardAndRam" , "The motherboard does not fit the ram memory type");
        }
        if(motherboardNumberOfMemorySlots != null && ramNumberOfModules != null && Integer.parseInt(motherboardNumberOfMemorySlots) < Integer.parseInt(ramNumberOfModules)){
            model.addAttribute("motherboardAndRamModules" , "The motherboard does not fit the count of ram modules");
        }
        if(motherboardFormFactor != null && caseFormFactorOfCompatibleBoards!= null && !motherboardFormFactor.equals(caseFormFactorOfCompatibleBoards)){
            model.addAttribute("motherboardAndCase" , "The body does not fit the motherboard form factor");
        }
    }

    public void orderComplete(String mail){
        Buyer buyer = new Buyer(mail);
        int price = 0;
        Order order = getProductsOfOrder(buyer);
        if(order.getCpu() != null)
            price+=order.getCpu().getPrice();
        if(order.getGpu() != null)
            price+=order.getGpu().getPrice();
        if(order.getMotherboard() != null)
            price+=order.getMotherboard().getPrice();
        if(order.getPsu() != null)
            price+=order.getPsu().getPrice();
        if(order.getRam() != null)
            price+=order.getRam().getPrice();
        if(order.getBody() != null)
            price+=order.getBody().getPrice();
        if(order.getCooler() != null)
            price+=order.getCooler().getPrice();
        if(order.getSsd() != null)
            price+=order.getSsd().getPrice();
        orderImpl.update(orderImpl.getByUserIncomplete(buyer.getMail()).getId(), price);
    }

}


