package org.hbdev.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    private int id;
    private String paymentType;
    private String paymentDate;
    private double amount;
    private Client client;
    private Subscription subscription;
}
