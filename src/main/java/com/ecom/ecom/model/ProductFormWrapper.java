package com.ecom.ecom.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductFormWrapper {
    private MultipartFile thumbnailImage;
    private List<MultipartFile> variantImages;
    private String product;

    public MultipartFile getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(MultipartFile thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public List<MultipartFile> getVariantImages() {
        return variantImages;
    }

    public void setVariantImages(List<MultipartFile> variantImages) {
        this.variantImages = variantImages;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductFormWrapper{" +
                "thumbnailImage=" + thumbnailImage +
                ", variantImages=" + variantImages +
                ", product='" + product + '\'' +
                '}';
    }
}
