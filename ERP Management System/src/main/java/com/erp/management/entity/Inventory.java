package com.erp.management.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "inventory_erp")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    private String productName;
    private Integer quantity;
    private String warehouseLocation;
    public Long getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getWarehouseLocation() {
        return warehouseLocation;
    }
    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }
}
