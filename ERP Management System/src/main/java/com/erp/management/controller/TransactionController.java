package com.erp.management.controller;

import com.erp.management.entity.Transaction;
import com.erp.management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        transaction.setProductId(transactionDetails.getProductId());
        transaction.setTransactionType(transactionDetails.getTransactionType());
        transaction.setQuantity(transactionDetails.getQuantity());
        return transactionRepository.save(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}
