package com.erp.management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts_erp")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    private Long productId;

    private String alertMessage;

    @Column(insertable = false, updatable = false)
    private LocalDateTime alertDate;

    // Getters and Setters
    public Long getAlertId() { return alertId; }
    public void setAlertId(Long alertId) { this.alertId = alertId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getAlertMessage() { return alertMessage; }
    public void setAlertMessage(String alertMessage) { this.alertMessage = alertMessage; }

    public LocalDateTime getAlertDate() { return alertDate; }
    public void setAlertDate(LocalDateTime alertDate) { this.alertDate = alertDate; }
}
