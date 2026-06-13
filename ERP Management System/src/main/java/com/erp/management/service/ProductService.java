package com.erp.management.service;
import com.erp.management.entity.Product;
import com.erp.management.entity.Transaction;
import com.erp.management.repository.ProductRepository;
import com.erp.management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;

    public Product saveProduct(Product product) {
        boolean isNew = product.getProductId() == null;
        Product savedProduct = productRepository.save(product);
        
        if (isNew) {
            Transaction t = new Transaction();
            t.setProductId(savedProduct.getProductId());
            t.setTransactionType("INITIAL_STOCK");
            t.setQuantity(0);
            transactionRepository.save(t);
        }
        
        return savedProduct;
    }
    public long getProductCount() {
    return productRepository.count();
}
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
