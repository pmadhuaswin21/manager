package com.sales.manager.model;

import java.util.List;


public record Product(int id,
                      String name,
                      String description,
                      double price,
                      int quantity,
                      List<Sale> sales) {
}
