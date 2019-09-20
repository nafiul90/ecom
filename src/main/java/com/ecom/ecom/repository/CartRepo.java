package com.ecom.ecom.repository;

import com.ecom.ecom.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepo extends CrudRepository<Cart,Long> {
    public Optional<Cart> findByCustomerId(long id);
}
