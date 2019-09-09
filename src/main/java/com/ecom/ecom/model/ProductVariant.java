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

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public double getPurchasePrice() {
//        return purchasePrice;
//    }
//
//    public void setPurchasePrice(double purchasePrice) {
//        this.purchasePrice = purchasePrice;
//    }
//
//    public double getSalePrice() {
//        return salePrice;
//    }
//
//    public void setSalePrice(double salePrice) {
//        this.salePrice = salePrice;
//    }
//
//    public long getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(long quantity) {
//        this.quantity = quantity;
//    }
//
//    public List<PackagePrice> getPackagePrice() {
//        return packagePrice;
//    }
//
//    public void setPackagePrice(List<PackagePrice> packagePrice) {
//        this.packagePrice = packagePrice;
//    }
//
//    public double getDiscountAmount() {
//        return discountAmount;
//    }
//
//    public void setDiscountAmount(double discountAmount) {
//        this.discountAmount = discountAmount;
//    }
//
//    public double getDiscountPercentage() {
//        return discountPercentage;
//    }
//
//    public void setDiscountPercentage(double discountPercentage) {
//        this.discountPercentage = discountPercentage;
//    }
//
//    public List<MediaFile> getMediaFileList() {
//        return mediaFileList;
//    }
//
//    public void setMediaFileList(List<MediaFile> mediaFileList) {
//        this.mediaFileList = mediaFileList;
//    }
//
//    public List<Attribute> getAttributeList() {
//        return attributeList;
//    }
//
//    public void setAttributeList(List<Attribute> attributeList) {
//        this.attributeList = attributeList;
//    }
}
