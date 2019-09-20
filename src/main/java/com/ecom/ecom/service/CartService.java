package com.ecom.ecom.service;

import com.ecom.ecom.exception.AlreadyExistsException;
import com.ecom.ecom.exception.DoesNotExistsException;
import com.ecom.ecom.model.Cart;
import com.ecom.ecom.model.Customer;
import com.ecom.ecom.repository.CartRepo;
import com.ecom.ecom.repository.CustomerRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private CartRepo cartRepo;
    private CustomerRepo customerRepo;

    public CartService(CartRepo cartRepo, CustomerRepo customerRepo) {
        this.cartRepo = cartRepo;
        this.customerRepo = customerRepo;
    }

    public ResponseEntity<Iterable<Cart>> getAllCarts() {
        return ResponseEntity.status(200).body(cartRepo.findAll());
    }

    public ResponseEntity<Optional<Cart>> getCartById(long id) {
        Optional<Cart> optionalCart = cartRepo.findById(id);
        if(!optionalCart.isPresent()){
            throw new DoesNotExistsException("Cart with this id "+id+" does not exists");
        }

        return ResponseEntity.status(200).body(optionalCart);
    }

    public ResponseEntity<Cart> saveCart(Cart cart) {

        //check the customer.
        Optional<Customer> optionalCustomer = customerRepo.findById(cart.getCustomerId());
        if(!optionalCustomer.isPresent()){
            throw new DoesNotExistsException("Customer with this id "+cart.getCustomerId()
            +" does not exists");
        }

        //check if the customer already has a cart.
        Optional<Cart> optionalCart = cartRepo.findByCustomerId(cart.getCustomerId());
        if(optionalCart.isPresent()){
            throw new AlreadyExistsException("Cart already exists.update request is allowed only.");
        }

        //check if the cart already exists.
        optionalCart = cartRepo.findById(cart.getId());
        if(optionalCart.isPresent())
            throw new AlreadyExistsException("Cart with this id "+cart.getId()+" already exists.");
        cartRepo.save(cart);
        return ResponseEntity.status(200).body(cart);
    }

    public ResponseEntity<Cart> updateCart(long id,Cart cart) {
        Optional<Cart> optionalCart = cartRepo.findById(id);
        if(!optionalCart.isPresent()){
            throw new DoesNotExistsException("Cart with this id "+id+" does not exists");
        }
        cartRepo.save(cart);

        return ResponseEntity.status(200).body(cart);
    }

    public ResponseEntity<Optional<Cart>> deleteCart(long id) {
        Optional<Cart> optionalCart = cartRepo.findById(id);
        if(!optionalCart.isPresent()){
            throw new DoesNotExistsException("Cart with this id "+id+" does not exists");
        }

        cartRepo.delete(optionalCart.get());

        return ResponseEntity.status(200).body(optionalCart);
    }
}
