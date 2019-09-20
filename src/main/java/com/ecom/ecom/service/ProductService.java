package com.ecom.ecom.service;

import com.ecom.ecom.exception.AlreadyExistsException;
import com.ecom.ecom.exception.DoesNotExistsException;
import com.ecom.ecom.model.MediaFile;
import com.ecom.ecom.model.Product;
import com.ecom.ecom.model.ProductFormWrapper;
import com.ecom.ecom.repository.*;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    //delete single product
    public ResponseEntity<Product> deleteProductById(long id){
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent())
            throw new DoesNotExistsException("Product with Id "+id+" does not exists.");
        else{
            productRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
        }
    }

    public ResponseEntity<Product> saveProduct(ProductFormWrapper model) {
        //save thumbnail image and get the path.
        Path thumbnailImagePath = saveImage(model.getThumbnailImage());

        //save variant images and get the path to images list.
        List<Path> images = new ArrayList<>();
        model.getVariantImages().forEach(e-> {
            images.add(saveImage(e));
        });

        //get product object from json data.
        String productJson = model.getProduct();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Product product = mapper.readValue(productJson,Product.class);

            //set product thumbnailImage path.
            product.setMediaFile(new MediaFile("image",thumbnailImagePath.toString()));

            //set variant images paths.
            int i=0;
            product.getProductVariantList().forEach(e->{
                MediaFile mediaFile = new MediaFile("image",images.get(i).toString());
                List<MediaFile> mediaFileList = new ArrayList<>();
                mediaFileList.add(mediaFile);
                e.setMediaFileList(mediaFileList);
            });

            System.out.println("product-->"+product);
            productRepo.save(product);
            return ResponseEntity.status(HttpStatus.OK).body(product);

        } catch (IOException e) {
            e.printStackTrace();

        }

        return null;


    }

    private Path saveImage(MultipartFile file) {
        if (!file.isEmpty()) {
            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();

                Path path = Paths.get( "images/"+file.getOriginalFilename());
                Files.write(path, bytes);
                return path;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
