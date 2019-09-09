package com.ecom.ecom.controller;


import com.ecom.ecom.model.*;
import com.ecom.ecom.repository.*;
import com.ecom.ecom.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    private ProductService productService;
    private final String allowedClientUrl = "http://localhost:3000";//"http://localhost:3000";

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @CrossOrigin(origins = allowedClientUrl)
    @GetMapping("")
    @ResponseBody
    ResponseEntity<Iterable<Product>> getAllProduct(){
        return productService.getAllProduct();
    }

    @CrossOrigin(origins = allowedClientUrl)
    @GetMapping("id/{id}")
    @ResponseBody
    ResponseEntity<Product> getProductById(@PathVariable long id){
        return productService.getProductById(id);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @GetMapping("name/{name}")
    @ResponseBody
    ResponseEntity<Iterable<Product>> getAllProductsByName(@PathVariable String name){
        return productService.getAllProductsByName(name);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @PostMapping("add-product")
    @ResponseBody
    ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        //first time create a product and save to database.
        //Product product = createProductDemo();
        return productService.saveProduct(product);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @PutMapping("update")
    @ResponseBody
    ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @PutMapping("delete/{id}")
    @ResponseBody
    ResponseEntity<Product> deleteProduct(@PathVariable long id){
        return productService.deleteProductById(id);
    }





    //for first time to create a product.
    Product createProductDemo(){
        Attribute attribute1 = new Attribute("color","red");

        Attribute attribute2 = new Attribute("color","red");


        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(attribute1);
        attributeList.add(attribute2);

        MediaFile thumbnailImage = new MediaFile("image","fileUrlDemo");


        MediaFile mediaFile1 = new MediaFile("image","fileUrlDemo");
        MediaFile mediaFile2 = new MediaFile("image","fileUrlDemo");


        List<MediaFile> mediaFileList = new ArrayList<>();
        mediaFileList.add(mediaFile1);
        mediaFileList.add(mediaFile2);

        PackagePrice packagePrice = new PackagePrice(10,100);

        List<PackagePrice> packagePriceList = new ArrayList<>();
        packagePriceList.add(packagePrice);
        ProductVariant productVariant = new ProductVariant(8,10,100,packagePriceList,0,0,mediaFileList,attributeList);

        List<ProductVariant> productVariantList = new ArrayList<>();
        productVariantList.add(productVariant);

        Product product = new Product("Shirt","Men->Clothing->Formal->Top->Shirt",mediaFile1,productVariantList);

        return product;
    }
}
