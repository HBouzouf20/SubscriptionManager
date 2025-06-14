package org.hbdev.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class Invoice {
    private int id;
    private String invoiceNumber;
    private Client client;
    private SubscriptionCategory subscription;
    private Payment payment;
}
