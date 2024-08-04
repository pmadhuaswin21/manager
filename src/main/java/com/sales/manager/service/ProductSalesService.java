package com.sales.manager.service;

import com.sales.manager.entity.ProductEntity;
import com.sales.manager.exception.ProductNotFoundException;
import com.sales.manager.model.Product;
import com.sales.manager.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.sales.manager.constants.Constants.PRODUCT_NOT_FOUND_EXCEPTION;
import static com.sales.manager.converter.ProductConverter.convertProductDTOListToProductList;
import static com.sales.manager.converter.ProductConverter.convertProductDTOToProduct;
import static com.sales.manager.converter.ProductConverter.convertProductToProductDTO;

@Service
public class ProductSalesService implements ProductService, SaleService {

    private final ProductRepository productRepository;

    public ProductSalesService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(PageRequest pageRequest) {
        return convertProductDTOListToProductList(productRepository.findAll(pageRequest).get().toList());
    }

    @Override
    public Product getProductById(int id) throws ProductNotFoundException {
        Optional<ProductEntity> optionalProductDTO = productRepository.findById(id);
        if (optionalProductDTO.isPresent()) {
            return convertProductDTOToProduct(optionalProductDTO.get());
        } else {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION + id);
        }

    }

    @Override
    public void addProduct(Product product) {
        ProductEntity productEntity = convertProductToProductDTO(product);
        productRepository.save(productEntity);
    }

    @Override
    public void updateProduct(int id, Product product) throws ProductNotFoundException {

        Optional<ProductEntity> optionalProductDTO = productRepository.findById(id);
        if (optionalProductDTO.isPresent()) {
            ProductEntity productEntity = optionalProductDTO.get();
            productEntity.setName(product.name());
            productEntity.setDescription(product.description());
            productEntity.setPrice(product.price());
            productEntity.setQuantity(product.quantity());
            productRepository.save(productEntity);
        } else {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION + id);
        }

    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public double getTotalRevenue() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return productEntityList.stream()
                .reduce(0.0, (intermediatePrice, productEntity) ->
                        intermediatePrice + calculateRevenueForAProduct(productEntity), Double::sum);
    }

    @Override
    public double getRevenueByProduct(int productId) throws ProductNotFoundException {
        Optional<ProductEntity> productDTO = productRepository.findById(productId);
        if (productDTO.isPresent()) {
            return calculateRevenueForAProduct(productDTO.get());

        } else {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION + productId);
        }
    }

    private double calculateRevenueForAProduct(ProductEntity productEntity) {
        return productEntity.getPrice() * calculateTotalSales(productEntity);
    }

    private int calculateTotalSales(ProductEntity productEntity) {
        return productEntity.getSales().stream()
                .reduce(0, (partialQuantity, sales) -> partialQuantity + sales.getQuantity(), Integer::sum);
    }
}
