package com.sales.manager.repository;

import com.sales.manager.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SaleEntity, Integer> {

}
