package com.ecom.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Entity


public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Purchase price is mandatory")
    @DecimalMin("0.0") @DecimalMax("9000000000.0")
    private double purchasePrice;

    @NotNull(message = "Sale price is mandatory")
    @DecimalMin("0.0") @DecimalMax("9000000000.0")
    private double salePrice;

    @NotNull(message = "Quantity is mandatory")
    @DecimalMin("0.0") @DecimalMax("9000000000.0")
    private long quantity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PackagePrice> packagePrice;

    @DecimalMin("0.0") @DecimalMax("9000000000.0")
    private double discountAmount;

    @DecimalMin("0.0") @DecimalMax("9000000000.0")
    private double discountPercentage;

    @NotNull(message = "Media file can not be null.")
    @OneToMany(cascade = CascadeType.ALL)
    private List<MediaFile> mediaFileList;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Attribute> attributeList;

    public ProductVariant(double purchasePrice, double salePrice, long quantity, List<PackagePrice> packagePrice, double discountAmount, double discountPercentage, List<MediaFile> mediaFileList, List<Attribute> attributeList) {
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.packagePrice = packagePrice;
        this.discountAmount = discountAmount;
        this.discountPercentage = discountPercentage;
        this.mediaFileList = mediaFileList;
        this.attributeList = attributeList;
    }

}
