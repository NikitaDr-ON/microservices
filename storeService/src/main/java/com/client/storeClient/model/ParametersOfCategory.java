package com.client.storeClient.model;

public class ParametersOfCategory {
    private long parameterId;
    private long categoryId;

    public ParametersOfCategory() {
    }

    public long getParameterId() {
        return parameterId;
    }

    public void setParameterId(long parameterId) {
        this.parameterId = parameterId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
