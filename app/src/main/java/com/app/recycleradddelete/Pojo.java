package com.app.recycleradddelete;

public class Pojo {

    private String prodName;
    private String prodPrice;

    public Pojo(String prodName, String prodPrice) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdPrice() {
        return prodPrice;
    }
}
