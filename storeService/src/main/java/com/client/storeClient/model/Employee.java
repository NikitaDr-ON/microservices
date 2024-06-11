package com.client.storeClient.model;

public class Employee {
    private long id;
    private String fio;
    private String profession;

    public Employee() {
    }

    public Employee(String fio, String profession) {
        this.fio = fio;
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
