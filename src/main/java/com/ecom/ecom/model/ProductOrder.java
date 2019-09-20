package com.ecom.ecom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long customerId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductVariantBundle> productVariantBundleList;

    private double totalPrice;
    private double totalDiscountAmount;
    private double totalDiscountPercentage;
    private double totalDiscount;
    private double orderPrice;
    private boolean pending;
    private boolean delivered;

//    public ProductOrder(long customerId, List<ProductVariantBundle> productVariantBundleList) {
//        this.customerId = customerId;
//        this.productVariantBundleList = productVariantBundleList;
//        this.totalPrice = this.calculateTotalPrice(productVariantBundleList);
//        this.totalDiscountAmount = this.calculateTotalDiscountAmount(productVariantBundleList);
//        this.totalDiscountPercentage = this.calculateTotalDiscountPercentage(productVariantBundleList);
//        this.totalDiscount = this.totalDiscountAmount + this.totalDiscountPercentage;
//        this.orderPrice = this.totalPrice - this.totalDiscount;
//        this.pending=true;
//        this.delivered=false;
//    }


    public ProductOrder(long customerId, List<ProductVariantBundle> productVariantBundleList) {
        this.customerId = customerId;
        this.productVariantBundleList = productVariantBundleList;
    }

    public ProductOrder(long customerId, List<ProductVariantBundle> productVariantBundleList, double totalPrice, double totalDiscountAmount, double totalDiscountPercentage, double totalDiscount, double orderPrice, boolean pending, boolean delivered) {
        this.customerId = customerId;
        this.productVariantBundleList = productVariantBundleList;
        this.totalPrice = totalPrice;
        this.totalDiscountAmount = totalDiscountAmount;
        this.totalDiscountPercentage = totalDiscountPercentage;
        this.totalDiscount = totalDiscount;
        this.orderPrice = orderPrice;
        this.pending = pending;
        this.delivered = delivered;

    }
}
