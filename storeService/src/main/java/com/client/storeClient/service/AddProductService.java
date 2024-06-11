package com.client.storeClient.service;


import com.client.storeClient.Repository.*;
import com.client.storeClient.model.Characteristics;
import com.client.storeClient.model.Dealer;
import com.client.storeClient.model.Parameters;
import com.client.storeClient.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddProductService {
    @Autowired
    ProductImpl productImpl;
    @Autowired
    DealerImpl dealerImpl;
    @Autowired
    CharacteristicsImpl characteristicsImpl;
    @Autowired
    ParametersImpl parametersImpl;
    @Autowired
    ParametersOfCategoryImpl parametersOfCategoryImpl;
    @Autowired
    ParametersOfCharacteristicsImpl parametersOfCharacteristicsImpl;

    public void addCpu(String dealer, String modelName, String socket, String countOfCore,String Ghz, String l2cash, String l3cash, int price){

        if(productImpl.findByName(modelName) == null) {

            if (dealerImpl.findByName(dealer) == null) {
                Dealer newDealer = new Dealer(dealer);
                dealerImpl.create(newDealer);
            }
            Dealer cpuDealer = dealerImpl.findByName(dealer);
            Characteristics characteristics = characteristicsImpl.getCount(); //получение кол-во характеристик
            characteristicsImpl.create(characteristics); // создание новой характеристики, туда кладется id = кол-во характеристик + 1
            characteristics = characteristicsImpl.getCount();
            System.out.println(characteristics.getId());

            if (parametersImpl.findByParameter(socket) == null) {
                Parameters cpuSocket = new Parameters(socket, "Socket");
                parametersImpl.create(cpuSocket);
            }
            Parameters cpuSocket = parametersImpl.findByParameter(socket);//получение параметра


            if (parametersImpl.findByParameter(countOfCore) == null) {
                Parameters cpuCountOfCore = new Parameters(countOfCore, "Count of Core");
                parametersImpl.create(cpuCountOfCore);
            }
            Parameters cpuCountOfCore = parametersImpl.findByParameter(countOfCore);//получение параметра


            if (parametersImpl.findByParameter(Ghz) == null) {
                Parameters cpuGhz = new Parameters(Ghz, "Ghz");
                parametersImpl.create(cpuGhz);
            }
            Parameters cpuGhz = parametersImpl.findByParameter(Ghz);//получение параметра

            if (parametersImpl.findByParameter(l2cash) == null) {
                Parameters cpuL2cash = new Parameters(l2cash, "L2 Cash");
                parametersImpl.create(cpuL2cash);
            }
            Parameters cpuL2cash = parametersImpl.findByParameter(l2cash);//получение параметра

            if (parametersImpl.findByParameter(l3cash) == null) {
                Parameters cpuL3cash = new Parameters(l3cash, "L3 Cash");
                parametersImpl.create(cpuL3cash);
            }
            Parameters cpuL3cash = parametersImpl.findByParameter(l3cash);//получение параметра

            if (parametersOfCategoryImpl.check(cpuCountOfCore.getId()) == null) {
                parametersOfCategoryImpl.createCpu(cpuCountOfCore);
            }
            if (parametersOfCategoryImpl.check(cpuGhz.getId()) == null) {
                parametersOfCategoryImpl.createCpu(cpuGhz);
            }
            if (parametersOfCategoryImpl.check(cpuL2cash.getId()) == null) {
                parametersOfCategoryImpl.createCpu(cpuL2cash);
            }
            if (parametersOfCategoryImpl.check(cpuL3cash.getId()) == null) {
                parametersOfCategoryImpl.createCpu(cpuL3cash);
            }
            if (parametersOfCategoryImpl.check(cpuSocket.getId()) == null) {
                parametersOfCategoryImpl.createCpu(cpuSocket);
            }

            parametersOfCharacteristicsImpl.create(cpuSocket, characteristics);
            parametersOfCharacteristicsImpl.create(cpuCountOfCore, characteristics);
            parametersOfCharacteristicsImpl.create(cpuGhz, characteristics);
            parametersOfCharacteristicsImpl.create(cpuL2cash, characteristics);
            parametersOfCharacteristicsImpl.create(cpuL3cash, characteristics);

            Product product = new Product(modelName, price);
            product.setCharacteristicsId(characteristics.getId());
            product.setDealerId(cpuDealer.getId());
            productImpl.create(product);
        }

    }

    public void addGpu(String dealer,String modelName,String videoMemoryCapacity,String memoryType,
                       String TypeAndNumberOfFans,String gpu,int price,String recommendedPowerSupply){

        if(productImpl.findByName(modelName) == null) {

            if (dealerImpl.findByName(dealer) == null) {
                Dealer newDealer = new Dealer(dealer);
                dealerImpl.create(newDealer);
            }
            Dealer cpuDealer = dealerImpl.findByName(dealer);
            Characteristics characteristics = characteristicsImpl.getCount(); //получение кол-во характеристик
            characteristicsImpl.create(characteristics); // создание новой характеристики, туда кладется id = кол-во характеристик + 1
            characteristics = characteristicsImpl.getCount();
            System.out.println(characteristics.getId());

            if (parametersImpl.findByParameter(videoMemoryCapacity) == null) {
                Parameters gpuVideoMemoryCapacity = new Parameters(videoMemoryCapacity, "Video Memory Capacity");
                parametersImpl.create(gpuVideoMemoryCapacity);
            }
            Parameters gpuVideoMemoryCapacity = parametersImpl.findByParameter(videoMemoryCapacity);//получение параметра


            if (parametersImpl.findByParameter(memoryType) == null) {
                Parameters gpuMemoryType = new Parameters(memoryType, "Memory Type");
                parametersImpl.create(gpuMemoryType);
            }
            Parameters gpuMemoryType = parametersImpl.findByParameter(memoryType);//получение параметра


            if (parametersImpl.findByParameter(TypeAndNumberOfFans) == null) {
                Parameters gpuTypeAndNumberOfFans = new Parameters(TypeAndNumberOfFans, "Type And Number Of Fans");
                parametersImpl.create(gpuTypeAndNumberOfFans);
            }
            Parameters gpuTypeAndNumberOfFans = parametersImpl.findByParameter(TypeAndNumberOfFans);//получение параметра

            if (parametersImpl.findByParameter(gpu) == null) {
                Parameters newGpu = new Parameters(gpu, "Gpu");
                parametersImpl.create(newGpu);
            }
            Parameters newGpu = parametersImpl.findByParameter(gpu);//получение параметра

            if (parametersImpl.findByParameter(recommendedPowerSupply) == null) {
                Parameters gpuRecommendedPowerSupply = new Parameters(recommendedPowerSupply, "RecommendedPowerSupply");
                parametersImpl.create(gpuRecommendedPowerSupply);
            }
            Parameters gpuRecommendedPowerSupply = parametersImpl.findByParameter(recommendedPowerSupply);//получение параметра

            if (parametersOfCategoryImpl.check(gpuVideoMemoryCapacity.getId()) == null) {
                parametersOfCategoryImpl.createGpu(gpuVideoMemoryCapacity);
            }
            if (parametersOfCategoryImpl.check(gpuMemoryType.getId()) == null) {
                parametersOfCategoryImpl.createGpu(gpuMemoryType);
            }
            if (parametersOfCategoryImpl.check(gpuTypeAndNumberOfFans.getId()) == null) {
                parametersOfCategoryImpl.createGpu(gpuTypeAndNumberOfFans);
            }
            if (parametersOfCategoryImpl.check(newGpu.getId()) == null) {
                parametersOfCategoryImpl.createGpu(newGpu);
            }
            if (parametersOfCategoryImpl.check(gpuRecommendedPowerSupply.getId()) == null) {
                parametersOfCategoryImpl.createGpu(gpuRecommendedPowerSupply);
            }

            parametersOfCharacteristicsImpl.create(gpuVideoMemoryCapacity, characteristics);
            parametersOfCharacteristicsImpl.create(gpuMemoryType, characteristics);
            parametersOfCharacteristicsImpl.create(gpuTypeAndNumberOfFans, characteristics);
            parametersOfCharacteristicsImpl.create(newGpu, characteristics);
            parametersOfCharacteristicsImpl.create(gpuRecommendedPowerSupply, characteristics);

            Product product = new Product(modelName, price);
            product.setCharacteristicsId(characteristics.getId());
            product.setDealerId(cpuDealer.getId());
            productImpl.create(product);
        }

    }

    public void addRam(String dealer,String modelName,String MemoryType,String NumberOfModules,
                       String ClockFrequency,String VolumeOfOneMemoryModule,int price){

        if(productImpl.findByName(modelName) == null) {

            if (dealerImpl.findByName(dealer) == null) {
                Dealer newDealer = new Dealer(dealer);
                dealerImpl.create(newDealer);
            }
            Dealer cpuDealer = dealerImpl.findByName(dealer);
            Characteristics characteristics = characteristicsImpl.getCount(); //получение кол-во характеристик
            characteristicsImpl.create(characteristics); // создание новой характеристики, туда кладется id = кол-во характеристик + 1
            characteristics = characteristicsImpl.getCount();

            if (parametersImpl.findByParameter(MemoryType) == null) {
                Parameters ramMemoryType = new Parameters(MemoryType, "Memory Type");
                parametersImpl.create(ramMemoryType);
            }
            Parameters ramMemoryType = parametersImpl.findByParameter(MemoryType);//получение параметра


            if (parametersImpl.findByParameter(NumberOfModules) == null) {
                Parameters ramNumberOfModules = new Parameters(NumberOfModules, "Number Of Modules");
                parametersImpl.create(ramNumberOfModules);
            }
            Parameters ramNumberOfModules = parametersImpl.findByParameter(NumberOfModules);//получение параметра


            if (parametersImpl.findByParameter(ClockFrequency) == null) {
                Parameters ramClockFrequency = new Parameters(ClockFrequency, "Clock Frequency");
                parametersImpl.create(ramClockFrequency);
            }
            Parameters ramClockFrequency = parametersImpl.findByParameter(ClockFrequency);//получение параметра

            if (parametersImpl.findByParameter(VolumeOfOneMemoryModule) == null) {
                Parameters ramVolumeOfOneMemoryModule = new Parameters(VolumeOfOneMemoryModule, "Volume Of One Memory Module");
                parametersImpl.create(ramVolumeOfOneMemoryModule);
            }
            Parameters ramVolumeOfOneMemoryModule = parametersImpl.findByParameter(VolumeOfOneMemoryModule);//получение параметра


            if (parametersOfCategoryImpl.check(ramMemoryType.getId()) == null) {
                parametersOfCategoryImpl.createRam(ramMemoryType);
            }
            if (parametersOfCategoryImpl.check(ramNumberOfModules.getId()) == null) {
                parametersOfCategoryImpl.createRam(ramNumberOfModules);
            }
            if (parametersOfCategoryImpl.check(ramClockFrequency.getId()) == null) {
                parametersOfCategoryImpl.createRam(ramClockFrequency);
            }
            if (parametersOfCategoryImpl.check(ramVolumeOfOneMemoryModule.getId()) == null) {
                parametersOfCategoryImpl.createRam(ramVolumeOfOneMemoryModule);
            }


            parametersOfCharacteristicsImpl.create(ramMemoryType, characteristics);
            parametersOfCharacteristicsImpl.create(ramNumberOfModules, characteristics);
            parametersOfCharacteristicsImpl.create(ramClockFrequency, characteristics);
            parametersOfCharacteristicsImpl.create(ramVolumeOfOneMemoryModule, characteristics);

            Product product = new Product(modelName, price);
            product.setCharacteristicsId(characteristics.getId());
            product.setDealerId(cpuDealer.getId());
            productImpl.create(product);
        }

    }


    public void addPower(String dealer,String modelName,String formFactor,String Power,
                        String mainPowerConnector, int price){

        if(productImpl.findByName(modelName) == null) {

            if (dealerImpl.findByName(dealer) == null) {
                Dealer newDealer = new Dealer(dealer);
                dealerImpl.create(newDealer);
            }
            Dealer cpuDealer = dealerImpl.findByName(dealer);
            Characteristics characteristics = characteristicsImpl.getCount(); //получение кол-во характеристик
            characteristicsImpl.create(characteristics); // создание новой характеристики, туда кладется id = кол-во характеристик + 1
            characteristics = characteristicsImpl.getCount();

            if (parametersImpl.findByParameter(formFactor) == null) {
                Parameters psuFormFactor = new Parameters(formFactor, "Form factor");
                parametersImpl.create(psuFormFactor);
            }
            Parameters psuFormFactor = parametersImpl.findByParameter(formFactor);//получение параметра


            if (parametersImpl.findByParameter(Power) == null) {
                Parameters psuPower = new Parameters(Power, "power");
                parametersImpl.create(psuPower);
            }
            Parameters psuPower = parametersImpl.findByParameter(Power);//получение параметра


            if (parametersImpl.findByParameter(mainPowerConnector) == null) {
                Parameters psuMainPowerConnector = new Parameters(mainPowerConnector, "main power connector");
                parametersImpl.create(psuMainPowerConnector);
            }
            Parameters psuMainPowerConnector = parametersImpl.findByParameter(mainPowerConnector);//получение параметра


            if (parametersOfCategoryImpl.check(psuFormFactor.getId()) == null) {
                parametersOfCategoryImpl.createPower(psuFormFactor);
            }
            if (parametersOfCategoryImpl.check(psuPower.getId()) == null) {
                parametersOfCategoryImpl.createPower(psuPower);
            }
            if (parametersOfCategoryImpl.check(psuMainPowerConnector.getId()) == null) {
                parametersOfCategoryImpl.createPower(psuMainPowerConnector);
            }


            parametersOfCharacteristicsImpl.create(psuFormFactor, characteristics);
            parametersOfCharacteristicsImpl.create(psuPower, characteristics);
            parametersOfCharacteristicsImpl.create(psuMainPowerConnector, characteristics);

            Product product = new Product(modelName, price);
            product.setCharacteristicsId(characteristics.getId());
            product.setDealerId(cpuDealer.getId());
            productImpl.create(product);
        }

    }


    public void addCooler(String dealer,String modelName,String BaseMaterial,String socket,
                          String powerDissipation,String numberOfHeatPipes, int price){

        if(productImpl.findByName(modelName) == null) {

            if (dealerImpl.findByName(dealer) == null) {
                Dealer newDealer = new Dealer(dealer);
                dealerImpl.create(newDealer);
            }
            Dealer cpuDealer = dealerImpl.findByName(dealer);
            Characteristics characteristics = characteristicsImpl.getCount(); //получение кол-во характеристик
            characteristicsImpl.create(characteristics); // создание новой характеристики, туда кладется id = кол-во характеристик + 1
            characteristics = characteristicsImpl.getCount();

            if (parametersImpl.findByParameter(BaseMaterial) == null) {
                Parameters coolerBaseMaterial = new Parameters(BaseMaterial, "Base Material");
                parametersImpl.create(coolerBaseMaterial);
            }
            Parameters coolerBaseMaterial = parametersImpl.findByParameter(BaseMaterial);//получение параметра


            if (parametersImpl.findByParameter(socket) == null) {
                Parameters coolerSocket = new Parameters(socket, "Socket");
                parametersImpl.create(coolerSocket);
            }
            Parameters coolerSocket = parametersImpl.findByParameter(socket);//получение параметра


            if (parametersImpl.findByParameter(powerDissipation) == null) {
                Parameters coolerPowerDissipation = new Parameters(powerDissipation, "Power Dissipation");
                parametersImpl.create(coolerPowerDissipation);
            }
            Parameters coolerPowerDissipation = parametersImpl.findByParameter(powerDissipation);//получение параметра


            if (parametersImpl.findByParameter(numberOfHeatPipes) == null) {
                Parameters coolerNumberOfHeatPipes = new Parameters(numberOfHeatPipes, "Number Of Heat Pipes");
                parametersImpl.create(coolerNumberOfHeatPipes);
            }
            Parameters coolerNumberOfHeatPipes = parametersImpl.findByParameter(numberOfHeatPipes);//получение параметра


            if (parametersOfCategoryImpl.check(coolerBaseMaterial.getId()) == null) {
                parametersOfCategoryImpl.createCooler(coolerBaseMaterial);
            }
            if (parametersOfCategoryImpl.check(coolerSocket.getId()) == null) {
                parametersOfCategoryImpl.createCooler(coolerSocket);
            }
            if (parametersOfCategoryImpl.check(coolerPowerDissipation.getId()) == null) {
                parametersOfCategoryImpl.createCooler(coolerPowerDissipation);
            }
            if (parametersOfCategoryImpl.check(coolerNumberOfHeatPipes.getId()) == null) {
                parametersOfCategoryImpl.createCooler(coolerNumberOfHeatPipes);
            }


            parametersOfCharacteristicsImpl.create(coolerBaseMaterial, characteristics);
            parametersOfCharacteristicsImpl.create(coolerSocket, characteristics);
            parametersOfCharacteristicsImpl.create(coolerPowerDissipation, characteristics);
            parametersOfCharacteristicsImpl.create(coolerNumberOfHeatPipes, characteristics);

            Product product = new Product(modelName, price);
            product.setCharacteristicsId(characteristics.getId());
            product.setDealerId(cpuDealer.getId());
            productImpl.create(product);
        }

    }


    public void addBody(String dealer,String modelName,String caseSize,String formFactorOfCompatibleBoards,
                        String powerSupplyPlacement, int price){

        if(productImpl.findByName(modelName) == null) {

            if (dealerImpl.findByName(dealer) == null) {
                Dealer newDealer = new Dealer(dealer);
                dealerImpl.create(newDealer);
            }
            Dealer cpuDealer = dealerImpl.findByName(dealer);
            Characteristics characteristics = characteristicsImpl.getCount(); //получение кол-во характеристик
            characteristicsImpl.create(characteristics); // создание новой характеристики, туда кладется id = кол-во характеристик + 1
            characteristics = characteristicsImpl.getCount();

            if (parametersImpl.findByParameter(caseSize) == null) {
                Parameters bodyCaseSize = new Parameters(caseSize, "Case Size");
                parametersImpl.create(bodyCaseSize);
            }
            Parameters bodyCaseSize = parametersImpl.findByParameter(caseSize);//получение параметра


            if (parametersImpl.findByParameter(formFactorOfCompatibleBoards) == null) {
                Parameters bodyFormFactorOfCompatibleBoards = new Parameters(formFactorOfCompatibleBoards, "Form Factor Of Compatible Boards");
                parametersImpl.create(bodyFormFactorOfCompatibleBoards);
            }
            Parameters bodyFormFactorOfCompatibleBoards = parametersImpl.findByParameter(formFactorOfCompatibleBoards);//получение параметра


            if (parametersImpl.findByParameter(powerSupplyPlacement) == null) {
                Parameters bodyPowerSupplyPlacement = new Parameters(powerSupplyPlacement, "Power Supply Placement");
                parametersImpl.create(bodyPowerSupplyPlacement);
            }
            Parameters bodyPowerSupplyPlacement = parametersImpl.findByParameter(powerSupplyPlacement);//получение параметра


            if (parametersOfCategoryImpl.check(bodyCaseSize.getId()) == null) {
                parametersOfCategoryImpl.createBody(bodyCaseSize);
            }
            if (parametersOfCategoryImpl.check(bodyFormFactorOfCompatibleBoards.getId()) == null) {
                parametersOfCategoryImpl.createBody(bodyFormFactorOfCompatibleBoards);
            }
            if (parametersOfCategoryImpl.check(bodyPowerSupplyPlacement.getId()) == null) {
                parametersOfCategoryImpl.createBody(bodyPowerSupplyPlacement);
            }


            parametersOfCharacteristicsImpl.create(bodyCaseSize, characteristics);
            parametersOfCharacteristicsImpl.create(bodyFormFactorOfCompatibleBoards, characteristics);
            parametersOfCharacteristicsImpl.create(bodyPowerSupplyPlacement, characteristics);

            Product product = new Product(modelName, price);
            product.setCharacteristicsId(characteristics.getId());
            product.setDealerId(cpuDealer.getId());
            productImpl.create(product);
        }

    }

    public void addSsd(String dealer,String modelName,String capacity,
                       String maximumDataTransferRate,int price){

        if(productImpl.findByName(modelName) == null) {

            if (dealerImpl.findByName(dealer) == null) {
                Dealer newDealer = new Dealer(dealer);
                dealerImpl.create(newDealer);
            }
            Dealer cpuDealer = dealerImpl.findByName(dealer);
            Characteristics characteristics = characteristicsImpl.getCount(); //получение кол-во характеристик
            characteristicsImpl.create(characteristics); // создание новой характеристики, туда кладется id = кол-во характеристик + 1
            characteristics = characteristicsImpl.getCount();

            if (parametersImpl.findByParameter(capacity) == null) {
                Parameters ssdCapacity = new Parameters(capacity, "Capacity");
                parametersImpl.create(ssdCapacity);
            }
            Parameters ssdCapacity = parametersImpl.findByParameter(capacity);//получение параметра


            if (parametersImpl.findByParameter(maximumDataTransferRate) == null) {
                Parameters ssdMaximumDataTransferRate = new Parameters(maximumDataTransferRate, "Maximum Data Transfer Rate");
                parametersImpl.create(ssdMaximumDataTransferRate);
            }
            Parameters ssdMaximumDataTransferRate = parametersImpl.findByParameter(maximumDataTransferRate);//получение параметра


            if (parametersOfCategoryImpl.check(ssdCapacity.getId()) == null) {
                parametersOfCategoryImpl.createSsd(ssdCapacity);
            }
            if (parametersOfCategoryImpl.check(ssdMaximumDataTransferRate.getId()) == null) {
                parametersOfCategoryImpl.createSsd(ssdMaximumDataTransferRate);
            }


            parametersOfCharacteristicsImpl.create(ssdCapacity, characteristics);
            parametersOfCharacteristicsImpl.create(ssdMaximumDataTransferRate, characteristics);

            Product product = new Product(modelName, price);
            product.setCharacteristicsId(characteristics.getId());
            product.setDealerId(cpuDealer.getId());
            productImpl.create(product);
        }

    }


    public void addMotherboard(String dealer,String modelName,String formFactor,String socket,
                               String supportedMemoryType,String numberOfMemorySlots,int price){

        if(productImpl.findByName(modelName) == null) {

            if (dealerImpl.findByName(dealer) == null) {
                Dealer newDealer = new Dealer(dealer);
                dealerImpl.create(newDealer);
            }
            Dealer cpuDealer = dealerImpl.findByName(dealer);
            Characteristics characteristics = characteristicsImpl.getCount(); //получение кол-во характеристик
            characteristicsImpl.create(characteristics); // создание новой характеристики, туда кладется id = кол-во характеристик + 1
            characteristics = characteristicsImpl.getCount();

            if (parametersImpl.findByParameter(formFactor) == null) {
                Parameters motherboardFormFactor = new Parameters(formFactor, "Form Factor");
                parametersImpl.create(motherboardFormFactor);
            }
            Parameters motherboardFormFactor = parametersImpl.findByParameter(formFactor);//получение параметра


            if (parametersImpl.findByParameter(socket) == null) {
                Parameters motherboardSocket = new Parameters(socket, "Socket");
                parametersImpl.create(motherboardSocket);
            }
            Parameters motherboardSocket = parametersImpl.findByParameter(socket);//получение параметра


            if (parametersImpl.findByParameter(supportedMemoryType) == null) {
                Parameters motherboardSupportedMemoryType = new Parameters(supportedMemoryType, "Supported Memory Type");
                parametersImpl.create(motherboardSupportedMemoryType);
            }
            Parameters motherboardSupportedMemoryType = parametersImpl.findByParameter(supportedMemoryType);//получение параметра


            if (parametersImpl.findByParameter(numberOfMemorySlots) == null) {
                Parameters motherboardNumberOfMemorySlots = new Parameters(numberOfMemorySlots, "Number Of Memory Slots");
                parametersImpl.create(motherboardNumberOfMemorySlots);
            }
            Parameters motherboardNumberOfMemorySlots = parametersImpl.findByParameter(numberOfMemorySlots);//получение параметра


            if (parametersOfCategoryImpl.check(motherboardFormFactor.getId()) == null) {
                parametersOfCategoryImpl.createMotherboard(motherboardFormFactor);
            }
            if (parametersOfCategoryImpl.check(motherboardSocket.getId()) == null) {
                parametersOfCategoryImpl.createMotherboard(motherboardSocket);
            }
            if (parametersOfCategoryImpl.check(motherboardSupportedMemoryType.getId()) == null) {
                parametersOfCategoryImpl.createMotherboard(motherboardSupportedMemoryType);
            }
            if (parametersOfCategoryImpl.check(motherboardNumberOfMemorySlots.getId()) == null) {
                parametersOfCategoryImpl.createMotherboard(motherboardNumberOfMemorySlots);
            }


            parametersOfCharacteristicsImpl.create(motherboardFormFactor, characteristics);
            parametersOfCharacteristicsImpl.create(motherboardSocket, characteristics);
            parametersOfCharacteristicsImpl.create(motherboardSupportedMemoryType, characteristics);
            parametersOfCharacteristicsImpl.create(motherboardNumberOfMemorySlots, characteristics);

            Product product = new Product(modelName, price);
            product.setCharacteristicsId(characteristics.getId());
            product.setDealerId(cpuDealer.getId());
            productImpl.create(product);
        }

    }

}
