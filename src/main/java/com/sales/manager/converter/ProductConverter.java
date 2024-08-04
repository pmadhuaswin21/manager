package com.sales.manager.converter;

import com.sales.manager.entity.ProductEntity;
import com.sales.manager.entity.SaleEntity;
import com.sales.manager.model.Product;
import com.sales.manager.model.Sale;

import java.util.List;

public class ProductConverter {

    private ProductConverter() {

    }

    public static List<Product> convertProductDTOListToProductList(List<ProductEntity> productEntityList) {
        return productEntityList
                .stream()
                .map(ProductConverter::convertProductDTOToProduct)
                .toList();
    }

    public static Product convertProductDTOToProduct(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getQuantity(),
                convertSaleDTOListToSaleList(productEntity.getSales(), productEntity.getId())
        );
    }

    private static List<Sale> convertSaleDTOListToSaleList(List<SaleEntity> saleEntityList, int productId) {
        return saleEntityList.stream()
                .map((saleEntity -> convertSaleDTOToSale(saleEntity, productId))).toList();
    }

    private static Sale convertSaleDTOToSale(SaleEntity saleEntity, int productId) {
        return new Sale(saleEntity.getId(), productId, saleEntity.getQuantity(), saleEntity.getSaleDate());
    }

    public static ProductEntity convertProductToProductDTO(Product product) {
        return new ProductEntity(
                product.name(),
                product.description(),
                product.price(),
                product.quantity(),
                convertSaleListToSaleDTOList(product.sales())
        );
    }

    private static List<SaleEntity> convertSaleListToSaleDTOList(List<Sale> sales) {
        return sales.stream().map(ProductConverter::convertSaleToSaleDTO).toList();
    }

    private static SaleEntity convertSaleToSaleDTO(Sale sale) {
        return new SaleEntity(sale.quantity(), sale.saleDate());
    }

}
