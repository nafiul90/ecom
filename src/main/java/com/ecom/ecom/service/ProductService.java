package com.ecom.ecom.service;

import com.ecom.ecom.exception.AlreadyExistsException;
import com.ecom.ecom.exception.DoesNotExistsException;
import com.ecom.ecom.model.Product;
import com.ecom.ecom.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepo productRepo;
    private AttributeRepo attributeRepo;
    private MediaFileRepo mediaFileRepo;
    private ProductVariantRepo productVariantRepo;
    private PackagePriceRepo packagePriceRepo;

    public ProductService(ProductRepo productRepo, AttributeRepo attributeRepo, MediaFileRepo mediaFileRepo, ProductVariantRepo productVariantRepo, PackagePriceRepo packagePriceRepo) {
        this.productRepo = productRepo;
        this.attributeRepo = attributeRepo;
        this.mediaFileRepo = mediaFileRepo;
        this.productVariantRepo = productVariantRepo;
        this.packagePriceRepo = packagePriceRepo;
    }

    //get all products
    public ResponseEntity<Iterable<Product>> getAllProduct(){

        ResponseEntity<Iterable<Product>> productResponseEntity = ResponseEntity
                .status(HttpStatus.OK).body(productRepo.findAll());

        return productResponseEntity;
    }

    //get product by name
    public ResponseEntity<Iterable<Product>> getAllProductsByName(String name){
        return ResponseEntity.status(HttpStatus.OK).body(productRepo.findAllByName(name));
    }

    //get product by id
    public ResponseEntity<Product> getProductById(Long id){
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent())
            throw new DoesNotExistsException("Product with this Id "+id+" does not exists");
        else
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    //post single product
    public ResponseEntity<Product> saveProduct(Product product) {
        Optional<Product> optionalProduct = productRepo.findById(product.getId());
        if(optionalProduct.isPresent()){
            throw new AlreadyExistsException("Product Already Exists with Id "+product.getId());
        }

        else{
            productRepo.save(product);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
    }

    //update single product
    public ResponseEntity<Product> updateProduct(Product product){
        if(!productRepo.findById(product.getId()).isPresent())
            throw new DoesNotExistsException("Product with Id "+product.getId()+" does not exists");

        else{
            productRepo.save(product);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
    }

    //delete dingle product
    public ResponseEntity<Product> deleteProductById(long id){
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent())
            throw new DoesNotExistsException("Product with Id "+id+" does not exists.");
        else{
            productRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
        }
    }

}
