package org.hbdev.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subscription {
    private int id;
    private SubscriptionPlan plan;
    private String relativeMonth;
    private String relativeYear;
}
