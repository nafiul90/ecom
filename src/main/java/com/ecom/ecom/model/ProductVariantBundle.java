package com.ecom.ecom.model;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ProductVariantBundle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long variantId;

    private long quantity;

    public ProductVariantBundle(long variantId, long quantity) {
        this.variantId = variantId;
        this.quantity = quantity;
    }
}
