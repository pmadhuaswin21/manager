package com.sales.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.manager.entity.ProductEntity;
import com.sales.manager.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SalesManagerApplication {

    private static Logger LOG = LoggerFactory.getLogger(SalesManagerApplication.class);


    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SalesManagerApplication.class, args);

        ProductRepository productRepository = context.getBean(ProductRepository.class);

        addProductDataToDataBase(productRepository);
    }

    private static void addProductDataToDataBase(ProductRepository productRepository) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productRepository.saveAll(
                    List.of(objectMapper.readValue(new File("src/main/resources/productList.json"),
							ProductEntity[].class))
            );

        } catch (IOException e) {
            LOG.error("Error reading product list ", e);
        } catch (Exception e) {
			LOG.error("unknown error while saving the products ", e);
		}
    }

}
