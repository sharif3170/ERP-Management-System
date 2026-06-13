package com.erp.management.controller;

import com.erp.management.entity.Alert;
import com.erp.management.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    @Autowired
    private AlertRepository alertRepository;

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @PostMapping
    public Alert createAlert(@RequestBody Alert alert) {
        return alertRepository.save(alert);
    }

    @PutMapping("/{id}")
    public Alert updateAlert(@PathVariable Long id, @RequestBody Alert alertDetails) {
        Alert alert = alertRepository.findById(id).orElseThrow();
        alert.setProductId(alertDetails.getProductId());
        alert.setAlertMessage(alertDetails.getAlertMessage());
        return alertRepository.save(alert);
    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable Long id) {
        alertRepository.deleteById(id);
    }
}
