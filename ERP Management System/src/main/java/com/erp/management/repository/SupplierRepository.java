package com.erp.management.repository;
import com.erp.management.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SupplierRepository
        extends JpaRepository<Supplier, Long> {
}
