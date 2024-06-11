package com.client.storeClient.model;

public class Order {
    private long id;
    private int price;
    private String orderDate;
    private String deliveryDate;
    private String toDelivery;
    private Product cpu;
    private Product gpu;
    private Product psu;
    private Product motherboard;
    private Product ram;
    private Product cooler;
    private Product body;
    private Product ssd;
    private Buyer buyer;
    private long buyerId;

    private boolean complete;


    public Order() {
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getToDelivery() {
        return toDelivery;
    }

    public void setToDelivery(String toDelivery) {
        this.toDelivery = toDelivery;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Product getCpu() {
        return cpu;
    }

    public void setCpu(Product cpu) {
        this.cpu = cpu;
    }

    public Product getGpu() {
        return gpu;
    }

    public void setGpu(Product gpu) {
        this.gpu = gpu;
    }

    public Product getPsu() {
        return psu;
    }

    public void setPsu(Product psu) {
        this.psu = psu;
    }

    public Product getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Product motherboard) {
        this.motherboard = motherboard;
    }

    public Product getRam() {
        return ram;
    }

    public void setRam(Product ram) {
        this.ram = ram;
    }

    public Product getCooler() {
        return cooler;
    }

    public void setCooler(Product cooler) {
        this.cooler = cooler;
    }

    public Product getBody() {
        return body;
    }

    public void setBody(Product body) {
        this.body = body;
    }

    public Product getSsd() {
        return ssd;
    }

    public void setSsd(Product ssd) {
        this.ssd = ssd;
    }

    @Override
    public String toString() {
        String cpu = "not",gpu = "not",ram = "not",motherboard = "not",psu = "not",cooler = "not",body = "not",ssd = "not";
        if(getCpu() != null)
            cpu = getCpu().getName();
        if(getGpu() != null)
            gpu = getGpu().getName();
        if(getRam() != null)
            ram = getRam().getName();
        if(getMotherboard() != null)
            motherboard = getMotherboard().getName();
        if(getPsu() != null)
            psu = getPsu().getName();
        if(getCooler() != null)
            cooler = getCooler().getName();
        if(getBody() != null)
            body = getBody().getName();
        if(getSsd() != null)
            ssd = getSsd().getName();
        return ("cpu : " + cpu + " gpu : " + gpu + "\n ram : " + ram + "\n motherboard : " + motherboard + "\n psu : "
        + psu + "\n cooler : " + cooler + "\n body : " + body + "\n ssd : " + ssd + "\n price : " + getPrice());
    }
}
