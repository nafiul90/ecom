package com.ecom.ecom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String methodName;
    private long customerId;
    private long orderId;
    private long amount;

    public Payment(String methodName, long customerId, long orderId, long amount) {
        this.methodName = methodName;
        this.customerId = customerId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
