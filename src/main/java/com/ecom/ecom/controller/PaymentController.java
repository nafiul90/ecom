package com.ecom.ecom.controller;

import com.ecom.ecom.model.Payment;
import com.ecom.ecom.service.OrderService;
import com.ecom.ecom.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("payment")
public class PaymentController {
    private final String allowedClientUrl = "https://ns471.csb.app";//"http://localhost:3000";
    private PaymentService paymentService;
    private OrderService orderService;

    public PaymentController(PaymentService paymentService, OrderService orderService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @CrossOrigin(origins = allowedClientUrl)
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Iterable<Payment>> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @CrossOrigin(origins = allowedClientUrl)
    @PostMapping("add/")
    @ResponseBody
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment){
        return paymentService.savePayment(payment);
    }
}
