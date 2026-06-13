package com.erp.management.repository;
import com.erp.management.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InventoryRepository
        extends JpaRepository<Inventory, Long> {
}
