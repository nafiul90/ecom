package com.ecom.ecom.repository;

import com.ecom.ecom.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product,Long> {

    Iterable<Product> findAllByName(String name);
}
