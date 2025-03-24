package com.training.InventoryService.controller;

import com.training.InventoryService.entity.Inventory;
import com.training.InventoryService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    //this could only be accessed by admin
    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.addInventory(inventory));
    }

    @GetMapping("/welcome")
    public String secured() {
        return "This is secured endpoint";
    }

    @GetMapping("/get/{productCode}")
    public ResponseEntity<Inventory> getInventory(@PathVariable String productCode) {
        return ResponseEntity.ok(inventoryService.getInventoryByProductCode(productCode));
    }

    @GetMapping("/check/{productCode}/in-stock")
    public Boolean checkStock(@PathVariable String productCode) {
        return inventoryService.isProductInStock(productCode);
    }

    @PutMapping("/{productCode}")
    public ResponseEntity<Inventory> updateStock(@PathVariable String productCode, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.updateStock(productCode, quantity));
    }

    @PutMapping("/update/{productCode}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable String productCode, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.updateInventory(productCode, quantity));
    }

    //this could only be accessed by admin
    @DeleteMapping("/{productCode}")
    public ResponseEntity<String> deleteInventory(@PathVariable String productCode) {
        inventoryService.deleteInventory(productCode);
        return ResponseEntity.ok("Inventory with product code "+productCode+" deleted!");
    }
}
