package com.microservice.inventoryservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	 Optional<Inventory> findBySkuCode(String skuCode);
}