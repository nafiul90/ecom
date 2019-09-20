package com.ecom.ecom.controller;

import com.ecom.ecom.model.ProductOrder;
import com.ecom.ecom.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("order")
public class OrderController {
    private final String allowedClientUrl = "https://ns471.csb.app";//"http://localhost:3000";

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = allowedClientUrl)
    @ResponseBody
    @GetMapping("")
    public ResponseEntity<Iterable<ProductOrder>> getAllOrder(){
        return orderService.getAllOrders();
    }

    @CrossOrigin(origins = allowedClientUrl)
    @ResponseBody
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<ProductOrder>> getOrderById(@PathVariable long id){
        return orderService.getOrderById(id);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @ResponseBody
    @GetMapping("customer-id/{id}")
    public ResponseEntity<Iterable<ProductOrder>> getAllOrdersByCustomerId(@PathVariable long id){
        return orderService.getOrdersByCustomerId(id);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @ResponseBody
    @PostMapping("add")
    public ResponseEntity<ProductOrder> addOrder(@RequestBody ProductOrder productOrder){
        return orderService.saveOrder(productOrder);
    }
}
