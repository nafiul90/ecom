package com.ecom.ecom.controller;

import com.ecom.ecom.model.Cart;
import com.ecom.ecom.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("cart")
public class CartController {

    private final String allowedClientUrl = "https://ns471.csb.app";//"http://localhost:3000";
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @CrossOrigin(origins = allowedClientUrl)
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Iterable<Cart>> getAllCart(){
        return cartService.getAllCarts();
    }

    @CrossOrigin(origins = allowedClientUrl)
    @GetMapping("id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Cart>> getCartById(@PathVariable long id){
        return cartService.getCartById(id);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @PostMapping("add/")
    @ResponseBody
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart){
        return cartService.saveCart(cart);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @PutMapping("update/{id}")
    @ResponseBody
    public ResponseEntity<Cart> updateCart(@PathVariable long id, @RequestBody Cart cart){
        return cartService.updateCart(id,cart);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Cart>> deleteCart(@PathVariable long id){
        return cartService.deleteCart(id);
    }

}
