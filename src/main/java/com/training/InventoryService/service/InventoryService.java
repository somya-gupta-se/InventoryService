package com.training.InventoryService.service;

import com.training.InventoryService.entity.Inventory;
import com.training.InventoryService.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory getInventoryByProductCode(String productCode) {
        return inventoryRepository.findByProductCode(productCode)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found for product code: " + productCode));
    }

    public boolean isProductInStock(String productCode) {
        Inventory inventory = getInventoryByProductCode(productCode);
        return inventory.getStockQuantity() > 0;
    }

    public Inventory updateStock(String productCode, int quantity) {
        Inventory inventory = getInventoryByProductCode(productCode);
        inventory.setStockQuantity(inventory.getStockQuantity() + quantity);

        return inventoryRepository.save(inventory);
    }



    public Inventory updateInventory(String productCode, int quantity) {
        Inventory inventory = getInventoryByProductCode(productCode);
        inventory.setStockQuantity(inventory.getStockQuantity() - quantity);
        LOGGER.info("Inventory stock quantity "+inventory.getStockQuantity());
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(String productCode) {
        Inventory inventory = getInventoryByProductCode(productCode);
        inventoryRepository.delete(inventory);
    }

    public void updateStock(Long orderId) {
    }
}