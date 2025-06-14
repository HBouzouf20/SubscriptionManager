package org.hbdev.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionPlan {
    private int id;
    private String label;
    private String description;
    private double amount;
}
