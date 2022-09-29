package com.company.oils.core.models;

public class Buy {
    String fio, date, variant, product;
    Long count;
    Status status;
    String docId;

    public Buy(String fio, String date, Long count, String product, Long status, String variant, String docId) {
        this.fio = fio;
        this.date = date;
        this.count = count;
        this.product = product;
        this.docId = docId;
        this.status = Status.values()[(int)(long)status];
        this.variant = variant;
    }

    public String getDocId() {
        return docId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
