package com.microservice.inventoryservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	 List<Inventory> findBySkuCodeIn(List<String> skuCode);
}