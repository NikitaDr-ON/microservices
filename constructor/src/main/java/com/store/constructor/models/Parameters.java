package com.store.constructor.models;

public class Parameters {
    private long id;
    private String parameter;
    private String category;
    private String nameOfParameter;

    public Parameters() {
    }

    public Parameters(String parameter, String nameOfParameter) {
        this.parameter = parameter; this.nameOfParameter = nameOfParameter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNameOfParameter() {
        return nameOfParameter;
    }

    public void setNameOfParameter(String nameOfParameter) {
        this.nameOfParameter = nameOfParameter;
    }
}
