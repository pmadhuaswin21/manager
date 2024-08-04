package com.sales.manager.service;

import com.sales.manager.exception.ProductNotFoundException;
import com.sales.manager.model.Product;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts(PageRequest pageRequest);

    Product getProductById(int id) throws ProductNotFoundException;

    void addProduct(Product product);

    void updateProduct(int id, Product product) throws ProductNotFoundException;

    void deleteProduct(int id);
}
