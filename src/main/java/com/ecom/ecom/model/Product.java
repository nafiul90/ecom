package com.ecom.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3,max = 20,message = "Name must be between 3 and 20 character long.")
    private String name;

    @NotBlank(message = "Category is mandatory")
    @Size(min = 3,max = 1000,message = "Name must be between 3 and 1000 character long.")
    private String category;

    @NotNull(message = "Media file can not be null.")
    @OneToOne(cascade = CascadeType.ALL)
    private MediaFile mediaFile; // this is for thumbnail image.
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductVariant> productVariantList;

    public Product(String name, String category, MediaFile mediaFile, List<ProductVariant> productVariantList) {
        this.name = name;
        this.category = category;
        this.mediaFile = mediaFile;
        this.productVariantList = productVariantList;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCatagory() {
//        return catagory;
//    }
//
//    public void setCatagory(String catagory) {
//        this.catagory = catagory;
//    }
//
//    public MediaFile getMediaFile() {
//        return mediaFile;
//    }
//
//    public void setMediaFile(MediaFile mediaFile) {
//        this.mediaFile = mediaFile;
//    }
//
//    public List<ProductVariant> getProductVariantList() {
//        return productVariantList;
//    }
//
//    public void setProductVarientList(List<ProductVariant> productVarientList) {
//        this.productVariantList = productVarientList;
//    }
}
