package com.sales.manager.service;

import com.sales.manager.exception.ProductNotFoundException;

public interface SaleService {
    double getTotalRevenue();
    double getRevenueByProduct(int productId) throws ProductNotFoundException;
}
