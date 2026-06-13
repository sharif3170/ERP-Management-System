package com.erp.management.service;
import com.erp.management.entity.Inventory;
import com.erp.management.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class InventoryService {
    @Autowired
    private InventoryRepository repository;
    public Inventory saveInventory(Inventory inventory){
        return repository.save(inventory);
    }
    public List<Inventory> getAllInventory(){
        return repository.findAll();
    }
    public void deleteInventory(Long id){
        repository.deleteById(id);
    }
    public long getInventoryCount() {
    return repository.count();
}
}
