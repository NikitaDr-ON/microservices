package com.client.storeClient.controller;

import com.client.storeClient.service.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddController {

    @Autowired
    AddProductService addProductService;
    @GetMapping("store/addCpu")
    public String ShowFormCpu(Model model){
        return "addCpu";
    }
    @PostMapping("store/addCpu")
    public String addCpu(@RequestParam String dealer,@RequestParam String modelName,@RequestParam String socket,@RequestParam String countOfCore,
                         @RequestParam String ghz,@RequestParam String l2cash,@RequestParam String l3cash, @RequestParam int price, Model model){
        addProductService.addCpu(dealer,modelName ,socket,countOfCore ,ghz ,l2cash ,l3cash,price);
        return "addCpu";
    }

    @GetMapping("/addGpu")
    public String ShowFormGpu(Model model){
        return "addGpu";
    }
    @PostMapping("/addGpu")
    public String addGpu(@RequestParam String dealer,@RequestParam String modelName,@RequestParam String videoMemoryCapacity,@RequestParam String memoryType,
                         @RequestParam String typeAndNumberOfFans,@RequestParam String gpu,@RequestParam int price,@RequestParam String recommendedPowerSupply, Model model){
        addProductService.addGpu(dealer,modelName ,videoMemoryCapacity,memoryType ,typeAndNumberOfFans ,gpu ,price, recommendedPowerSupply);
        return "addGpu";
    }

    @GetMapping("/addRam")
    public String ShowFormRam(Model model){
        return "addRam";
    }
    @PostMapping("/addRam")
    public String addRam(@RequestParam String dealer,@RequestParam String modelName,@RequestParam String memoryType,@RequestParam String numberOfModules,
                         @RequestParam String clockFrequency,@RequestParam String volumeOfOneMemoryModule,@RequestParam int price, Model model){
        addProductService.addRam(dealer,modelName ,memoryType,numberOfModules ,clockFrequency ,volumeOfOneMemoryModule ,price);
        return "addRam";
    }

    @GetMapping("/addCooler")
    public String ShowFormCooler(Model model){
        return "addCooler";
    }
    @PostMapping("/addCooler")
    public String addCooler(@RequestParam String dealer,@RequestParam String modelName,@RequestParam String BaseMaterial,@RequestParam String socket,
                            @RequestParam String powerDissipation,@RequestParam String numberOfHeatPipes,@RequestParam int price, Model model){
        addProductService.addCooler(dealer,modelName ,BaseMaterial,socket ,powerDissipation ,numberOfHeatPipes ,price);
        return "addCooler";
    }

    @GetMapping("/addBody")
    public String ShowFormBody(Model model){
        return "addBody";
    }
    @PostMapping("/addBody")
    public String addBody(@RequestParam String dealer,@RequestParam String modelName,@RequestParam String caseSize,@RequestParam String formFactorOfCompatibleBoards,
                          @RequestParam String powerSupplyPlacement,@RequestParam int price, Model model){
        addProductService.addBody(dealer,modelName,caseSize,formFactorOfCompatibleBoards,
                powerSupplyPlacement,price);
        return "addBody";
    }

    @GetMapping("/addSsd")
    public String ShowFormSsd(Model model){
        return "addSsd";
    }
    @PostMapping("/addSsd")
    public String addSsd(@RequestParam String dealer,@RequestParam String modelName,@RequestParam String capacity,
                         @RequestParam String maximumDataTransferRate,@RequestParam int price, Model model){
        addProductService.addSsd(dealer,modelName,capacity,maximumDataTransferRate,price);
        return "addSsd";
    }

    @GetMapping("/addPower")
    public String ShowFormPower(Model model){
        return "addPower";
    }
    @PostMapping("/addPower")
    public String addPower(@RequestParam String dealer,@RequestParam String modelName,@RequestParam String formFactor,@RequestParam String power,
                           @RequestParam String mainPowerConnector, int price, Model model){
        addProductService.addPower(dealer,modelName,formFactor, power, mainPowerConnector, price);
        return "addPower";
    }

    @GetMapping("/addMotherboard")
    public String ShowFormMotherboard(Model model){
        return "addMotherboard";
    }
    @PostMapping("/addMotherboard")
    public String addMotherboard(@RequestParam String dealer,@RequestParam String modelName,@RequestParam String formFactor,@RequestParam String socket,
                                 @RequestParam String supportedMemoryType, @RequestParam String numberOfMemorySlots,@RequestParam int price,Model model){
        addProductService.addMotherboard(dealer,modelName,formFactor,socket,
                supportedMemoryType,numberOfMemorySlots,price);
        return "addMotherboard";
    }
}
