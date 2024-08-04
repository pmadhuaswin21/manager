package com.sales.manager.controller;

import com.sales.manager.exception.ProductNotFoundException;
import com.sales.manager.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SaleController {

    public final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<Double> totalSalesAmount() {
        return ResponseEntity.ok(saleService.getTotalRevenue());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Double> totalSalesAmountByProduct(@PathVariable int productId) throws ProductNotFoundException {
        return ResponseEntity.ok(saleService.getRevenueByProduct(productId));
    }
}
