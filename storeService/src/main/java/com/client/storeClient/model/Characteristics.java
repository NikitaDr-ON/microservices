package com.client.storeClient.model;

public class Characteristics {
    private long id;
    private String parameter;
    private String nameOfParameter;

    private long parameterId;

    public Characteristics() {
    }

    public long getParameterId() {
        return parameterId;
    }

    public void setParameterId(long parameterId) {
        this.parameterId = parameterId;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getNameOfParameter() {
        return nameOfParameter;
    }

    public void setNameOfParameter(String nameOfParameter) {
        this.nameOfParameter = nameOfParameter;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return (getNameOfParameter() + " " + getParameter() + " ");
    }
}
