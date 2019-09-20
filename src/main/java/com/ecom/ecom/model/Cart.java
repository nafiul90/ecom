package com.ecom.ecom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private long customerId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductVariantBundle> productVariantBundleList;

    public Cart(long customerId, List<ProductVariantBundle> productVariantBundleList) {
        this.customerId = customerId;
        this.productVariantBundleList = productVariantBundleList;
    }
}
