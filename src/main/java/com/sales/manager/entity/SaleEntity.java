package com.sales.manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "Sales")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int quantity;
    Date saleDate;

    public SaleEntity() {
    }

    public SaleEntity(int quantity, Date saleDate) {
        this.quantity = quantity;
        this.saleDate = saleDate;
    }
}
