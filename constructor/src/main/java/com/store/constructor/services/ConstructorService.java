package com.store.constructor.services;



import com.store.constructor.models.*;
import com.store.constructor.repositories.CharacteristicsImpl;
import com.store.constructor.repositories.OrderImpl;
import com.store.constructor.repositories.ProductsOfOrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ConstructorService {
    @Autowired
    public OrderImpl orderImpl;
    @Autowired
    public ProductsOfOrderImpl productsOfOrder;
    @Autowired
    public ProductService productService;
    @Autowired
    CharacteristicsImpl characteristicsImpl;

    public boolean isOrderEmpty(String mail) {
        Order order = orderImpl.getByUserIncomplete(mail);
        List<ProductsOfOrder> currentProducts = productsOfOrder.getProductsOfOrder(order.getId());
        if (currentProducts.size() > 0)
            return false;
        else
            return true;
    }

    public void ifNotConsistProductOfCategory(Product product, Order order) {
        if (product.getCategory().equals("cpu") && order.getCpu() == null)
            productsOfOrder.addProduct(product, order);
        if (product.getCategory().equals("gpu") && order.getGpu() == null)
            productsOfOrder.addProduct(product, order);
        if (product.getCategory().equals("power") && order.getPsu() == null)
            productsOfOrder.addProduct(product, order);
        if (product.getCategory().equals("ram") && order.getRam() == null)
            productsOfOrder.addProduct(product, order);
        if (product.getCategory().equals("body") && order.getBody() == null)
            productsOfOrder.addProduct(product, order);
        if (product.getCategory().equals("cooler") && order.getCooler() == null)
            productsOfOrder.addProduct(product, order);
        if (product.getCategory().equals("motherboard") && order.getMotherboard() == null)
            productsOfOrder.addProduct(product, order);
        if (product.getCategory().equals("ssd") && order.getSsd() == null) {
            productsOfOrder.addProduct(product, order);
        }
    }

    public void addIfNull(Product product, Order order) {
        ifNotConsistProductOfCategory(product, order);

    }

    public void addProductToOrder(Product product, Buyer buyer) {
        if (orderImpl.getByUserIncomplete(buyer.getMail()) != null) {
            Order order = getProductsOfOrder(buyer.getMail());

            addIfNull(product, order);
        } else {
            orderImpl.create(buyer.getMail());
            Order order = orderImpl.getByUserIncomplete(buyer.getMail());
            addIfNull(product, order);
        }
    }

    public void addIfAllOrdersComplete(String mail) {
        if (orderImpl.getByUserIncomplete(mail) != null) {
            orderImpl.create(mail);
        }
    }

    public List<Order> getProductsOfCompleteOrder(Buyer buyer) {
        List<Order> orders = orderImpl.getByUserComplete(buyer.getMail());
        for (Order order : orders) {
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

    public Order getProductsOfOrder(String mail) {
        Order order = orderImpl.getByUserIncomplete(mail);
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
        return order;
    }

    public void checkCompatibility(String mail, Model model) {
        Order order = getProductsOfOrder(mail);
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
        if (gpuRecommendedPowerSupply != null && psuPower != null && Integer.parseInt(gpuRecommendedPowerSupply) < Integer.parseInt(psuPower)) {
            model.addAttribute("gpuAndPsu", "The power supply does not fit the GPU");
        }
        if (cpuSocket != null && motherboardSocket != null && !cpuSocket.equals(motherboardSocket)) {
            model.addAttribute("cpuAndMotherboard", "The motherboard socket does not fit the cpu");
        }
        if (cpuSocket != null && coolerSocket != null && !cpuSocket.equals(coolerSocket)) {
            model.addAttribute("cpuAndCooler", "The cooler socket does not fit the cpu");
        }
        if (motherboardSupportedMemoryType != null && ramMemoryType != null && !motherboardSupportedMemoryType.equals(ramMemoryType)) {
            model.addAttribute("motherboardAndRam", "The motherboard does not fit the ram memory type");
        }
        if (motherboardNumberOfMemorySlots != null && ramNumberOfModules != null && Integer.parseInt(motherboardNumberOfMemorySlots) < Integer.parseInt(ramNumberOfModules)) {
            model.addAttribute("motherboardAndRamModules", "The motherboard does not fit the count of ram modules");
        }
        if (motherboardFormFactor != null && caseFormFactorOfCompatibleBoards != null && !motherboardFormFactor.equals(caseFormFactorOfCompatibleBoards)) {
            model.addAttribute("motherboardAndCase", "The body does not fit the motherboard form factor");
        }
    }

    public void orderComplete(String mail) {
        int price = 0;
        Order order = getProductsOfOrder(mail);
        if (order.getCpu() != null)
            price += order.getCpu().getPrice();
        if (order.getGpu() != null)
            price += order.getGpu().getPrice();
        if (order.getMotherboard() != null)
            price += order.getMotherboard().getPrice();
        if (order.getPsu() != null)
            price += order.getPsu().getPrice();
        if (order.getRam() != null)
            price += order.getRam().getPrice();
        if (order.getBody() != null)
            price += order.getBody().getPrice();
        if (order.getCooler() != null)
            price += order.getCooler().getPrice();
        if (order.getSsd() != null)
            price += order.getSsd().getPrice();
        orderImpl.update(orderImpl.getByUserIncomplete(mail).getId(), price);
    }


    public List<Product> getMotherboards(String mail) {
        if (isOrderEmpty(mail)) {
            List<Product> products = productService.getProductByCategory("motherboard");
        return products;
    }else {
            List<Product> products = productService.getProductByCategory("motherboard");
            Order order = getProductsOfOrder(mail);
            String cpuSocket = null;
            String caseFormFactorOfCompatibleBoards = null;
            String ramMemoryType = null;
            if (order.getCpu() != null) {
                for (Characteristics characteristics : order.getCpu().getCharacteristics()) {
                    if (characteristics.getNameOfParameter().equals("Socket"))
                        cpuSocket = characteristics.getParameter();
                }
            }
            if (order.getRam() != null) {
                for (Characteristics characteristics : order.getRam().getCharacteristics()) {
                    if (characteristics.getNameOfParameter().equals("Memory Type"))
                        ramMemoryType = characteristics.getParameter();
                }
            }
            if (order.getBody() != null) {
                for (Characteristics characteristics : order.getBody().getCharacteristics()) {
                    if (characteristics.getNameOfParameter().equals("Form Factor Of Compatible Boards"))
                        caseFormFactorOfCompatibleBoards = characteristics.getParameter();
                }
            }
            ArrayList<Product> newList = new ArrayList<>();
            for (Product product : products) {
                ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
                boolean haveNecessaryCaseFormFactorOfCompatibleBoards = false;
                boolean haveNecessaryCpuSocket = false;
                boolean haveNecessaryRamMemoryType = false;
                System.out.println("список продуктов = " + products.size());
                System.out.println(characteristics);
                System.out.println("продукт : " + product);
                if (cpuSocket != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(cpuSocket)){
                            System.out.println("сокет " + cpuSocket);
                            haveNecessaryCpuSocket=true;
                        }

                    }
                }
                if (ramMemoryType != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(ramMemoryType)){
                            System.out.println("тип памяти " + ramMemoryType);
                            haveNecessaryRamMemoryType=true;
                        }
                    }

                }
                if (caseFormFactorOfCompatibleBoards != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(caseFormFactorOfCompatibleBoards)){
                            System.out.println("форм фактор " + caseFormFactorOfCompatibleBoards);
                            haveNecessaryCaseFormFactorOfCompatibleBoards=true;}
                    }
                }
                if(haveNecessaryCaseFormFactorOfCompatibleBoards == true && haveNecessaryRamMemoryType==true && haveNecessaryCpuSocket==true)
                    newList.add(product);
                if(haveNecessaryCaseFormFactorOfCompatibleBoards == true && haveNecessaryRamMemoryType==true && cpuSocket == null)
                    newList.add(product);
                if(haveNecessaryCaseFormFactorOfCompatibleBoards == true && ramMemoryType==null && haveNecessaryCpuSocket==true)
                    newList.add(product);
                if(haveNecessaryCaseFormFactorOfCompatibleBoards == true && ramMemoryType == null && cpuSocket == null)
                    newList.add(product);
                if(caseFormFactorOfCompatibleBoards == null  && haveNecessaryRamMemoryType==true && haveNecessaryCpuSocket==true)
                    newList.add(product);
                if(caseFormFactorOfCompatibleBoards == null  && haveNecessaryRamMemoryType==true && cpuSocket == null)
                    newList.add(product);
                if(caseFormFactorOfCompatibleBoards == null  && ramMemoryType == null && haveNecessaryCpuSocket==true)
                    newList.add(product);
                if(caseFormFactorOfCompatibleBoards == null  && ramMemoryType == null && cpuSocket == null)
                    newList.add(product);
            }
            return newList;
    }
}

    public List<Product> getGpu(String mail) {
        if (isOrderEmpty(mail)) {
            List<Product> products = productService.getProductByCategory("gpu");
            return products;
        }else {
            List<Product> products = productService.getProductByCategory("gpu");
            Order order = getProductsOfOrder(mail);
            String psuPower = null;

            if (order.getPsu() != null) {
                for (Characteristics characteristics : order.getPsu().getCharacteristics()) {
                    if (characteristics.getNameOfParameter().equals("power"))
                        psuPower = characteristics.getParameter();
                }
            }
            boolean haveNecessaryPsuPower = false;
            ArrayList<Product> newList = new ArrayList<>();
            for (Product product : products) {
                ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
                System.out.println("список продуктов = " + products.size());
                System.out.println(characteristics);
                System.out.println("продукт : " + product);
                if (psuPower != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(psuPower)){
                            System.out.println("мощность psu  " + psuPower);
                            haveNecessaryPsuPower=true;
                        }
                    }

                }
                else{
                    return productService.getProductByCategory("gpu");
                }
                if(haveNecessaryPsuPower == true)
                    newList.add(product);
            }
            return newList;
        }
    }

    public List<Product> getRam(String mail) {
        if (isOrderEmpty(mail)) {
            List<Product> products = productService.getProductByCategory("ram");
            return products;
        }else {
            List<Product> products = productService.getProductByCategory("ram");
            Order order = getProductsOfOrder(mail);
            String motherboardSupportedMemoryType = null;
            String motherboardNumberOfMemorySlots = null;
            if (order.getMotherboard() != null) {
                for (Characteristics characteristics : order.getMotherboard().getCharacteristics()) {

                    if (characteristics.getNameOfParameter().equals("Supported Memory Type"))
                        motherboardSupportedMemoryType = characteristics.getParameter();
                    if (characteristics.getNameOfParameter().equals("Number Of Memory Slots"))
                        motherboardNumberOfMemorySlots = characteristics.getParameter();
                }
            }

            boolean haveNecessaryMotherboardSupportedMemoryType = false;
            boolean haveNecessaryMotherboardNumberOfMemorySlots = false;
            ArrayList<Product> newList = new ArrayList<>();
            for (Product product : products) {
                ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
                System.out.println("список продуктов = " + products.size());
                System.out.println(characteristics);
                System.out.println("продукт : " + product);
                if (motherboardSupportedMemoryType != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(motherboardSupportedMemoryType)){
                            System.out.println("тип памяти для материнки  " + motherboardSupportedMemoryType);
                            haveNecessaryMotherboardSupportedMemoryType=true;
                        }
                    }

                }

                if (motherboardNumberOfMemorySlots != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(motherboardNumberOfMemorySlots)){
                            System.out.println("количество слотов под память  " + motherboardNumberOfMemorySlots);
                            haveNecessaryMotherboardNumberOfMemorySlots=true;
                        }
                    }

                }

                if(haveNecessaryMotherboardSupportedMemoryType == true && haveNecessaryMotherboardNumberOfMemorySlots == true)
                    newList.add(product);
                if(haveNecessaryMotherboardSupportedMemoryType == true && motherboardNumberOfMemorySlots == null)
                    newList.add(product);
                if(motherboardSupportedMemoryType == null && haveNecessaryMotherboardNumberOfMemorySlots == true)
                    newList.add(product);
                if(motherboardSupportedMemoryType == null && motherboardNumberOfMemorySlots == null)
                    newList.add(product);
            }
            return newList;
        }
    }

    public List<Product> getCpu(String mail) {
        if (isOrderEmpty(mail)) {
            List<Product> products = productService.getProductByCategory("cpu");
            return products;
        }else {
            List<Product> products = productService.getProductByCategory("cpu");
            Order order = getProductsOfOrder(mail);
            String coolerSocket = null;
            String motherboardSocket = null;
            if (order.getMotherboard() != null) {
                for (Characteristics characteristics : order.getMotherboard().getCharacteristics()) {
                    if (characteristics.getNameOfParameter().equals("Socket"))
                        motherboardSocket = characteristics.getParameter();
                }
            }
            if (order.getCooler() != null) {
                for (Characteristics characteristics : order.getCooler().getCharacteristics()) {
                    if (characteristics.getNameOfParameter().equals("Socket"))
                        coolerSocket = characteristics.getParameter();
                }
            }

            boolean haveNecessaryCoolerSocket = false;
            boolean haveNecessaryMotherboardSocket = false;
            ArrayList<Product> newList = new ArrayList<>();
            for (Product product : products) {
                ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
                System.out.println("список продуктов = " + products.size());
                System.out.println(characteristics);
                System.out.println("продукт : " + product);
                if (coolerSocket != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(coolerSocket)){
                            System.out.println("кулер под cpu " + coolerSocket);
                            haveNecessaryCoolerSocket=true;
                        }
                    }

                }

                if (coolerSocket != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(motherboardSocket)){
                            System.out.println("материнка под cpu " + motherboardSocket);
                            haveNecessaryMotherboardSocket=true;
                        }
                    }

                }


                if(haveNecessaryCoolerSocket == true && haveNecessaryMotherboardSocket == true)
                    newList.add(product);
                if(haveNecessaryCoolerSocket == true && motherboardSocket == null)
                    newList.add(product);
                if(coolerSocket == null && haveNecessaryMotherboardSocket == true)
                    newList.add(product);
                if(coolerSocket == null  && motherboardSocket == null)
                    newList.add(product);
            }
            return newList;
        }
    }

    public List<Product> getPsu(String mail) {
        if (isOrderEmpty(mail)) {
            List<Product> products = productService.getProductByCategory("power");
            return products;
        }else {
            List<Product> products = productService.getProductByCategory("power");;
            String gpuRecommendedPowerSupply = null;
            Order order = getProductsOfOrder(mail);
            if (order.getGpu() != null) {
                for (Characteristics characteristics : order.getGpu().getCharacteristics()) {
                    if (characteristics.getNameOfParameter().equals("RecommendedPowerSupply"))
                        gpuRecommendedPowerSupply = characteristics.getParameter();
                }
            }

            boolean haveNecessaryGpuRecommendedPowerSupply = false;
            ArrayList<Product> newList = new ArrayList<>();
            for (Product product : products) {
                ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
                System.out.println("список продуктов = " + products.size());
                System.out.println(characteristics);
                System.out.println("продукт : " + product);
                if (gpuRecommendedPowerSupply != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(gpuRecommendedPowerSupply)){
                            System.out.println("бп под gpu " + gpuRecommendedPowerSupply);
                            haveNecessaryGpuRecommendedPowerSupply=true;
                        }
                    }

                }


                if(gpuRecommendedPowerSupply == null)
                    newList.add(product);
                if(haveNecessaryGpuRecommendedPowerSupply == true)
                    newList.add(product);
            }
            return newList;
        }
    }

    public List<Product> getBody(String mail) {
        if (isOrderEmpty(mail)) {
            List<Product> products = productService.getProductByCategory("body");
            return products;
        }else {
            List<Product> products = productService.getProductByCategory("body");;
            String motherboardFormFactor = null;
            Order order = getProductsOfOrder(mail);

            if (order.getMotherboard() != null) {
                for (Characteristics characteristics : order.getMotherboard().getCharacteristics()) {
                    if (characteristics.getNameOfParameter().equals("Form Factor"))
                        motherboardFormFactor = characteristics.getParameter();
                }
            }

            boolean haveNecessaryMotherboardFormFactor = false;
            ArrayList<Product> newList = new ArrayList<>();
            for (Product product : products) {
                ArrayList<Characteristics> characteristics = (ArrayList<Characteristics>) characteristicsImpl.getById(product.getCharacteristicsId());
                if (motherboardFormFactor != null) {
                    for (int j = 0; j < characteristics.size(); j++) {
                        if (characteristics.get(j).getParameter().equals(motherboardFormFactor)) {
                            haveNecessaryMotherboardFormFactor = true;
                        }
                    }

                }

                if (haveNecessaryMotherboardFormFactor == true)
                    newList.add(product);
                if (motherboardFormFactor == null)
                    newList.add(product);
            }
            return newList;
            }
    }


}


