package com.sales.manager.model;

import java.util.Date;


public record Sale(int id,
                   int productId,
                   int quantity,
                   Date saleDate) {


    public Sale(int id, int productId, int quantity, Date saleDate) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.saleDate = saleDate;
    }
}
